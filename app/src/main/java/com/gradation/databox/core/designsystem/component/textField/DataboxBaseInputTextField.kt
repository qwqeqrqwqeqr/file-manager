package com.gradation.databox.core.designsystem.component.textField

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.gradation.databox.core.designsystem.component.surface.DataboxSurface
import com.gradation.databox.core.designsystem.component.surface.SurfaceType
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataBoxTheme
import com.gradation.databox.core.designsystem.theme.DataboxIcon
import com.gradation.databox.core.designsystem.theme.DataboxTheme


@Composable
internal fun DataboxTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolderValue: String = "",
    onValueChange: (String) -> Unit,
    onValueClear: (() -> Unit)? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    singleLine: Boolean = true,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = DataboxTheme.colorScheme.primaryTextColor,
        focusedContainerColor = Color.Transparent,
        focusedPlaceholderColor = DataboxTheme.colorScheme.primaryTextColor,
        cursorColor = DataboxTheme.colorScheme.primaryColor1,
        focusedIndicatorColor = DataboxTheme.colorScheme.primaryColor1,
        unfocusedTextColor = DataboxTheme.colorScheme.primaryTextColor,
        unfocusedLabelColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        unfocusedPlaceholderColor = DataboxTheme.colorScheme.primaryTextColor,
        unfocusedIndicatorColor = DataboxTheme.colorScheme.primaryColor1,
        selectionColors = TextSelectionColors(
            handleColor = DataboxTheme.colorScheme.primaryColor1,
            backgroundColor = DataboxTheme.colorScheme.primaryColor1.copy(alpha = 0.2f),
        )
    )
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = DataboxTheme.typography.no6,
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        placeholder = @Composable {
            DataboxText(
                modifier = modifier,
                textStyle = DataboxTextStyle.No6,
                text = placeHolderValue,
                color = DataboxTheme.colorScheme.secondaryTextColor,
                textAlign = TextAlign.Start
            )
        },
        colors = colors,
        shape = RoundedCornerShape(DataboxTheme.space.space12),
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        trailingIcon = @Composable {
            AnimatedVisibility(
                visible = value.isNotEmpty() && onValueClear != null,
                enter = fadeIn(tween(500)),
                exit = fadeOut(tween(500)),

            ) {
                Icon(
                    modifier = modifier
                        .size(DataboxTheme.space.space24)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { onValueClear?.invoke() },
                        ),
                    imageVector = ImageVector.vectorResource(DataboxIcon.Clear),
                    contentDescription = "Cancel",
                    tint = Color.Unspecified
                )
            }
        }
    )

}

@PreviewLightDark
@Composable
fun DataboxTextFieldPreview(modifier: Modifier = Modifier) {
    DataBoxTheme {
        Column(
            modifier = modifier
                .background(DataboxTheme.colorScheme.backgroundColor)
                .padding(DataboxTheme.space.space20),
            verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
        ) {
            DataboxTextField(
                value = "텍스트 필드",
                onValueClear = {},
                onValueChange = {},
            )
        }
    }
}