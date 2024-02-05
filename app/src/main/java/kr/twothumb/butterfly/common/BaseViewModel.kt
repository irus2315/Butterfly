/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.twothumb.butterfly.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kr.twothumb.butterfly.R
import kr.twothumb.butterfly.common.Constants.TIMEOUT
import kr.twothumb.butterfly.models.ResponseData
import kr.twothumb.lib.logger.DevLog
import java.io.PrintWriter
import java.io.StringWriter

data class BaseUiStatus(
    val dialogMessage: String = "",
    val isLoading: Boolean = false
)

open class BaseViewModel(
    private val application: Application
) : AndroidViewModel(application) {

    private val _baseUiState = MutableStateFlow(BaseUiStatus())
    val baseUiState: StateFlow<BaseUiStatus> = _baseUiState.asStateFlow()
//    val baseUiState by mutableStateOf(BaseUiStatus())

    private var auth: FirebaseAuth = Firebase.auth
    private val userStateFlow: MutableStateFlow<FirebaseUser?> = MutableStateFlow(null)
    private val tokenId: MutableStateFlow<String?> = MutableStateFlow("")
    val dialogMessage: MutableStateFlow<String?> = MutableStateFlow("")
    val toastMessage: MutableStateFlow<String?> = MutableStateFlow("")
    val errorMessage: MutableStateFlow<String?> = MutableStateFlow("")

    var token = ""
    fun errorMessage(status:Int, description: String): String = application.getString(R.string.on_error_code, status, description)

    init {
        /**
         * todo TokenSetting
         */
    }

    /**
     * todo 모듈화
     */
    protected fun getNetworkModule(needToken: Boolean = true, useLog: Boolean = true) = HttpClient(CIO) {
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        if (needToken) {
            defaultRequest {
                header("Authorization", "Bearer $token")
            }
        }
    }

    protected suspend inline fun <reified T> HttpClient.onCallApi(
        useBaseUrl: Boolean = true,
        useLog: Boolean = true,
        url: String,
        body: Any? = null,
        flow: MutableStateFlow<Async<ResponseData<T>>>
    ) {
        flow.value = Async.Loading
        try {
            val host = if (useBaseUrl) "${Constants.BASE_URL}/$url" else url
            if (useLog)
                DevLog.d("request: $host")
            val response: HttpResponse = post(host) {
//                this.attributes

                body?.let {
                    contentType(ContentType.Application.Json)
                    setBody(it)
                }
            }
            val responseStatus = response.status
            if (useLog)
                DevLog.d("Http : ", "responseStatus: $responseStatus")
            if (responseStatus == HttpStatusCode.OK) {
                if (useLog)
                    DevLog.d("Http : ", "requestKtorResponse: ${response.bodyAsText()}") //받아온 data 로그

                val result = response.body<ResponseData<T>>()
                flow.value = Async.Success(result)
            } else {
                flow.value = Async.Error(errorMessage(responseStatus.value, responseStatus.description))
            }
        } catch (e: Exception) {
            if (useLog) {
                StringWriter().apply {
                    e.printStackTrace(PrintWriter(this))
                    DevLog.e(this)
                }
            }
            flow.value = Async.Error(e.localizedMessage)
        }
    }


    protected fun <E, T : Async<E>> observeFlow(
        flow: Flow<T>,
        handleLoading: Boolean = false,
        successHandler: ((result: E) -> Unit)? = null,
        errorHandler: ((message: String?) -> Unit)? = null,
    ) {
        viewModelScope.launch {
            flow.collect {
                val data = it.result
                when (it) {
                    is Async.Loading -> {
                        if (!handleLoading)
                            onLoading(true)
                    }

                    is Async.Success<*> -> {
                        if (!handleLoading)
                            onLoading(false)
                        data?.let {
                            successHandler?.invoke(data)
                        }
                    }

                    is Async.Error -> {
                        if (!handleLoading)
                            onLoading(false)
                        errorHandler?.invoke(it.errorMessage) ?: run {
                            onHandleError(it.errorMessage)
                        }
                    }
                }
            }
        }
    }

    protected suspend fun onLoading(isLoading: Boolean) {
        _baseUiState.update {
            it.copy(isLoading = isLoading)
        }
    }

    protected fun onHandleError(message: String?) {
        _baseUiState.update {
            message?.let { message ->
                it.copy(dialogMessage = message)

            } ?: run {
                it.copy(dialogMessage = application.getString(R.string.on_error))
            }
        }
    }
}
