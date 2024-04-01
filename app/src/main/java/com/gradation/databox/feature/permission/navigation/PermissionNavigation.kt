package com.gradation.databox.feature.permission.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.gradation.databox.core.ui.navigation.Route
import com.gradation.databox.core.ui.navigation.navigatePermissionToHome


@OptIn(ExperimentalPermissionsApi::class)
fun NavGraphBuilder.permissionScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    multiplePermissionsState: MultiplePermissionsState,
    isExternalStorageManager: Boolean,
) {

    val navigateToHome:()-> Unit ={navController.navigatePermissionToHome() }

    composable(Route.PERMISSION_ROUTE) {
        PermissionRoute(
            modifier,
            navigateToHome,
            multiplePermissionsState,
            isExternalStorageManager
        )
    }
}