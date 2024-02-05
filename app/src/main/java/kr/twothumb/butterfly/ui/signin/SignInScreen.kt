package kr.twothumb.butterfly.ui.signin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    Scaffold(
        content = {
            Box(
                Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
//                MainNavGraph(navController = navController)
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = AnnotatedString("Your Total Balance"),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )
}

//@Composable
//private fun TasksContent(
//    loading: Boolean,
//    tasks: List<Task>,
//    @StringRes currentFilteringLabel: Int,
//    @StringRes noTasksLabel: Int,
//    @DrawableRes noTasksIconRes: Int,
//    onRefresh: () -> Unit,
//    onTaskClick: (Task) -> Unit,
//    onTaskCheckedChange: (Task, Boolean) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    LoadingContent(
//        loading = loading,
//        empty = tasks.isEmpty() && !loading,
//        emptyContent = { TasksEmptyContent(noTasksLabel, noTasksIconRes, modifier) },
//        onRefresh = onRefresh
//    ) {
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin))
//        ) {
//            Text(
//                text = stringResource(currentFilteringLabel),
//                modifier = Modifier.padding(
//                    horizontal = dimensionResource(id = R.dimen.list_item_padding),
//                    vertical = dimensionResource(id = R.dimen.vertical_margin)
//                ),
//                style = MaterialTheme.typography.h6
//            )
//            LazyColumn {
//                items(tasks) { task ->
//                    TaskItem(
//                        task = task,
//                        onTaskClick = onTaskClick,
//                        onCheckedChange = { onTaskCheckedChange(task, it) }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun TaskItem(
//    task: Task,
//    onCheckedChange: (Boolean) -> Unit,
//    onTaskClick: (Task) -> Unit
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(
//                horizontal = dimensionResource(id = R.dimen.horizontal_margin),
//                vertical = dimensionResource(id = R.dimen.list_item_padding),
//            )
//            .clickable { onTaskClick(task) }
//    ) {
//        Text(
//            text = task.titleForList,
//            style = MaterialTheme.typography.h6,
//            modifier = Modifier.padding(
//                start = dimensionResource(id = R.dimen.horizontal_margin)
//            ),
//            textDecoration = if (task.isCompleted) {
//                TextDecoration.LineThrough
//            } else {
//                null
//            }
//        )
//    }
//}
//
//@Composable
//private fun TasksEmptyContent(
//    @StringRes noTasksLabel: Int,
//    @DrawableRes noTasksIconRes: Int,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = noTasksIconRes),
//            contentDescription = stringResource(R.string.no_tasks_image_content_description),
//            modifier = Modifier.size(96.dp)
//        )
//        Text(stringResource(id = noTasksLabel))
//    }
//}
////
////@Preview
////@Composable
////private fun TasksContentPreview() {
////    AppCompatTheme {
////        Surface {
////            TasksContent(
////                loading = false,
////                tasks = listOf(
////                    Task(
////                        title = "Title 1",
////                        description = "Description 1",
////                        isCompleted = false,
////                        id = "ID 1"
////                    ),
////                    Task(
////                        title = "Title 2",
////                        description = "Description 2",
////                        isCompleted = true,
////                        id = "ID 2"
////                    ),
////                    Task(
////                        title = "Title 3",
////                        description = "Description 3",
////                        isCompleted = true,
////                        id = "ID 3"
////                    ),
////                    Task(
////                        title = "Title 4",
////                        description = "Description 4",
////                        isCompleted = false,
////                        id = "ID 4"
////                    ),
////                    Task(
////                        title = "Title 5",
////                        description = "Description 5",
////                        isCompleted = true,
////                        id = "ID 5"
////                    ),
////                ),
////                currentFilteringLabel = R.string.label_all,
////                noTasksLabel = R.string.no_tasks_all,
////                noTasksIconRes = R.drawable.logo_no_fill,
////                onRefresh = { },
////                onTaskClick = { },
////                onTaskCheckedChange = { _, _ -> },
////            )
////        }
////    }
////}
////
////@Preview
////@Composable
////private fun TasksContentEmptyPreview() {
////    AppCompatTheme {
////        Surface {
////            TasksContent(
////                loading = false,
////                tasks = emptyList(),
////                currentFilteringLabel = R.string.label_all,
////                noTasksLabel = R.string.no_tasks_all,
////                noTasksIconRes = R.drawable.logo_no_fill,
////                onRefresh = { },
////                onTaskClick = { },
////                onTaskCheckedChange = { _, _ -> },
////            )
////        }
////    }
////}
////
////@Preview
////@Composable
////private fun TasksEmptyContentPreview() {
////    AppCompatTheme {
////        Surface {
////            TasksEmptyContent(
////                noTasksLabel = R.string.no_tasks_all,
////                noTasksIconRes = R.drawable.logo_no_fill
////            )
////        }
////    }
////}
////
////@Preview
////@Composable
////private fun TaskItemPreview() {
////    AppCompatTheme {
////        Surface {
////            TaskItem(
////                task = Task(
////                    title = "Title",
////                    description = "Description",
////                    id = "ID"
////                ),
////                onTaskClick = { },
////                onCheckedChange = { }
////            )
////        }
////    }
////}
////
////@Preview
////@Composable
////private fun TaskItemCompletedPreview() {
////    AppCompatTheme {
////        Surface {
////            TaskItem(
////                task = Task(
////                    title = "Title",
////                    description = "Description",
////                    isCompleted = true,
////                    id = "ID"
////                ),
////                onTaskClick = { },
////                onCherckedChange = { }
////            )
////        }
////    }
////}
