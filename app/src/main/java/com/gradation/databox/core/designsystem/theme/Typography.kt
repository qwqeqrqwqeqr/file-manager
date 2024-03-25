package com.gradation.databox.core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gradation.databox.R



private val pretendard = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.pretendard_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.pretendard_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.pretendard_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.pretendard_extra_bold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.pretendard_extra_light, FontWeight.ExtraLight, FontStyle.Normal),
)

internal val TEXT_NO0 = TextStyle(
    fontSize = 26.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = pretendard
)

internal val TEXT_NO1 = TextStyle(
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = pretendard
)
internal val TEXT_NO2 = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = pretendard
)
internal val TEXT_NO3 = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = pretendard
)
internal val TEXT_NO4 = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = pretendard
)
internal val TEXT_NO5 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = pretendard
)
internal val TEXT_NO6 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = pretendard
)
internal val TEXT_NO7 = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = pretendard
)
internal val TEXT_NO8 = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = pretendard
)
internal val TEXT_NO9 = TextStyle(
    fontSize = 10.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = pretendard
)
internal val TEXT_NO10 = TextStyle(
    fontSize = 10.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = pretendard
)

data class DataboxTypography(
    val no0: TextStyle = TEXT_NO0,
    val no1: TextStyle = TEXT_NO1,
    val no2: TextStyle= TEXT_NO2,
    val no3: TextStyle= TEXT_NO3,
    val no4: TextStyle= TEXT_NO4,
    val no5: TextStyle= TEXT_NO5,
    val no6: TextStyle= TEXT_NO6,
    val no7: TextStyle= TEXT_NO7,
    val no8: TextStyle= TEXT_NO8,
    val no9: TextStyle= TEXT_NO9,
    val no10: TextStyle= TEXT_NO10,
)


