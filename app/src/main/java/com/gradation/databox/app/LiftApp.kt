package com.gradation.databox.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.databox.theme.DataboxTheme


@Composable
fun LiftApp(
    modifier: Modifier = Modifier,
    appState: AppState,
) {
    Scaffold(
        modifier=modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(DataboxTheme.colorScheme.backgroundColor)
                .padding(it)
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = DataboxTheme.colorScheme.defaultButtonColor,
                    contentColor = DataboxTheme.colorScheme.defaultButtonTextColor
                ),
            ) {
                Text(
                    text = "dsadasda",
                )
            }
        }
    }
}



