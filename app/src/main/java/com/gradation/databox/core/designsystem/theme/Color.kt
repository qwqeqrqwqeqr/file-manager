package com.gradation.databox.core.designsystem.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color

val PRIMARY_COLOR1 = Color(0xFF0099FF)
val PRIMARY_COLOR2 = Color(0xFF98D6FF)



val PRIMARY_COLOR3 = Color(0xFF2BAAFF)
val PRIMARY_COLOR4 = Color(0xFF8FD2FF)




class DataboxColorScheme(
    primaryColor1: Color,
    primaryColor2: Color,
    mainBackgroundColor: Color,
    mainSurfaceColor: Color,
    backgroundColor: Color,
    surfaceColor: Color,
    primaryTextColor: Color,
    secondaryTextColor: Color,
    tertiaryTextColor: Color,
    primaryIconColor: Color,
    secondaryIconColor:Color,
    dividerColor: Color,
    buttonTextColor: Color,
    defaultButtonColor: Color,
    defaultButtonTextColor: Color,
    snackbarColor: Color,
    snackbarTextColor: Color,
) {
    var primaryColor1: Color by mutableStateOf(primaryColor1)
        private set
    var primaryColor2: Color by mutableStateOf(primaryColor2)
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
    var primaryIconColor: Color by mutableStateOf(primaryIconColor)
        private set
    var secondaryIconColor: Color by mutableStateOf(secondaryIconColor)
        private set
    var dividerColor: Color by mutableStateOf(dividerColor)
        private set
    var buttonTextColor: Color by mutableStateOf(buttonTextColor)
        private set
    var defaultButtonColor: Color by mutableStateOf(defaultButtonColor)
        private set
    var defaultButtonTextColor: Color by mutableStateOf(defaultButtonTextColor)
        private set
    var snackbarColor:Color by mutableStateOf(snackbarColor)
        private set
    var snackbarTextColor:Color by mutableStateOf(snackbarTextColor)
        private set




}

val databoxLightColorScheme: DataboxColorScheme by lazy {
    DataboxColorScheme(
        primaryColor1 = PRIMARY_COLOR1,
        primaryColor2 = PRIMARY_COLOR2,
        mainBackgroundColor = Color(0xFFEFF0F3),
        mainSurfaceColor = Color(0xFFFFFFFF),
        backgroundColor = Color(0xFFFFFFFF),
        surfaceColor = Color(0xFFF3F4F6),
        primaryTextColor = Color(0xFF353E4D),
        secondaryTextColor = Color(0xFF787878),
        tertiaryTextColor = Color(0xFFACACAC),
        primaryIconColor = Color(0xFFB0B9C2 ),
        secondaryIconColor= Color(0xFF353E4D),
        dividerColor = Color(0xFFD2D2D2),
        buttonTextColor = Color(0xFFFFFFFF),
        defaultButtonColor = Color(0xFFE8E9ED),
        defaultButtonTextColor = Color(0xFF56626F),
        snackbarColor = Color(0xEE8E98A2),
        snackbarTextColor = Color(0xFFFCFCFC),
    )
}

val databoxDarkColorScheme: DataboxColorScheme by lazy {
    DataboxColorScheme(
        primaryColor1 = PRIMARY_COLOR1,
        primaryColor2 = PRIMARY_COLOR2,
        mainBackgroundColor = Color(0xFF1C1C1C),
        mainSurfaceColor = Color(0xFF282828),
        backgroundColor = Color(0xFF282828),
        surfaceColor = Color(0xFF5F5F5F),
        primaryTextColor = Color(0xFFFCFCFC),
        secondaryTextColor = Color(0xFFB8B8B8),
        tertiaryTextColor = Color(0xFF7D7D7D),
        primaryIconColor = Color(0xFF6E6E6E),
        secondaryIconColor= Color(0xFFFCFCFC),
        dividerColor = Color(0xFF5C5C5C),
        buttonTextColor = Color(0xFFFFFFFF),
        defaultButtonColor = Color(0xFF3D3D3D),
        defaultButtonTextColor = Color(0xFFC5C4C4),
        snackbarColor = Color(0xEE8E98A2),
        snackbarTextColor = Color(0xFFFCFCFC),
    )
}
