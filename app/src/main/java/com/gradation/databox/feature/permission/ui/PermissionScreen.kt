package com.gradation.databox.feature.permission.ui

import android.content.Context
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.gradation.databox.core.designsystem.component.button.DataboxDefaultButton
import com.gradation.databox.core.designsystem.component.surface.DataboxSurface
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataBoxTheme
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.utils.navigateSettingInfo
import com.gradation.databox.feature.permission.data.model.Permission
import com.gradation.databox.feature.permission.data.model.PermissionText

@Composable
fun PermissionScreen(
    modifier: Modifier,
    context: Context,
    permissionList: List<Permission>,
    launchMultiplePermissionRequest: () -> Unit,
) {


    Column(
        modifier = modifier
            .background(
                DataboxTheme.colorScheme.mainBackgroundColor
            )
            .fillMaxSize()
            .padding(horizontal = DataboxTheme.space.space20)
            .padding(top = DataboxTheme.space.space40),
        verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space40)
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
        ) {
            DataboxText(
                textStyle = DataboxTextStyle.No1,
                text = "권한을 확인해주세요",
                color = DataboxTheme.colorScheme.primaryTextColor,
                textAlign = TextAlign.Start
            )
            DataboxText(
                textStyle = DataboxTextStyle.No4,
                text = "앱을 사용하기 위해선 아래와 같은 권한이 허용 되어야 합니다.",
                color = DataboxTheme.colorScheme.secondaryTextColor,
                textAlign = TextAlign.Start
            )
        }
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20)
        ) {

            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
            ) {
                permissionList.forEach {
                    DataboxSurface(
                        modifier = modifier.fillMaxWidth(),
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DataboxText(
                                textStyle = DataboxTextStyle.No6,
                                text = it.name.value,
                                color = DataboxTheme.colorScheme.primaryTextColor,
                                textAlign = TextAlign.Start
                            )
                            DataboxText(
                                modifier = modifier.clickable {
                                    context.navigateSettingInfo()
                                },
                                textStyle = DataboxTextStyle.No8,
                                text = if (it.isGranted) "허용" else "미허용",
                                color =
                                if (it.isGranted) DataboxTheme.colorScheme.primaryTextColor
                                else DataboxTheme.colorScheme.tertiaryTextColor,
                                textAlign = TextAlign.Start
                            )

                        }
                    }
                }
            }
            DataboxDefaultButton(text = "권한 요청하기") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) context.navigateSettingInfo()
                else launchMultiplePermissionRequest()
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PermissionScreenPreview(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    DataBoxTheme {
        PermissionScreen(
            modifier = modifier,
            context = context,
            permissionList = listOf(
                Permission(
                    PermissionText.READ_EXTERNAL_STORAGE,
                    true
                ),
                Permission(
                    PermissionText.READ_EXTERNAL_STORAGE,
                    true
                ),
                Permission(
                    PermissionText.READ_EXTERNAL_STORAGE,
                    false
                )
            ),
            launchMultiplePermissionRequest = {},
        )
    }
}