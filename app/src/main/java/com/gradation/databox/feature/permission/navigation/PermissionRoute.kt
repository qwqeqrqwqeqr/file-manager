package com.gradation.databox.feature.permission.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.isGranted
import com.gradation.databox.core.ui.compose.LocalSnackbarHostState
import com.gradation.databox.feature.permission.data.model.Permission
import com.gradation.databox.feature.permission.data.model.toPermission
import com.gradation.databox.feature.permission.ui.PermissionScreen


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRoute(
    modifier: Modifier = Modifier,
    multiplePermissionsState: MultiplePermissionsState
) {
    val snackbarHostState = LocalSnackbarHostState.current
    val context = LocalContext.current
    val permissionList: List<Permission> = multiplePermissionsState.permissions.map {
        Permission(
            it.permission.toPermission(),
            it.status.isGranted
        )
    }


    val launchMultiplePermissionRequest: () -> Unit =
        { multiplePermissionsState.launchMultiplePermissionRequest() }

    PermissionScreen(modifier,context, permissionList, launchMultiplePermissionRequest)


    LaunchedEffect(multiplePermissionsState.shouldShowRationale) {
        if (multiplePermissionsState.shouldShowRationale) {
            snackbarHostState.showSnackbar("권한을 허용해야 앱을 사용할 수 있습니다.")
        }
    }
}



