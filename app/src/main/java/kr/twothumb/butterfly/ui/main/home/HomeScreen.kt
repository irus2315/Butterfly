package kr.twothumb.butterfly.ui.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.rounded.PrivacyTip
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.twothumb.butterfly.R
import kr.twothumb.butterfly.common.SystemUi
import kr.twothumb.butterfly.common.circularReveal
import kr.twothumb.butterfly.models.Project
import kr.twothumb.butterfly.models.Task
import kr.twothumb.butterfly.models.local.LocalProjectDataProvider
import kr.twothumb.butterfly.ui.component.BaseButton
import kr.twothumb.butterfly.ui.component.topbar.MainTopBar
import kr.twothumb.butterfly.ui.theme.ButterflyTheme
import kr.twothumb.butterfly.ui.theme.Spaces
import kr.twothumb.butterfly.ui.theme.buttonText
import kr.twothumb.butterfly.ui.theme.colorProject2
import kr.twothumb.butterfly.ui.theme.colorProject3
import kr.twothumb.butterfly.ui.theme.init
import kr.twothumb.butterfly.ui.theme.labelLarge
import kr.twothumb.butterfly.ui.theme.labelMedium
import kr.twothumb.butterfly.ui.theme.labelMediumNumber
import kr.twothumb.butterfly.ui.theme.labelSmall
import kr.twothumb.butterfly.ui.theme.labelTag
import kr.twothumb.butterfly.ui.theme.labelTagInverse
import kr.twothumb.butterfly.ui.theme.textDp
import kr.twothumb.lib.logger.DevLog

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    projectList: List<Project> = LocalProjectDataProvider.projectList,
    taskList: List<Task> = LocalProjectDataProvider.taskList,
) {

    SystemUi()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeUi(uiState = uiState, onClickAction = {

    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeUi(
    uiState: HomeUiState = HomeUiState(),
    onClickAction: (type: HomeViewModel.OnSelected) -> Unit,
) {
    DevLog.i("HomeScreen Init")
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    LaunchedEffect(uiState.initialize) {}
    BackdropScaffold(scaffoldState = scaffoldState,
        backLayerBackgroundColor = MaterialTheme.colorScheme.primary,
        frontLayerElevation = 0.dp,
        frontLayerScrimColor = Color.Unspecified,
        appBar = {
            MainTopBar(stringResource(id = R.string.blank)) {
                onClickAction.invoke(HomeViewModel.OnSelected.Test1)
            }
        },
        backLayerContent = {
            /**
             * todo
             * 1. 전체 높이에서 변경
             * 2. reveal 실행
             * 가능??..
             */
            if (!uiState.initialize) {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .circularReveal(true)
                        .padding(Spaces.LONG_PADDING, Spaces.BASE_PADDING, Spaces.BASE_PADDING, Spaces.BASE_PADDING),

                    ) {
                    Text(
                        text = "Hi Twothumb!\nYou have 3 task for this week.", style = MaterialTheme.typography.bodyLarge.init(
                            fontSize = 24.textDp, lineHeight = 30.textDp, color = MaterialTheme.colorScheme.inversePrimary, textAlign = TextAlign.Start
                        ), softWrap = true,

                        modifier = Modifier
                    )

                    Spacer(modifier = Modifier.height(Spaces.BASE_PADDING))

                    BaseButton(text = "VIEW UPCOMMING TASK", style = MaterialTheme.typography.titleMedium.buttonText.init(
                        fontSize = 14.textDp, lineHeight = 16.textDp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                    ), modifier = Modifier
                        .background(Color.Transparent)
                        .border(
                            width = 1.dp, color = MaterialTheme.colorScheme.inversePrimary, shape = RoundedCornerShape(Spaces.ROUND_CORNER)
                        )
                        .wrapContentWidth(), onClickAction = {
                        DevLog.d("btn1 Clicked!")
                    })


                    BaseButton(text = "VIEW UPCOMMING TASK", style = MaterialTheme.typography.titleMedium.buttonText.init(
                        fontSize = 14.textDp, lineHeight = 16.textDp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                    ), onClickAction = {
                        DevLog.d("btn1 Clicked222")
                    })
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .circularReveal(true)
                )
            }
        },

        frontLayerContent = {
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(Spaces.BASE_MARGIN)
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(start = Spaces.BASE_GAP)
                            .clickable {
                                onClickAction.invoke(HomeViewModel.OnSelected.Test1)
                            }, style = MaterialTheme.typography.bodyLarge.init(
                            fontSize = 18.textDp, lineHeight = 20.textDp
                        ), text = "My Project"
                    )
                    Spacer(Modifier.height(Spaces.BASE_GAP))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(items = uiState.projectList, key = { _, project ->
                            project.uuid
                        }) { _, project ->
                            SimpleProject(
                                Modifier.fillMaxWidth(), project
                            )
                        }
                    }
                    Spacer(Modifier.height(Spaces.LONG_PADDING))

                }
                item {
                    TaskList(uiState.taskList)
                }
            }
        })
}

