package com.gradation.databox.app

import android.app.Activity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.navigation.Route
import com.gradation.databox.core.utils.permissionList
import kotlinx.coroutines.CoroutineScope

class AppState @OptIn(ExperimentalPermissionsApi::class) constructor(
    val navController: NavHostController,
    val currentDestination: NavDestination?,
    val snackbarHostState: SnackbarHostState,
    val multiplePermissionsState: MultiplePermissionsState,
    val coroutineScope: CoroutineScope,
) {

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    multiplePermissionsState: MultiplePermissionsState = rememberMultiplePermissionsState(
        permissions = permissionList
    ),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState {
    val currentDestination: NavDestination? =
        navController.currentBackStackEntryAsState().value?.destination
    val view = LocalView.current
    val window = (view.context as Activity).window


    val color = when (currentDestination?.route) {
        Route.HOME_ROUTE -> DataboxTheme.colorScheme.mainBackgroundColor
        else -> DataboxTheme.colorScheme.backgroundColor
    }

    LaunchedEffect(currentDestination) {
        if (!view.isInEditMode) {
            window.statusBarColor = color.toArgb()
            window.navigationBarColor = color.toArgb()
        }
    }

    return remember(
        navController,
        currentDestination,
        snackbarHostState,
        multiplePermissionsState,
        coroutineScope
    ) {
        AppState(
            navController,
            currentDestination,
            snackbarHostState,
            multiplePermissionsState,
            coroutineScope
        )
    }
}
