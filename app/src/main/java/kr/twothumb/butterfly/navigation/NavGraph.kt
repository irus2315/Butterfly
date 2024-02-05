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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kr.twothumb.butterfly.ui.main.MainScreen
import kr.twothumb.butterfly.ui.signup.SignUpScreen
import kr.twothumb.butterfly.ui.splash.SplashScreen
import kr.twothumb.lib.logger.DevLog

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ButterflyDestinations.SPLASH_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {
        composable(ButterflyDestinations.SPLASH_ROUTE) {
            SplashScreen(onNavigateMainScreen = {
                DevLog.i("onNavigateMainScreen")
                navActions.navigateToMain()
            }, onNavigateSignInScreen = {
                DevLog.i("onNavigateSignInScreen")
                navActions.navigateToMain()
            }, onNavigateSignUpScreen = {
                DevLog.i("onNavigateSignUpScreen")
                navActions.navigateToSignUp()
            })
        }
        composable(ButterflyDestinations.SIGN_UP_ROUTE) {
            SignUpScreen(
            )
//            }
        }
        composable(
            ButterflyDestinations.MAIN_GRAPH_ROUTE
            , arguments = listOf(
                navArgument(DestinationsArgs.MAIN_MENU) {
                    type = NavType.StringType; defaultValue = DestinationsArgs.MENU_HOME
                },
            )
        ) {
            val mainMenu: MainMenuType = it.arguments?.getString(DestinationsArgs.MAIN_MENU)?.run {
                MainMenuType.valueOf(this)
            } ?: run {
                MainMenuType.MENU_HOME
            }
            MainScreen(mainMenu, onNavigateMainScreen = { mainNavigation ->
                /**
                 * todo 현재 화면이면 리턴
                 */
                DevLog.e("mainMenu : " + mainMenu, " : " + mainNavigation)
                navActions.navigateToMain(mainNavigation)
            })
        }
    }
}