package com.gradation.databox.feature.permission.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.gradation.databox.core.ui.navigation.Route


@OptIn(ExperimentalPermissionsApi::class)
fun NavGraphBuilder.permissionScreen(
    modifier: Modifier = Modifier,
    multiplePermissionsState: MultiplePermissionsState
) {
    composable(Route.PERMISSION_ROUTE) {
        PermissionRoute(
            modifier,
            multiplePermissionsState
        )
    }
}