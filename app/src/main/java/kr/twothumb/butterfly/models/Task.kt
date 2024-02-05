package kr.twothumb.butterfly.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Task(
    val uuid: String = "",      //고유아이디
    val title: String = "",     //타이틀
    val manager: User,     //작성
    val managerNickName: String = "",
    val managerProfile: String = "",
    val content: String = "",   //내용
    val status:StatusType = StatusType.READY,   // 상태

    val createDate: String = "",
    val updateDate: String = "",
    val dueDate: String = "",

    val project: String = "",
    val parentTask: String = "",
    val assignee: User?,
    val taskType: TaskType = TaskType.TODO,

    val priority: Priority = Priority.MEDIUM,

    /**
     * 카운트 추가
     * 소속 프로젝트
     * 상위 태스크
     */
    val attachmentList: ArrayList<String> = arrayListOf(),
    val imageList: ArrayList<String> = arrayListOf(),
    val memberList: ArrayList<String> = arrayListOf(),
    val taskList: ArrayList<Task> = arrayListOf(),
    val tagList: ArrayList<String> = arrayListOf()
)

enum class TaskType {
    TODO, PROJECT, DIARY
}

enum class Priority {
    VERYHIGH, HIGH, MEDIUM, LOW
}