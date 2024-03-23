package com.gradation.databox.core.designsystem.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataBoxTheme
import com.gradation.databox.core.designsystem.theme.DataboxTheme

@Composable
fun DataboxSnackBar(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
) {
    SnackbarHost(
        modifier = modifier.padding(DataboxTheme.space.space12),
        hostState = snackbarHostState,
        snackbar = { data ->
            SnackbarComponent(
                modifier = modifier,
                message = data.visuals.message,
            )
        }
    )
}


@Composable
fun SnackbarComponent(
    modifier: Modifier = Modifier,
    message: String,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(
                DataboxTheme.colorScheme.snackbarColor,
                RoundedCornerShape(DataboxTheme.space.space6)
            )
            .padding(
                horizontal = DataboxTheme.space.space16,
                vertical = DataboxTheme.space.space12
            )

    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space8)
        ) {
            DataboxText(
                textStyle = DataboxTextStyle.No5,
                text = message,
                color = DataboxTheme.colorScheme.snackbarTextColor,
                textAlign = TextAlign.Start
            )
        }
    }
}


@PreviewLightDark
@Composable
fun DataboxSnackbarComponentPreview(modifier: Modifier = Modifier) {
    DataBoxTheme {
        Column(
            modifier = modifier
                .background(DataboxTheme.colorScheme.mainBackgroundColor)
                .padding(DataboxTheme.space.space20),
            verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
        ) {
            SnackbarComponent(modifier, message = "snack bar message")
        }
    }
}