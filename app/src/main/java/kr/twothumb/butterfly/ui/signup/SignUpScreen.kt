@file:JvmName("SignUpViewModelKt")

package kr.twothumb.butterfly.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.twothumb.butterfly.R
import kr.twothumb.butterfly.common.SystemUi
import kr.twothumb.butterfly.ui.component.topbar.TopBar
import kr.twothumb.butterfly.ui.component.inputField
import kr.twothumb.butterfly.ui.theme.ButterflyTheme
import kr.twothumb.butterfly.ui.theme.caution
import kr.twothumb.butterfly.ui.theme.labelMedium
import kr.twothumb.butterfly.ui.theme.labelSmall

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    SystemUi()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignUpUi(uiState = uiState, onClickAction = {})
}

@Composable
fun SignUpUi(
    uiState: SignUpUiState = SignUpUiState(),
    modifier: Modifier = Modifier,
    onClickAction: (type: SignUpViewModel.OnSelected) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val blank = stringResource(id = R.string.blank)
    var text by remember { mutableStateOf(blank) }

    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar("title") {} },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            inputField(modifier,
                labelText = stringResource(id = R.string.email),
                hint = stringResource(id = R.string.email_example),
                text = stringResource(id = R.string.blank),
                onValueChange = { value -> text = value },
                labelUi = {
                    Text(text = stringResource(id = R.string.asterisk), style = MaterialTheme.typography.bodyMedium.labelMedium.caution)
                },
                /**
                 * 이메일 형식 체크
                 */
                bottomCaution = {

                }
            )

            inputField(
                modifier,
                labelText = stringResource(id = R.string.name),
                hint = stringResource(id = R.string.input_hint_name),
                text = stringResource(id = R.string.blank),
                onValueChange = { text = it },
                labelUi = {
                    Text(text = stringResource(id = R.string.input_option), style = MaterialTheme.typography.bodyMedium.labelSmall)
                },
            )

            inputField(
                modifier,
                labelText = stringResource(id = R.string.company),
                hint = stringResource(id = R.string.input_hint_company),
                text = stringResource(id = R.string.blank),
                onValueChange = { text = it },
                labelUi = {
                    Text(text = stringResource(id = R.string.input_option), style = MaterialTheme.typography.bodyMedium.labelSmall)
                },
            )

            inputField(
                modifier,
                labelText = stringResource(id = R.string.phone),
                hint = stringResource(id = R.string.input_hint_phone_number),
                text = stringResource(id = R.string.blank),
                onValueChange = { text = it },
                labelUi = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(text = stringResource(id = R.string.input_option), style = MaterialTheme.typography.bodyMedium.labelSmall)
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpUiPreview() {
    ButterflyTheme {
        SignUpUi(onClickAction = {})
    }
}
