package com.gradation.databox.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


@Composable
fun DataBoxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {


    val databoxTypography = DataboxTypography()
    val databoxSpace = DataboxSpace()
    val currentColor: DataboxColorScheme = remember { if (darkTheme) databoxDarkColorScheme else databoxLightColorScheme }


    CompositionLocalProvider(
        LocalDataboxColorScheme provides currentColor,
        LocalDataboxTypography provides databoxTypography,
        LocalDataboxSpace provides databoxSpace
    ) {
        ProvideTextStyle(databoxTypography.no1, content = content)
    }
}


object DataboxTheme {
    val colorScheme: DataboxColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalDataboxColorScheme.current

    val typography: DataboxTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalDataboxTypography.current


    val space: DataboxSpace
        @Composable
        @ReadOnlyComposable
        get() = LocalDataboxSpace.current
}

val LocalDataboxColorScheme = staticCompositionLocalOf { databoxLightColorScheme }
val LocalDataboxTypography = staticCompositionLocalOf { DataboxTypography() }
internal val LocalDataboxSpace = staticCompositionLocalOf { DataboxSpace() }
