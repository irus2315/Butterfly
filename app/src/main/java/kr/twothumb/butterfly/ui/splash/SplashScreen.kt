package kr.twothumb.butterfly.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kr.twothumb.butterfly.R
import kr.twothumb.butterfly.ui.component.BaseButton
import kr.twothumb.butterfly.common.SystemUi
import kr.twothumb.butterfly.models.local.LocalProjectDataProvider
import kr.twothumb.butterfly.ui.theme.ButterflyTheme
import kr.twothumb.butterfly.ui.theme.Spaces
import kr.twothumb.butterfly.ui.theme.baseStyle
import kr.twothumb.butterfly.ui.theme.init
import kr.twothumb.butterfly.ui.theme.labelTagInverse
import kr.twothumb.butterfly.ui.theme.textDp


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateSignInScreen: () -> Unit,
    onNavigateSignUpScreen: () -> Unit,
    onNavigateMainScreen: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    val systemColor = MaterialTheme.colorScheme.inverseOnSurface
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = systemColor
        )
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val baseUiState by viewModel.baseUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigateMainScreen) {
        if (uiState.navigateMainScreen) {
            onNavigateMainScreen()
            viewModel.onNavigatedScreen()
        }
    }

    LaunchedEffect(uiState.navigateSignInScreen) {
        if (uiState.navigateSignInScreen) {
            onNavigateSignInScreen()
            viewModel.onNavigatedScreen()
        }
    }

    LaunchedEffect(uiState.navigateSignUpScreen) {
        if (uiState.navigateSignUpScreen) {
            onNavigateSignUpScreen()
            viewModel.onNavigatedScreen()
        }
    }

    SplashUi(uiState, modifier, onClickAction = {
        when (it) {
            SplashViewModel.OnSelected.SignIn -> {
                viewModel.onSignIn()
            }

            SplashViewModel.OnSelected.SignInGuest -> {
                viewModel.onSignInAnonymously()
            }

            SplashViewModel.OnSelected.Test -> {
                viewModel.onTest()
            }

            SplashViewModel.OnSelected.CreateDummyProject -> {

                viewModel.onCreateDummyProject()
            }
        }
    })
}

@Composable
fun SplashUi(
    uiState: SplashUiState = SplashUiState(),
    modifier: Modifier = Modifier,
    onClickAction: (type: SplashViewModel.OnSelected) -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(Spaces.LONG_PADDING),

        content = {

            Spacer(modifier = Modifier.height(58.dp))
            Text(
                text = "Butterfly",
                style = MaterialTheme.typography.labelLarge.init(
                    fontSize = 48.textDp,
                    lineHeight = 56.textDp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    textAlign = TextAlign.Start
//                    lineHeight = 18.textDp,
                ),
                softWrap = true,
                modifier = Modifier
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.splash_title),
                style = MaterialTheme.typography.displayLarge.init(
                    fontSize = 32.textDp,
                    lineHeight = 42.textDp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    textAlign = TextAlign.Start
//                    lineHeight = 18.textDp,
                ),
                softWrap = true,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
            )
            Spacer(modifier = Modifier.height(58.dp))
            BaseButton(text = stringResource(id = R.string.sign_start), onClickAction = {
                onClickAction.invoke(SplashViewModel.OnSelected.SignIn)
            }, modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(12.dp)
                ))
            Spacer(modifier = Modifier.height(8.dp))
            BaseButton(
                text = stringResource(id = R.string.sign_in_guest),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = RoundedCornerShape(Spaces.ROUND_CORNER)
                    ),
                onClickAction = {
                    onClickAction.invoke(SplashViewModel.OnSelected.SignInGuest)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            BaseButton(
                text = "testButton",//,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = RoundedCornerShape(Spaces.ROUND_CORNER)
                    ),
                onClickAction = {
                    onClickAction.invoke(SplashViewModel.OnSelected.Test)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            BaseButton(
                text = "testButton11",//,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = RoundedCornerShape(Spaces.ROUND_CORNER)
                    ),
                onClickAction = {
                    onClickAction.invoke(SplashViewModel.OnSelected.CreateDummyProject)
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.copyright),
                style = MaterialTheme.typography.labelSmall.init(
                    fontSize = 14.textDp,
                    lineHeight = 16.textDp,
                    color = MaterialTheme.colorScheme.inversePrimary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),

                )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SignScreenPreview() {
    ButterflyTheme {
        SplashUi(onClickAction = {})
    }
}
