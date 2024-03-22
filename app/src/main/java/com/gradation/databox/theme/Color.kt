package com.gradation.databox.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color

val PRIMARY_COLOR = Color(0xFF0099FF)
val PRIMARY_COLOR2 = Color(0xFF2BAAFF)
val SECONDARY_COLOR = Color(0xFFFF99FF)

val BUTTON_COLOR_BRUSH: Brush = linearGradient(colors = listOf(PRIMARY_COLOR, PRIMARY_COLOR2))


class DataboxColorScheme(
    primaryColor: Color,
    secondaryColor: Color,
    mainBackgroundColor: Color,
    mainSurfaceColor: Color,
    backgroundColor: Color,
    surfaceColor: Color,
    primaryTextColor: Color,
    secondaryTextColor: Color,
    tertiaryTextColor: Color,
    iconColor: Color,
    dividerColor: Color,
    buttonColorBrush: Brush,
    buttonTextColor: Color,
    defaultButtonColor: Color,
    defaultButtonTextColor: Color
) {
    var primaryColor: Color by mutableStateOf(primaryColor)
        private set
    var secondaryColor: Color by mutableStateOf(secondaryColor)
        private set
    var mainBackgroundColor: Color by mutableStateOf(mainBackgroundColor)
        private set
    var mainSurfaceColor: Color by mutableStateOf(mainSurfaceColor)
        private set
    var backgroundColor: Color by mutableStateOf(backgroundColor)
        private set
    var surfaceColor: Color by mutableStateOf(surfaceColor)
        private set
    var primaryTextColor: Color by mutableStateOf(primaryTextColor)
        private set
    var secondaryTextColor: Color by mutableStateOf(secondaryTextColor)
        private set
    var tertiaryTextColor: Color by mutableStateOf(tertiaryTextColor)
        private set
    var iconColor: Color by mutableStateOf(iconColor)
        private set
    var dividerColor: Color by mutableStateOf(dividerColor)
        private set
    var buttonColorBrush: Brush by mutableStateOf(buttonColorBrush)
        private set
    var buttonTextColor: Color by mutableStateOf(buttonTextColor)
        private set
    var defaultButtonColor: Color by mutableStateOf(defaultButtonColor)
        private set
    var defaultButtonTextColor: Color by mutableStateOf(defaultButtonTextColor)
        private set

    fun copy(
        primaryColor: Color = this.primaryColor,
        secondaryColor: Color = this.secondaryColor,
        mainBackgroundColor: Color = this.mainBackgroundColor,
        mainSurfaceColor: Color = this.mainSurfaceColor,
        backgroundColor: Color = this.backgroundColor,
        surfaceColor: Color = this.surfaceColor,
        primaryTextColor: Color = this.primaryTextColor,
        secondaryTextColor: Color = this.secondaryTextColor,
        tertiaryTextColor: Color = this.tertiaryTextColor,
        iconColor: Color = this.iconColor,
        dividerColor: Color = this.dividerColor,
        buttonColorBrush: Brush = this.buttonColorBrush,
        buttonTextColor: Color = this.buttonTextColor,
        defaultButtonColor: Color = this.defaultButtonColor,
        defaultButtonTextColor: Color = this.defaultButtonTextColor
    ): DataboxColorScheme = DataboxColorScheme(
        primaryColor,
        secondaryColor,
        mainBackgroundColor,
        mainSurfaceColor,
        backgroundColor,
        surfaceColor,
        primaryTextColor,
        secondaryTextColor,
        tertiaryTextColor,
        iconColor,
        dividerColor,
        buttonColorBrush,
        buttonTextColor,
        defaultButtonColor,
        defaultButtonTextColor
    )

    fun updateColorsFrom(other: DataboxColorScheme) {
        primaryColor = other.primaryColor
        secondaryColor = other.secondaryColor
        mainBackgroundColor = other.mainBackgroundColor
        mainSurfaceColor = other.mainSurfaceColor
        backgroundColor = other.backgroundColor
        surfaceColor = other.surfaceColor
        primaryTextColor = other.primaryTextColor
        secondaryTextColor = other.secondaryTextColor
        tertiaryTextColor = other.tertiaryTextColor
        iconColor = other.iconColor
        dividerColor = other.dividerColor
        buttonColorBrush = other.buttonColorBrush
        buttonTextColor = other.buttonTextColor
        defaultButtonColor = other.defaultButtonColor
        defaultButtonTextColor = other.defaultButtonTextColor
    }

}

val databoxLightColorScheme: DataboxColorScheme by lazy {
    DataboxColorScheme(
        primaryColor = PRIMARY_COLOR,
        secondaryColor = SECONDARY_COLOR,
        mainBackgroundColor = Color(0xffF1F1F1),
        mainSurfaceColor = Color(0xffFAFDFD),
        backgroundColor = Color(0xFFFAFDFD),
        surfaceColor = Color(0xFFF1F1F1),
        primaryTextColor = Color(0xFF222222),
        secondaryTextColor = Color(0xFF787878),
        tertiaryTextColor = Color(0xFFC0C0C0),
        iconColor = Color(0xFFB5B5B5),
        dividerColor = Color(0xFFD2D2D2),
        buttonColorBrush = BUTTON_COLOR_BRUSH,
        buttonTextColor = Color(0xFFFFFFFF),
        defaultButtonColor = Color(0xFFF0F0F0),
        defaultButtonTextColor = Color(0xFF787878),
    )
}

val databoxDarkColorScheme: DataboxColorScheme by lazy {
    DataboxColorScheme(
        primaryColor = PRIMARY_COLOR,
        secondaryColor = SECONDARY_COLOR,
        mainBackgroundColor = Color(0xFF1C1C1C),
        mainSurfaceColor = Color(0xFF282828),
        backgroundColor = Color(0xFF282828),
        surfaceColor = Color(0xFF5F5F5F),
        primaryTextColor = Color(0xFFFCFCFC),
        secondaryTextColor = Color(0xFFB8B8B8),
        tertiaryTextColor = Color(0xFF7D7D7D),
        iconColor = Color(0xFF6E6E6E),
        dividerColor = Color(0xFF5C5C5C),
        buttonColorBrush = BUTTON_COLOR_BRUSH,
        buttonTextColor = Color(0xFFFFFFFF),
        defaultButtonColor = Color(0xFF3D3D3D),
        defaultButtonTextColor = Color(0xFFB8B8B8),
    )
}
