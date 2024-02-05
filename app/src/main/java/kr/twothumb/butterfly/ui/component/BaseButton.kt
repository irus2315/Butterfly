package kr.twothumb.butterfly.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.twothumb.butterfly.R
import kr.twothumb.butterfly.ui.splash.SplashUi
import kr.twothumb.butterfly.ui.theme.ButterflyTheme
import kr.twothumb.butterfly.ui.theme.Spaces
import kr.twothumb.butterfly.ui.theme.buttonText

@Composable
fun BaseButton(
    text: String = stringResource(id = R.string.action_confirm),
    style: TextStyle = MaterialTheme.typography.titleMedium.buttonText,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier
        .background(
            color = MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(12.dp)
        )
        .clickable {
            onClickAction.invoke()
        }
) {
    Text(
        text = text,
        style = style,
        modifier = modifier
            .then(modifier.clickable {
                onClickAction.invoke()
            })
            .padding(Spaces.BASE_PADDING)
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@Preview(showBackground = true)
@Composable
private fun SignScreenPreview() {
    ButterflyTheme {
        BaseButton(onClickAction = {})
    }
}
