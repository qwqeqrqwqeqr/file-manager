package com.gradation.databox.core.designsystem.component.selector

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataBoxTheme
import com.gradation.databox.core.designsystem.theme.DataboxTheme

@Composable
fun DataboxTextSelector(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = DataboxTheme.space.space12,
        vertical = DataboxTheme.space.space8
    ),
    shape: RoundedCornerShape = RoundedCornerShape(DataboxTheme.space.space12),
) {
    val backgroundColor: Color by animateColorAsState(
        targetValue =
        if (selected) DataboxTheme.colorScheme.backgroundColor else
            DataboxTheme.colorScheme.surfaceColor, label = "backgroundColorTransition"
    )

    val borderColor: Color by animateColorAsState(
        targetValue = if (selected) DataboxTheme.colorScheme.primaryColor else
            Color.Transparent,
        label = "borderColorTransition",
    )

    val textColor: Color by animateColorAsState(
        targetValue = if (selected) DataboxTheme.colorScheme.primaryColor else
            DataboxTheme.colorScheme.secondaryTextColor,
        label = "borderColorTransition",
    )

    Box(
        modifier = modifier
            .border(1.5.dp, borderColor, shape)
            .background(backgroundColor, shape)
            .clip(shape)
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        DataboxText(
            textStyle = DataboxTextStyle.No6,
            text = text,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@PreviewLightDark
@Composable
fun DataboxTextSelectorPreview(modifier: Modifier = Modifier) {
    DataBoxTheme {
        Row(
            modifier = modifier
                .background(DataboxTheme.colorScheme.backgroundColor)
                .padding(DataboxTheme.space.space20),
            horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
        ) {
            DataboxTextSelector(
                modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = "안녕",
                selected = true
            )


            DataboxTextSelector(
                modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = "false",
                selected = false
            )
        }
    }
}