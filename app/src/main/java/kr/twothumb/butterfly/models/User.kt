package kr.twothumb.butterfly.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val uuid: String,
    val name: String = "",
    val profile: String = "",
    val email: String = "",
    val emailVerify: Boolean = false,
    val phoneNumber: String = "",
    val company: String = "",
    val termsAgree: Boolean = false,
    val marketingAgree: Boolean = false,
    val lastedLoginAt: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val status: String = ""
) {
}
