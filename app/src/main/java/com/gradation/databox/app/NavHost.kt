package com.gradation.databox.app

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.gradation.databox.feature.directory.naivgation.directoryScreen
import com.gradation.databox.feature.home.navigation.navigation.homeScreen
import com.gradation.databox.feature.permission.navigation.permissionScreen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DataboxNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
    multiplePermissionsState: MultiplePermissionsState,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
    ) {
        homeScreen(modifier, navController)
        directoryScreen(modifier,navController)
        permissionScreen(modifier,multiplePermissionsState)
    }
}