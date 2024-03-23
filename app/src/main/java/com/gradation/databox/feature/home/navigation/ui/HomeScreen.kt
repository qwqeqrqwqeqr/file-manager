package com.gradation.databox.feature.home.navigation.ui

import android.os.Environment
import android.os.Environment.DIRECTORY_DCIM
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.os.Environment.DIRECTORY_MOVIES
import android.os.Environment.DIRECTORY_MUSIC
import android.os.Environment.getExternalStorageDirectory
import android.os.Environment.getRootDirectory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.databox.core.designsystem.component.surface.DataboxSurface
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.noRippleClickable

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateHomeToDirectory: (String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(DataboxTheme.colorScheme.mainBackgroundColor)
                    .padding(horizontal = DataboxTheme.space.space20)
                    .padding(top = DataboxTheme.space.space40),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DataboxText(
                    textStyle = DataboxTextStyle.No0,
                    text = "내 파일",
                    color = DataboxTheme.colorScheme.primaryTextColor,
                    textAlign = TextAlign.Start
                )
                Icon(
                    imageVector = Icons.Outlined.MoreHoriz, contentDescription = "MoreHoriz",
                    tint = DataboxTheme.colorScheme.iconColor
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(DataboxTheme.colorScheme.mainBackgroundColor)
                .padding(DataboxTheme.space.space20)
                .padding(it), verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space24)
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
            ) {
                DataboxText(
                    textStyle = DataboxTextStyle.No3,
                    text = "저장소",
                    color = DataboxTheme.colorScheme.primaryTextColor,
                    textAlign = TextAlign.Start
                )

                DataboxSurface(
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(DataboxTheme.space.space12),
                    paddingValues = PaddingValues(
                        vertical = DataboxTheme.space.space12
                    )
                ) {
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space8)
                    ) {
                        Row(
                            modifier
                                .fillMaxWidth()
                                .noRippleClickable {
                                    navigateHomeToDirectory(getExternalStorageDirectory().absolutePath)
                                }
                                .padding(horizontal = DataboxTheme.space.space20),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space8)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Folder,
                                    contentDescription = "Folder",
                                    tint = DataboxTheme.colorScheme.primaryColor
                                )
                                DataboxText(
                                    textStyle = DataboxTextStyle.No5,
                                    text = "내 디바이스",
                                    color = DataboxTheme.colorScheme.primaryTextColor,
                                    textAlign = TextAlign.Start
                                )
                            }
                            Icon(
                                imageVector = Icons.Outlined.ChevronRight,
                                contentDescription = "ChevronRight",
                                tint = DataboxTheme.colorScheme.iconColor
                            )

                        }
                        HorizontalDivider(
                            modifier,
                            0.5.dp,
                            DataboxTheme.colorScheme.dividerColor
                        )
                        Row(
                            modifier
                                .fillMaxWidth()
                                .noRippleClickable {
                                    navigateHomeToDirectory(getRootDirectory().absolutePath)
                                }
                                .padding(horizontal = DataboxTheme.space.space20),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space8)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Folder,
                                    contentDescription = "Folder",
                                    tint = DataboxTheme.colorScheme.primaryColor
                                )
                                DataboxText(
                                    textStyle = DataboxTextStyle.No5,
                                    text = "시스템 저장소",
                                    color = DataboxTheme.colorScheme.primaryTextColor,
                                    textAlign = TextAlign.Start
                                )
                            }
                            Icon(
                                imageVector = Icons.Outlined.ChevronRight,
                                contentDescription = "ChevronRight",
                                tint = DataboxTheme.colorScheme.iconColor
                            )
                        }
                    }
                }
            }


            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
            ) {
                DataboxText(
                    textStyle = DataboxTextStyle.No3,
                    text = "라이브러리",
                    color = DataboxTheme.colorScheme.primaryTextColor,
                    textAlign = TextAlign.Start
                )
                Column {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space16)
                    ) {


                        DataboxSurface(
                            modifier = modifier
                                .weight(1f)
                                .noRippleClickable {
                                    navigateHomeToDirectory(
                                        Environment.getExternalStoragePublicDirectory(
                                            DIRECTORY_DCIM
                                        ).absolutePath
                                    )
                                },
                            shape = RoundedCornerShape(DataboxTheme.space.space12),
                            paddingValues = PaddingValues(
                                horizontal = DataboxTheme.space.space20,
                                vertical = DataboxTheme.space.space20
                            )
                        ) {
                            DataboxText(
                                textStyle = DataboxTextStyle.No5,
                                text = "사진",
                                color = DataboxTheme.colorScheme.secondaryTextColor,
                                textAlign = TextAlign.Start
                            )
                        }
                        DataboxSurface(
                            modifier = modifier
                                .weight(1f)
                                .noRippleClickable {
                                    navigateHomeToDirectory(
                                        Environment.getExternalStoragePublicDirectory(
                                            DIRECTORY_MOVIES
                                        ).absolutePath
                                    )
                                },
                            shape = RoundedCornerShape(DataboxTheme.space.space12),
                            paddingValues = PaddingValues(
                                horizontal = DataboxTheme.space.space20,
                                vertical = DataboxTheme.space.space20
                            )
                        ) {
                            DataboxText(
                                textStyle = DataboxTextStyle.No5,
                                text = "비디오",
                                color = DataboxTheme.colorScheme.secondaryTextColor,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
                Column {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space16)
                    ) {

                        DataboxSurface(
                            modifier = modifier
                                .weight(1f)
                                .noRippleClickable {
                                    navigateHomeToDirectory(
                                        Environment.getExternalStoragePublicDirectory(
                                            DIRECTORY_MUSIC
                                        ).absolutePath
                                    )
                                },
                            shape = RoundedCornerShape(DataboxTheme.space.space12),
                            paddingValues = PaddingValues(
                                horizontal = DataboxTheme.space.space20,
                                vertical = DataboxTheme.space.space20
                            )
                        ) {
                            DataboxText(
                                textStyle = DataboxTextStyle.No5,
                                text = "음악",
                                color = DataboxTheme.colorScheme.secondaryTextColor,
                                textAlign = TextAlign.Start
                            )
                        }
                        DataboxSurface(
                            modifier = modifier
                                .weight(1f)
                                .noRippleClickable {
                                    navigateHomeToDirectory(
                                        Environment.getExternalStoragePublicDirectory(
                                            DIRECTORY_DOWNLOADS
                                        ).absolutePath
                                    )
                                },
                            shape = RoundedCornerShape(DataboxTheme.space.space12),
                            paddingValues = PaddingValues(
                                horizontal = DataboxTheme.space.space20,
                                vertical = DataboxTheme.space.space20
                            )
                        ) {
                            DataboxText(
                                textStyle = DataboxTextStyle.No5,
                                text = "다운로드",
                                color = DataboxTheme.colorScheme.secondaryTextColor,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }
        }
    }
}