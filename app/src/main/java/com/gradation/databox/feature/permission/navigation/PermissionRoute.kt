package com.gradation.databox.feature.permission.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.isGranted
import com.gradation.databox.feature.permission.data.model.Permission
import com.gradation.databox.feature.permission.data.model.toPermission
import com.gradation.databox.feature.permission.ui.PermissionScreen


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRoute(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    multiplePermissionsState: MultiplePermissionsState,
    isExternalStorageManager: Boolean
) {
    val context = LocalContext.current


    val permissionList: List<Permission> = multiplePermissionsState.permissions.map {
        Permission(
            name = it.permission.toPermission(),
            isGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) isExternalStorageManager else it.status.isGranted
        )
    }


    val launchMultiplePermissionRequest: () -> Unit =
        { multiplePermissionsState.launchMultiplePermissionRequest() }


    LaunchedEffect(isExternalStorageManager){
        if (permissionList.none { !it.isGranted }) navigateToHome()
    }


    PermissionScreen(
        modifier,
        context,
        permissionList,
        launchMultiplePermissionRequest,
    )
}



