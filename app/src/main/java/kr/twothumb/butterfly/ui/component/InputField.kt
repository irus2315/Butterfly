package kr.twothumb.butterfly.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.unit.dp
import kr.twothumb.butterfly.common.AnimationSpec.FocusDuration
import kr.twothumb.butterfly.common.AnimationSpec.FocusEasing
import kr.twothumb.butterfly.ui.theme.labelMedium
import kr.twothumb.butterfly.ui.theme.onSecondary

@Composable
fun inputField(
    modifier: Modifier = Modifier,
    labelUi: @Composable @UiComposable () -> Unit = {},
    bottomCaution: @Composable @UiComposable () -> Unit = {},
    labelText: String,
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val color by animateColorAsState(
        targetValue = if (isFocused) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.onSurface,
        animationSpec = tween(durationMillis = FocusDuration, easing = FocusEasing), label = ""
    )

    Row(modifier.fillMaxWidth()) {
        Text(style = MaterialTheme.typography.bodyLarge.labelMedium, text = labelText)
        Spacer(modifier = Modifier.width(4.dp))
        labelUi.invoke()
    }
    Spacer(modifier = Modifier.height(8.dp))
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium.labelMedium,
        interactionSource = interactionSource,
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp)
            .border(
                BorderStroke(1.dp, color),
                RoundedCornerShape(4.dp)
            ),
        decorationBox = { innerTextField ->
            Row(
                modifier
                    .fillMaxSize()
                    .padding(14.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier.fillMaxWidth()
                ) {
                    if (text.isEmpty()) Text(hint, style = MaterialTheme.typography.bodyMedium.labelMedium.onSecondary)
                    innerTextField()
                }
            }
        },
        singleLine = true,
    )
    bottomCaution.invoke()
    Spacer(modifier = Modifier.height(20.dp))
}