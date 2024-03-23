package com.gradation.databox.core.designsystem.component.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme


enum class DataboxTextStyle { No0,No1, No2, No3, No4, No5, No6, No7, No8 }



data class TextWithStyle(
    val text: String,
    val color: Color? = null,
    val style: DataboxTextStyle? = null,
)

@Composable
internal fun DataboxTextStyle.toStyle(): TextStyle = when (this) {
    DataboxTextStyle.No0 -> DataboxTheme.typography.no0
    DataboxTextStyle.No1 -> DataboxTheme.typography.no1
    DataboxTextStyle.No2 -> DataboxTheme.typography.no2
    DataboxTextStyle.No3 -> DataboxTheme.typography.no3
    DataboxTextStyle.No4 -> DataboxTheme.typography.no4
    DataboxTextStyle.No5 -> DataboxTheme.typography.no5
    DataboxTextStyle.No6 -> DataboxTheme.typography.no6
    DataboxTextStyle.No7 -> DataboxTheme.typography.no7
    DataboxTextStyle.No8 -> DataboxTheme.typography.no8
}


@Composable
internal fun DataboxTextStyle.toSpanStyle(): SpanStyle = when (this) {
    DataboxTextStyle.No0 -> DataboxTheme.typography.no0.toSpanStyle()
    DataboxTextStyle.No1 -> DataboxTheme.typography.no1.toSpanStyle()
    DataboxTextStyle.No2 -> DataboxTheme.typography.no2.toSpanStyle()
    DataboxTextStyle.No3 -> DataboxTheme.typography.no3.toSpanStyle()
    DataboxTextStyle.No4 -> DataboxTheme.typography.no4.toSpanStyle()
    DataboxTextStyle.No5 -> DataboxTheme.typography.no5.toSpanStyle()
    DataboxTextStyle.No6 -> DataboxTheme.typography.no6.toSpanStyle()
    DataboxTextStyle.No7 -> DataboxTheme.typography.no7.toSpanStyle()
    DataboxTextStyle.No8 -> DataboxTheme.typography.no8.toSpanStyle()
}

