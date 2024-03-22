package com.gradation.databox.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.gradation.databox.core.designsystem.theme.DataBoxTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        setContent {
            DataBoxTheme {
                DataboxApp(
                    modifier = Modifier,
                    appState = rememberAppState(),
                )
            }
        }
    }
}

