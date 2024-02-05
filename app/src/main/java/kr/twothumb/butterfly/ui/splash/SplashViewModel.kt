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

package kr.twothumb.butterfly.ui.splash

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.twothumb.butterfly.common.Async
import kr.twothumb.butterfly.common.BaseViewModel
import kr.twothumb.butterfly.models.Project
import kr.twothumb.butterfly.models.ResponseData
import kr.twothumb.butterfly.models.local.LocalProjectDataProvider
import kr.twothumb.lib.logger.DevLog
import java.util.concurrent.Executors.newSingleThreadExecutor
import javax.inject.Inject

data class SplashUiState(
    val isFirebaseLogin: Boolean = false,
    val navigateSignInScreen: Boolean = false,
    val navigateSignUpScreen: Boolean = false,
    val navigateMainScreen: Boolean = false,
)

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    application: Application
) : BaseViewModel(application) {

    private var auth: FirebaseAuth = Firebase.auth
    private val userStateFlow: MutableStateFlow<FirebaseUser?> = MutableStateFlow(null)
    private val tokenId: MutableStateFlow<String?> = MutableStateFlow("")

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    private val _userInfo: MutableStateFlow<Async<ResponseData<Any>>> = MutableStateFlow(Async.Initial)
    private val _tempProject: MutableStateFlow<Async<ResponseData<Project>>> = MutableStateFlow(Async.Initial)
    private val _createProject: MutableStateFlow<Async<ResponseData<Project>>> = MutableStateFlow(Async.Initial)

    init {

//        auth.currentUser?.let {
//            /**
//             * 게스트면 바로 로그인
//             */
//            if (it.isAnonymous) {
//                onSignInAnonymously()
//            }
//            /**
//             * 이메일 로그인 유저 확인..
//             */
//            else {
//                DevLog.i("유저 확인 / 로그인")
//            }
//        }


        observeFlow(_userInfo, successHandler = {
            _uiState.update {
                it.copy(navigateMainScreen = true)
            }
        })

        observeFlow(_tempProject, successHandler = {
            it.result?.let {
            }
        })

        observeFlow(_createProject, successHandler = {
            if (it.isSuccess) {
                DevLog.d("success")
                it.result?.let { project ->
                    DevLog.d("success : $project")
                }
            } else {
                DevLog.d("fail")
            }
        })
    }

    fun onSignInEmailSelected() {

    }

    /**
     * 테스트 용도 인지?
     * 231213???
     */
    fun onSignIn() {
        _uiState.update {
            it.copy(navigateSignUpScreen = true)
        }
    }

    fun onSignInAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener(newSingleThreadExecutor()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    DevLog.d("signInAnonymously:success")
                    val user = auth.currentUser
                    user?.let {
                        it.getIdToken(true).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                task.result.token?.let { tokenString ->
                                    token = tokenString
                                    viewModelScope.launch {
                                        requestAuthenticated()
                                    }
                                } ?: run {
                                    onHandleError(task.exception?.localizedMessage)
                                }
                            } else {
                                onHandleError(task.exception?.localizedMessage)
                            }
                        }
                    }
                } else {
                    onHandleError(task.exception?.localizedMessage)
                }
            }
    }

    fun onNavigatedScreen() {
        _uiState.update {
            it.copy(navigateMainScreen = false, navigateSignInScreen = false, navigateSignUpScreen = false)
        }
    }

    private suspend fun requestAuthenticated() {
        getNetworkModule().onCallApi(url = "signIn", flow = _userInfo)
    }

    /**
     * todo 유저 체크 내부로 따로 정리
     */
    fun onTest() {
        val user = auth.currentUser
        user?.let {
            it.getIdToken(true).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result.token?.let { tokenString ->
                        token = tokenString
                        viewModelScope.launch {
                            getNetworkModule().onCallApi(url = "initializeProject", flow = _tempProject)
                        }
                    } ?: run {
                        onHandleError(task.exception?.localizedMessage)
                    }
                } else {
                    onHandleError(task.exception?.localizedMessage)
                }
            }
        }
    }

    fun onCreateDummyProject() {
        val user = auth.currentUser
        user?.let {
            it.getIdToken(true).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result.token?.let { tokenString ->
                        token = tokenString
                        viewModelScope.launch {
                            getNetworkModule().onCallApi(url = "createDirectProject", body = LocalProjectDataProvider.projectList[4], flow = _tempProject)
                        }
                    } ?: run {
                        onHandleError(task.exception?.localizedMessage)
                    }
                } else {
                    onHandleError(task.exception?.localizedMessage)
                }
            }
        }
    }

    enum class OnSelected { SignIn, SignInGuest, Test, CreateDummyProject }
}
