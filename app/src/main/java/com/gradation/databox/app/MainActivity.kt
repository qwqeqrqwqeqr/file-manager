package com.gradation.databox.app

import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.gradation.databox.core.common.event.EventManager
import com.gradation.databox.core.designsystem.theme.DataBoxTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var eventManager: EventManager

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()

        var isExternalStorageManager: Boolean by mutableStateOf(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) Environment.isExternalStorageManager() else false)

        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    isExternalStorageManager = Environment.isExternalStorageManager()
                }
            }
        }


        setContent {
            DataBoxTheme {
                DataboxApp(
                    modifier = Modifier,
                    appState = rememberAppState(eventManager = eventManager),
                    isExternalStorageManager,
                )
            }
        }
    }
}


