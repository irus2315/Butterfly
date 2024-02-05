package kr.twothumb.butterfly.models.local

import androidx.compose.ui.graphics.toArgb
import kr.twothumb.butterfly.models.Project
import kr.twothumb.butterfly.models.StatusType
import kr.twothumb.butterfly.models.Task
import kr.twothumb.butterfly.models.TaskType
import kr.twothumb.butterfly.models.User
import kr.twothumb.butterfly.ui.theme.*
import kr.twothumb.lib.logger.DevLog
import java.util.UUID
import kotlin.random.Random

@OptIn(ExperimentalStdlibApi::class)
object LocalProjectDataProvider {

    val projectList = listOf(
        Project(
            uuid = UUID.randomUUID().toString(),
            title = "Tutorial",
            description = "버터플라이 사용법을 안내하는 튜토리얼 프로젝트입니다.\n여기 내용을 뭘채워야 할까요 랜딩 기획 이런거 확인해야 할 듯 합니다.",
            color = toHexColorString(colorProject1.toArgb())
        ), Project(
            uuid = UUID.randomUUID().toString(), title = "ViviiPos", description = "비비포스 안드로이드 앱개발", color = toHexColorString(colorProject2.toArgb())
        ), Project(
            uuid = UUID.randomUUID().toString(), title = "Macro", description = "매크로 개발관리", color = toHexColorString(colorProject3.toArgb())
        ), Project(
            uuid = UUID.randomUUID().toString(), title = "TODO", description = "내 할일", color = toHexColorString(colorProject4.toArgb())
        ), Project(
            uuid = UUID.randomUUID().toString(), title = "Twothumb TODO", description = "사업자 관련 할일", color = toHexColorString(colorProject5.toArgb())
        ), Project(
            uuid = UUID.randomUUID().toString(), title = "DIARY", description = "일기", color = toHexColorString(colorProject6.toArgb())
        ), Project(
            uuid = UUID.randomUUID().toString(), title = "Butterfly", description = "버터플라이 꿀빠는 라이프", color = toHexColorString(colorProject7.toArgb())
        )
    )

    val taskList = listOf(
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
        Task(
            uuid = UUID.randomUUID().toString(),
            title = "9일 미팅",
            manager = User(""),
            content = "두베 미팅",
            status = StatusType.READY,   // 상태
            project = UUID.randomUUID().toString(),
            parentTask = "",
            assignee = null,
            tagList = arrayListOf<String>().run {
                add("todo")
                this
            }
        ),
    )

    private fun getRandomColor() = run {
        val hexFormat = HexFormat {
            upperCase = true; bytes.bytesPerGroup = 2; number {
            removeLeadingZeros = true
        }
        }
        val color = Random.nextInt(256).toHexString(hexFormat)
            .plus(Random.nextInt(256).toHexString(hexFormat))
            .plus(Random.nextInt(256).toHexString(hexFormat))
        DevLog.d("color : $color")
        "0xFF".plus(color)
    }

    fun toHexColorString(color: Int) = run {
        val hexFormat = HexFormat {
            upperCase = true; bytes.bytesPerGroup = 2; number {
            removeLeadingZeros = true
        }
        }
        DevLog.d(color.toHexString(hexFormat), " : " + hexFormat)
        if (color.toHexString(hexFormat).length == 8) {
            "0x".plus(color.toHexString(hexFormat))
        } else {
            "0xFF".plus(color.toHexString(hexFormat))
        }
    }
}