@Composable
private fun TaskList(
    taskList: List<Task>
) {
    Text(
        modifier = Modifier
            .padding(start = Spaces.BASE_GAP)
            .clickable {}, style = MaterialTheme.typography.bodyLarge.init(
            fontSize = 18.textDp, lineHeight = 20.textDp
        ), text = "My Tasks"
    )

    Spacer(Modifier.height(Spaces.BASE_GAP))
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        taskList.forEach {
            TaskItem(
                Modifier.fillMaxWidth(), it
            )
        }
    }
//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        itemsIndexed(items = taskList, key = { _, task ->
//            task.uuid
//        }) {_, task ->
//            TaskItem(
//                Modifier.fillMaxWidth(), task
//            )
//        }
//    }
}

@Composable
private fun SimpleProject(
    modifier: Modifier = Modifier, project: Project
) {
    Box(
        modifier = Modifier
            .width(240.dp)
            .background(
                color = Color(
                    project.color
                        .substring(2)
                        .toLong(16)
                ), shape = RoundedCornerShape(12.dp)
            )
            .padding(Spaces.BASE_PADDING)
    ) {

        Row(
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Text(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.inversePrimary, shape = RoundedCornerShape(12.dp)
                    )
                    .height(16.dp)
                    .padding(4.dp, 0.dp)
                    .wrapContentHeight(Alignment.CenterVertically),
                style = MaterialTheme.typography.labelLarge.labelTag,
                text = "HIGH",
            )

            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.inversePrimary, shape = RoundedCornerShape(12.dp)
                    )
                    .height(16.dp)
                    .padding(4.dp, 0.dp)
                    .wrapContentHeight(Alignment.CenterVertically),
                style = MaterialTheme.typography.labelLarge.labelTag,
                text = "D-1",
            )

            Spacer(modifier = Modifier.width(Spaces.BASE_PADDING))

            Icon(
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.primary,
                imageVector = Icons.Default.MoreHoriz,
                contentDescription = stringResource(id = R.string.blank)
            )
        }

        Column(
        ) {

            Text(
                style = MaterialTheme.typography.bodyLarge.labelLarge, text = project.title,

                )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                style = MaterialTheme.typography.bodyLarge.labelMedium, text = project.description, maxLines = 2, minLines = 2, overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                style = MaterialTheme.typography.labelLarge.labelSmall, text = "할일이 3건 남았어요.", overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun TaskItem(
    modifier: Modifier = Modifier, task: Task
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(Spaces.ROUND_CORNER)
            )
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline), RoundedCornerShape(Spaces.ROUND_CORNER)
            )
            .padding(Spaces.PADDING)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier.height(32.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.profile1),
                    contentDescription = "profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(style = MaterialTheme.typography.bodyLarge.labelMedium, text = "불타는 냉장고")
                    Text(style = MaterialTheme.typography.labelMedium.labelSmall, text = "2024.01.01")
                }
            }

            Row(
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Text(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(12.dp)
                        )
                        .height(16.dp)
                        .padding(4.dp, 0.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    style = MaterialTheme.typography.labelLarge.labelTag,
                    text = "HIGH",
                )

                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(12.dp)
                        )
                        .height(16.dp)
                        .padding(4.dp, 0.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    style = MaterialTheme.typography.labelLarge.labelTag,
                    text = "D-1",
                )

                Spacer(modifier = Modifier.width(Spaces.BASE_PADDING))

                Icon(
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = stringResource(id = R.string.blank)
                )
            }
        }

        Spacer(modifier = Modifier.height(Spaces.BASE_PADDING))

        /**
         * 내용
         */
        Column(modifier = Modifier.padding(start = Spaces.SMALL_GAP)) {

            Text(
                style = MaterialTheme.typography.bodyLarge.labelLarge, text = task.title, maxLines = 1, minLines = 1, overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))
            /**
             * TAG
             */
            Row(horizontalArrangement = Arrangement.spacedBy(Spaces.SMALL_GAP)) {
                /**
                 * todo dummy
                 */
                Text(
                    modifier = Modifier
                        .background(
                            color = Color(
                                LocalProjectDataProvider
                                    .toHexColorString(
                                        colorProject2.toArgb()
                                    )
                                    .substring(2)
                                    .toLong(16)
                            ), shape = RoundedCornerShape(12.dp)
                        )
                        .height(16.dp)
                        .padding(4.dp, 0.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    style = MaterialTheme.typography.labelLarge.labelTagInverse,
                    text = "TODO",
                )
                Text(
                    modifier = Modifier
                        .background(
                            color = Color(
                                LocalProjectDataProvider
                                    .toHexColorString(
                                        colorProject2.toArgb()
                                    )
                                    .substring(2)
                                    .toLong(16)
                            ), shape = RoundedCornerShape(12.dp)
                        )
                        .height(16.dp)
                        .padding(4.dp, 0.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    style = MaterialTheme.typography.labelLarge.labelTagInverse,
                    text = "일정관련",
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(style = MaterialTheme.typography.bodyLarge.labelMedium, text = task.content)

            Spacer(modifier = Modifier.height(4.dp))

            /**
             * todo 성능이슈 있을듯 zIndex말고 다른방법 찾아야.
             */

            Spacer(modifier = Modifier.height(1.dp).background(MaterialTheme.colorScheme.outline))
            Row() {
                Row(horizontalArrangement = Arrangement.spacedBy(Spaces.MINUS_GAP)) {
                    Box(modifier = Modifier.zIndex(3f)) {
                        Image(
                            painter = painterResource(R.drawable.profile1),
                            contentDescription = "profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .border(1.dp, MaterialTheme.colorScheme.inversePrimary, CircleShape)
                                .size(18.dp)
                                .padding(1.dp)
                                .clip(CircleShape)
                        )
                    }
                    Box(modifier = Modifier.zIndex(2f)) {
                        Image(
                            painter = painterResource(R.drawable.profile1),
                            contentDescription = "profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .border(1.dp, MaterialTheme.colorScheme.inversePrimary, CircleShape)
                                .size(18.dp)
                                .padding(1.dp)
                                .clip(CircleShape)
                        )
                    }
                    Box(modifier = Modifier.zIndex(1f)) {
                        Image(
                            painter = painterResource(R.drawable.profile1),
                            contentDescription = "profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .border(1.dp, MaterialTheme.colorScheme.inversePrimary, CircleShape)
                                .size(18.dp)
                                .padding(1.dp)
                                .clip(CircleShape)

                        )
                    }
                }
                Icon(
                    painterResource(R.drawable.ic_add_people), "addPeople",
                    tint = MaterialTheme.colorScheme.inversePrimary,
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.inversePrimary, CircleShape)
                        .size(18.dp)
                        .padding(1.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .padding(2.dp)
                )
            }
        }

        Row(modifier = Modifier.align(Alignment.End)) {
            Icon(
                painterResource(R.drawable.ic_file), "back",
                modifier = Modifier
                    .size(18.dp)

            )
            Spacer(modifier = Modifier.width(Spaces.LESS_SMALL_GAP))
            Text(style = MaterialTheme.typography.bodyMedium.labelMediumNumber, text = "0")
            Spacer(modifier = Modifier.width(Spaces.BASE_GAP))
            Icon(
                painterResource(R.drawable.ic_comment), "back",
                modifier = Modifier
                    .size(18.dp)
            )
            Spacer(modifier = Modifier.width(Spaces.LESS_SMALL_GAP))
            Text(style = MaterialTheme.typography.bodyMedium.labelMediumNumber, text = "0")

        }
    }
}

@Preview
@Composable
fun SimpleProject() {
    ButterflyTheme {
        SimpleProject(project = LocalProjectDataProvider.projectList.first())
    }
}

@Preview
@Composable
fun TaskItem() {
    ButterflyTheme {
        TaskItem(task = LocalProjectDataProvider.taskList.first())
    }
}

@Preview
@Composable
fun TaskListPreview() {
    ButterflyTheme {
        TaskList(taskList = LocalProjectDataProvider.taskList)
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    ButterflyTheme {
        val uiState = HomeUiState()
        HomeUi(uiState = uiState, onClickAction = {})
    }
}

@Preview
@Composable
fun TestText() {
    ButterflyTheme {
        Column(modifier = Modifier.background(Color.White)) {
            Text(
                style = MaterialTheme.typography.labelLarge.labelLarge, text = "안녕하세요1234 twothBMGJ"
            )
            Text(
                style = MaterialTheme.typography.labelLarge.labelMedium, text = "안녕하세요1234 twothBMGJ"
            )
            Text(
                style = MaterialTheme.typography.labelLarge.labelSmall, text = "안녕하세요1234 twothBMGJ"
            )
            Text(
                style = MaterialTheme.typography.labelLarge.labelTag, text = "안녕하세요1234 twothBMGJ"
            )
            Text(
                style = MaterialTheme.typography.labelMedium.labelLarge, text = "안녕하세요1234 twothBMGJ"
            )
            Text(
                style = MaterialTheme.typography.labelMedium.labelMedium, text = "안녕하세요1234 twothBMGJ"
            )
            Text(
                style = MaterialTheme.typography.labelMedium.labelSmall, text = "안녕하세요1234 twothBMGJ"
            )
            Text(
                style = MaterialTheme.typography.labelMedium.labelTag, text = "안녕하세요1234 twothBMGJ"
            )
        }
    }
}
