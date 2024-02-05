package kr.twothumb.butterfly.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseData<T>(
    val message: String? = "",
    val isSuccess: Boolean = true,
    val errorCode: Int? = 200,
    val result: T? = null
)
