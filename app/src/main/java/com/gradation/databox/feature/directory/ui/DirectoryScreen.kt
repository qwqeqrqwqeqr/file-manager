package com.gradation.databox.feature.directory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.databox.core.designsystem.component.surface.DataboxSurface
import com.gradation.databox.core.designsystem.component.surface.SurfaceType
import com.gradation.databox.core.designsystem.component.text.DataboxText
import com.gradation.databox.core.designsystem.component.text.DataboxTextStyle
import com.gradation.databox.core.designsystem.theme.DataboxTheme
import com.gradation.databox.core.ui.compose.noRippleClickable
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.data.file.utils.getPathTreeList
import com.gradation.databox.feature.directory.ui.component.DirectoryTypeItem
import com.gradation.databox.feature.directory.ui.component.FileTypeItem


@Composable
fun DirectoryScreen(
    modifier: Modifier,
    directoryPath: String,
    fileList: List<DataboxFileType>,
    navigateDirectoryToDirectory: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                DataboxTheme.colorScheme.backgroundColor
            )
    ) {
        Column(
            modifier = modifier.padding(bottom = DataboxTheme.space.space20)
        ) {
            Spacer(modifier = modifier.height(DataboxTheme.space.space72))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = DataboxTheme.space.space20),
                horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space20),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.FolderOpen,
                    contentDescription = "FolderOpen",
                    tint = DataboxTheme.colorScheme.iconColor
                )
                LazyRow(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4)
                ) {
                    directoryPath.getPathTreeList().also { pathTreeList ->
                        itemsIndexed(pathTreeList) { index, it ->
                            DataboxSurface(
                                modifier = modifier.noRippleClickable {
                                    if (pathTreeList.lastIndex != index)
                                        navigateDirectoryToDirectory(it.absolutePath)
                                },
                                surfaceType = SurfaceType.Secondary,
                                paddingValues = PaddingValues(
                                    vertical = DataboxTheme.space.space6,
                                    horizontal = DataboxTheme.space.space12
                                ),
                                shape = RoundedCornerShape(DataboxTheme.space.space12)
                            ) {
                                DataboxText(
                                    textStyle = DataboxTextStyle.No5,
                                    text = it.name,
                                    color = DataboxTheme.colorScheme.primaryTextColor,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            }
        }
        LazyColumn(
            modifier.padding(horizontal = DataboxTheme.space.space20)
        ) {
            items(fileList) { file ->
                when (file) {
                    is DataboxFileType.DirectoryType ->
                        DirectoryTypeItem(modifier, file, navigateDirectoryToDirectory)

                    is DataboxFileType.FileType ->
                        FileTypeItem(modifier, file)

                }
            }
        }
    }
}