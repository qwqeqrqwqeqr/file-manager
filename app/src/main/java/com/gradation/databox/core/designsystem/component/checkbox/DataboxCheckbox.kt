package com.gradation.databox.core.designsystem.component.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import com.gradation.databox.core.designsystem.theme.DataBoxTheme
import com.gradation.databox.core.designsystem.theme.DataboxTheme

@Composable
fun DataboxCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    size: Dp = DataboxTheme.space.space24,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .size(size)
            .toggleable(
                value = checked,
                interactionSource = interactionSource,
                onValueChange = onCheckedChange,
                role = null,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = modifier.fillMaxSize(),
            imageVector = if (checked) Icons.Filled.CheckCircle else Icons.Outlined.Circle,
            contentDescription = "",
            tint = DataboxTheme.colorScheme.primaryIconColor,
        )
    }
}

@PreviewLightDark
@Composable
fun DataboxDataboxCheckboxPreview(modifier: Modifier = Modifier) {
    DataBoxTheme {
        Row(
            modifier = modifier
                .background(DataboxTheme.colorScheme.backgroundColor)
                .padding(DataboxTheme.space.space20),
            horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
        ) {
            DataboxCheckbox(
                modifier,
                checked = true,
                onCheckedChange = {}
            )


            DataboxCheckbox(
                modifier,
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}