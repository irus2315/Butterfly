package kr.twothumb.butterfly.models

import kotlinx.serialization.Serializable

@Serializable
data class Member(
    val user: User,
    val role: RoleType,
)

enum class RoleType {
    WATCH,
    MANAGER,
    ASSIGNEE
}