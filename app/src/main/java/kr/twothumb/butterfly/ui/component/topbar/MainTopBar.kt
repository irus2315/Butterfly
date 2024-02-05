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

package kr.twothumb.butterfly.ui.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.twothumb.butterfly.R
import kr.twothumb.butterfly.ui.theme.Spaces
import kr.twothumb.butterfly.ui.theme.baseStyle
import kr.twothumb.butterfly.ui.theme.init
import kr.twothumb.butterfly.ui.theme.textDp

@Composable
fun MainTopBar(title: String, onMainClick: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(15.dp, 18.dp, 15.dp, 10.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .clickable {
                    onMainClick?.invoke()
                },
            style = MaterialTheme.typography.headlineSmall.init(
                fontSize = 16.textDp,
                lineHeight = 18.textDp,
                color = MaterialTheme.colorScheme.inversePrimary,
                letterSpacing = (-.5f).textDp
            ), text = "Butterfly"

        )
        if(title != stringResource(id = R.string.blank))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.baseStyle.init(
                    color = MaterialTheme.colorScheme.inversePrimary,
                ),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
            )
    }
}


@Preview
@Composable
private fun TasksTopAppBarPreview() {
    MainTopBar(stringResource(R.string.blank)) {}
}

