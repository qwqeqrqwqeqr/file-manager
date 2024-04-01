package com.gradation.databox.app

import android.os.Build
import android.os.Environment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.gradation.databox.core.designsystem.component.snackbar.DataboxSnackBar
import com.gradation.databox.core.ui.compose.LocalSnackbarHostState
import com.gradation.databox.core.ui.navigation.Route.HOME_ROUTE
import com.gradation.databox.core.ui.navigation.Route.PERMISSION_ROUTE


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DataboxApp(
    modifier: Modifier = Modifier,
    appState: AppState,
    isExternalStorageManager: Boolean,
) {
    CompositionLocalProvider(
        LocalSnackbarHostState provides appState.snackbarHostState,
    ) {
        Scaffold(
            modifier = modifier,
            snackbarHost = {
                DataboxSnackBar(
                    modifier = modifier,
                    snackbarHostState = appState.snackbarHostState,
                )
            }
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                DataboxNavHost(
                    modifier = modifier,
                    navController = appState.navController,
                    startDestination =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        if(Environment.isExternalStorageManager()) HOME_ROUTE
                        else PERMISSION_ROUTE
                    }else{
                        if (appState.multiplePermissionsState.allPermissionsGranted) HOME_ROUTE
                        else PERMISSION_ROUTE
                    },
                    multiplePermissionsState = appState.multiplePermissionsState,
                    isExternalStorageManager=isExternalStorageManager
                )
            }
        }
    }
}





