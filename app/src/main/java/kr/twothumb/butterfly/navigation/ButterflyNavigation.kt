/*
 * Copyright 2022 The Android Open Source Project
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

package kr.twothumb.butterfly.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kr.twothumb.butterfly.navigation.ButterflyDestinations.MAIN_HOME_ROUTE
import kr.twothumb.butterfly.navigation.ButterflyDestinations.SIGN_UP_ROUTE
import kr.twothumb.butterfly.navigation.ButterflyScreens.MAIN_SCREEN
import kr.twothumb.butterfly.navigation.ButterflyScreens.SIGN_UP_SCREEN
import kr.twothumb.butterfly.navigation.ButterflyScreens.SPLASH_SCREEN
import kr.twothumb.butterfly.navigation.DestinationsArgs.MAIN_MENU
import kr.twothumb.butterfly.navigation.DestinationsArgs.MENU_ALARM
import kr.twothumb.butterfly.navigation.DestinationsArgs.MENU_HOME
import kr.twothumb.butterfly.navigation.DestinationsArgs.MENU_PROFILE
import kr.twothumb.butterfly.navigation.DestinationsArgs.MENU_PROJECT
import kr.twothumb.lib.logger.DevLog

//import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.TASK_ID_ARG
//import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.TITLE_ARG
//import com.example.android.architecture.blueprints.todoapp.TodoDestinationsArgs.USER_MESSAGE_ARG

/**
 * Screens used in [ButterflyDestinations]
 */
private object ButterflyScreens {
    const val SPLASH_SCREEN = "SCREEN_SPLASH"
    const val SIGN_UP_SCREEN = "SCREEN_SIGN_UP"
    const val MAIN_SCREEN = "SCREEN_MAIN"
    const val TASKS_SCREEN = "tasks"
    const val STATISTICS_SCREEN = "statistics"
    const val TASK_DETAIL_SCREEN = "task"
    const val ADD_EDIT_TASK_SCREEN = "addEditTask"
}

/**
 * Arguments used in [TodoDestinations] routes
 */
object DestinationsArgs {
    const val MAIN_MENU = "MAIN_MENU"
    const val MENU_HOME = "MENU_HOME"
    const val MENU_PROJECT = "MENU_PROJECT"
    const val MENU_ALARM = "MENU_ALARM"
    const val MENU_PROFILE = "MENU_PROFILE"
    const val USER_MESSAGE_ARG = "userMessage"
    const val TASK_ID_ARG = "taskId"
    const val TITLE_ARG = "title"
}

object ButterflyDestinations {
    const val SPLASH_ROUTE = SPLASH_SCREEN
    const val SIGN_UP_ROUTE = SIGN_UP_SCREEN
    const val MAIN_GRAPH_ROUTE = "$MAIN_SCREEN/{$MAIN_MENU}"

    const val MAIN_HOME_ROUTE = "$MAIN_SCREEN/$MENU_HOME"
    const val MAIN_PROJECT_ROUTE = "$MAIN_SCREEN/$MENU_PROJECT"
    const val MAIN_ALARM_ROUTE = "$MAIN_SCREEN/$MENU_ALARM"
    const val MAIN_PROFILE_ROUTE = "$MAIN_SCREEN/$MENU_PROFILE"

//    const val TASKS_ROUTE = "$TASKS_SCREEN?$USER_MESSAGE_ARG={$USER_MESSAGE_ARG}"
//    const val STATISTICS_ROUTE = STATISTICS_SCREEN
//    const val TASK_DETAIL_ROUTE = "$TASK_DETAIL_SCREEN/{$TASK_ID_ARG}"
//    const val ADD_EDIT_TASK_ROUTE = "$ADD_EDIT_TASK_SCREEN/{$TITLE_ARG}?$TASK_ID_ARG={$TASK_ID_ARG}"
}

class NavigationActions(private val navController: NavHostController) {

    fun navigateToMain() {
        DevLog.e("navigateToMain")
        navController.navigate(MAIN_HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    fun navigateToSignUp() {
        navController.navigate(SIGN_UP_ROUTE)
    }


    fun navigateToMain(destination: MainNavigation) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }

    fun navigateToTasks(userMessage: Int = 0) {
//        val navigatesFromDrawer = userMessage == 0
//        navController.navigate(
//            TASKS_SCREEN.let {
//                if (userMessage != 0) "$it?$USER_MESSAGE_ARG=$userMessage" else it
//            }
//        ) {
//            popUpTo(navController.graph.findStartDestination().id) {
//                inclusive = !navigatesFromDrawer
//                saveState = navigatesFromDrawer
//            }
//            launchSingleTop = true
//            restoreState = navigatesFromDrawer
//        }
    }

    fun navigateToStatistics() {
//        navController.navigate(TodoDestinations.STATISTICS_ROUTE) {
//            // Pop up to the start destination of the graph to
//            // avoid building up a large stack of destinations
//            // on the back stack as users select items
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = true
//            }
//            // Avoid multiple copies of the same destination when
//            // reselecting the same item
//            launchSingleTop = true
//            // Restore state when reselecting a previously selected item
//            restoreState = true
//        }
    }

    fun navigateToTaskDetail(taskId: String) {
//        navController.navigate("$TASK_DETAIL_SCREEN/$taskId")
    }

    fun navigateToAddEditTask(title: Int, taskId: String?) {
//        navController.navigate(
//            "$ADD_EDIT_TASK_SCREEN/$title".let {
//                if (taskId != null) "$it?$TASK_ID_ARG=$taskId" else it
//            }
//        )
    }
}
