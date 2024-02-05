package kr.twothumb.butterfly.models

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val uuid: String = "",
    val title: String = "",
    val owner: String = "",
    val description: String = "",
    val status:StatusType = StatusType.READY,

    /**
     * "yyyy-MM-dd HH:mm:ss.fff"
     */
    val createDate: String = "",
    val updateDate: String = "",
    val dueDate: String = "",

    val color: String = "0x000000",
    val backgroundImage: String = "",

    /**
     * todo db에 카운트 포함
     */
    val attachmentList: ArrayList<String> = arrayListOf(),
    val imageList: ArrayList<String> = arrayListOf(),
    val memberList: ArrayList<Member> = arrayListOf(),
    val taskList: ArrayList<Task> = arrayListOf(),
)

enum class StatusType {
    COMPLETED, ING, CANCEL, READY
}