package com.gradation.databox.core.designsystem.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle


@Composable
fun DataboxMultiStyleText(
    modifier: Modifier = Modifier,
    defaultColor: Color,
    defaultTextStyle: DataboxTextStyle,
    textWithStyleList: List<TextWithStyle> = emptyList(),
    textAlign: TextAlign,
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            textWithStyleList.forEach { textWithStyle ->
                withStyle(
                    style = (textWithStyle.style?.toSpanStyle() ?: defaultTextStyle.toSpanStyle())
                        .copy(
                            color = textWithStyle.color ?: defaultColor
                        )
                ) {
                    append(textWithStyle.text)
                }
            }
        },
        color = defaultColor,
        style = defaultTextStyle.toStyle(),
        textAlign = textAlign,
    )
}



