package com.gradation.databox.core.designsystem.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataBoxTheme
import com.gradation.databox.core.designsystem.theme.DataboxTheme


@Composable
fun DataboxDefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val hapticFeedback: HapticFeedback = LocalHapticFeedback.current
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val backgroundColor: Color by animateColorAsState(
        if (!enabled) DataboxTheme.colorScheme.defaultButtonColor
        else if (isPressed) DataboxTheme.colorScheme.defaultButtonColor
        else DataboxTheme.colorScheme.defaultButtonColor,
        label = "contentColorTransition"
    )

    val textColor: Color by animateColorAsState(
        if (!enabled) DataboxTheme.colorScheme.defaultButtonTextColor
        else if (isPressed) DataboxTheme.colorScheme.defaultButtonTextColor
        else DataboxTheme.colorScheme.defaultButtonTextColor,
        label = "textColorTransition"
    )


    Row(
        modifier = modifier
            .height(DataboxTheme.space.space52)
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(size = DataboxTheme.space.space12))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onClick()
                },
                enabled = enabled
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        DataboxText(
            modifier = modifier,
            textStyle = DataboxTextStyle.No3,
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,

            )
    }
}

@PreviewLightDark
@Composable
fun DataboxButtonPreview(modifier: Modifier = Modifier) {
    DataBoxTheme {
        Column(
            modifier=modifier,
            verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
        ) {
            Column(
                modifier = modifier
                    .background(DataboxTheme.colorScheme.mainBackgroundColor)
                    .padding(DataboxTheme.space.space20)
            ) {
                DataboxDefaultButton(
                    modifier = modifier,
                    text="Click",
                    enabled = true,
                    onClick = {}
                )
            }
            Column(
                modifier = modifier
                    .background(DataboxTheme.colorScheme.backgroundColor)
                    .padding(DataboxTheme.space.space20)
            ) {
                DataboxDefaultButton(
                    modifier = modifier,
                    text="Click",
                    enabled = true,
                    onClick = {}
                )
            }
        }
    }
}