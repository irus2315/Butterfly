package kr.twothumb.butterfly.ui.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kr.twothumb.butterfly.navigation.ButterflyDestinations
import kr.twothumb.butterfly.navigation.MAIN_DESTINATIONS
import kr.twothumb.butterfly.navigation.MainMenuType
import kr.twothumb.butterfly.navigation.MainNavigation
import kr.twothumb.butterfly.common.SystemUi
import kr.twothumb.butterfly.ui.main.home.EmptyComingSoon
import kr.twothumb.butterfly.ui.theme.ButterflyTheme
import kr.twothumb.lib.logger.DevLog

@Composable
fun MainScreen(
    mainMenuType: MainMenuType,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateMainScreen: (MainNavigation) -> Unit
) {
    SystemUi()
    val activity = (LocalContext.current as? Activity)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    val baseUiState by viewModel.baseUiState.collectAsStateWithLifecycle()
//
    BackHandler {
        activity?.finish()
    }
    viewModel.check()
    MainUi(mainMenuType, onNavigateMainScreen = onNavigateMainScreen)
}

@Composable
fun MainUi(
    mainMenuType: MainMenuType,
    uiState: MainUiState = MainUiState(),
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onNavigateMainScreen: (MainNavigation) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination  : String = navBackStackEntry?.destination?.route?: ButterflyDestinations.MAIN_HOME_ROUTE

    DevLog.i("MainScreen Init")
    DevLog.i("navBackStackEntry?.destination?.route : " , navBackStackEntry?.destination?.route)
    DevLog.i("mainMenuType : " , mainMenuType)
    Scaffold(
        content = {
            DevLog.e("is call here > " +  mainMenuType)
            when (mainMenuType) {
                MainMenuType.MENU_HOME -> EmptyComingSoon(MainMenuType.MENU_PROJECT.name, Modifier.padding(it))//HomeScreen(modifier)
                MainMenuType.MENU_PROJECT -> EmptyComingSoon(MainMenuType.MENU_PROJECT.name, Modifier.padding(it))
                MainMenuType.MENU_ALARM -> EmptyComingSoon(MainMenuType.MENU_ALARM.name, Modifier.padding(it))
                MainMenuType.MENU_PROFILE -> EmptyComingSoon(MainMenuType.MENU_PROFILE.name, Modifier.padding(it))
                else -> {
                    DevLog.e("is call here")
                    EmptyComingSoon(MainMenuType.MENU_HOME.name, Modifier.padding(it))
                }
            }
        },
        bottomBar = {

            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                MAIN_DESTINATIONS.forEach { mainDestination ->
                    NavigationBarItem(
                        selected = selectedDestination == mainDestination.route,
                        onClick = {
                            if (selectedDestination != mainDestination.route)
                                onNavigateMainScreen.invoke(mainDestination)
                        },
                        icon = {
                            Icon(
                                imageVector = mainDestination.selectedIcon,
                                contentDescription = stringResource(id = mainDestination.iconTextId)
                            )
                        }
                    )
                }
            }
        }
    )
}


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

@Preview(showBackground = true)
@Composable
private fun MainUiPreview() {
    ButterflyTheme {
        MainUi(
            MainMenuType.MENU_HOME,
            onNavigateMainScreen = {}
        )
    }
}