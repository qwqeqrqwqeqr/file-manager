package com.gradation.databox.feature.directory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.MoreHoriz
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
import com.gradation.databox.core.ui.compose.scrollBar
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.data.file.model.PathTree
import com.gradation.databox.feature.directory.data.state.DirectoryScreenState
import com.gradation.databox.feature.directory.ui.component.DirectoryTypeItem
import com.gradation.databox.feature.directory.ui.component.FileTypeItem
import com.gradation.databox.feature.directory.ui.component.ImageFileTypeItem


@Composable
fun DirectoryScreen(
    modifier: Modifier,
    directoryPath: String,
    fileList: List<DataboxFileType>,
    pathTreeList: List<PathTree>,
    popBackStack: () -> Unit,
    navigateDirectoryToDirectory: (String) -> Unit,
    directoryScreenState: DirectoryScreenState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                DataboxTheme.colorScheme.backgroundColor
            )
    ) {
        Column(
            modifier = modifier.padding(
                top = DataboxTheme.space.space20,
                bottom = DataboxTheme.space.space20
            ),
            verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space32)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = DataboxTheme.space.space20),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space16),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = modifier.noRippleClickable { popBackStack() },
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        contentDescription = "ArrowBackIosNew",
                        tint = DataboxTheme.colorScheme.secondaryIconColor
                    )

                    DataboxText(
                        textStyle = DataboxTextStyle.No1,
                        text = pathTreeList.lastOrNull()?.name ?: "",
                        color = DataboxTheme.colorScheme.primaryTextColor,
                        textAlign = TextAlign.Start
                    )
                }
                Icon(
                    imageVector = Icons.Outlined.MoreHoriz, contentDescription = "MoreHoriz",
                    tint = DataboxTheme.colorScheme.secondaryIconColor
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space12)
            ) {
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
                        tint = DataboxTheme.colorScheme.primaryIconColor
                    )
                    LazyRow(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4)
                    ) {
                        pathTreeList.also { pathTreeList ->
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
                                        color = DataboxTheme.colorScheme.secondaryTextColor,
                                        textAlign = TextAlign.Start
                                    )
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = DataboxTheme.space.space20),
                    horizontalArrangement = Arrangement.spacedBy(
                        DataboxTheme.space.space20,
                        Alignment.End
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    /** TODO not implement
                     *                     Icon(
                     *                         modifier = modifier.size(DataboxTheme.space.space20),
                     *                         imageVector = Icons.Filled.FilterAlt,
                     *                         contentDescription = "FilterAlt",
                     *                         tint = DataboxTheme.colorScheme.primaryIconColor
                     *                     )
                     */

                    Icon(
                        modifier = modifier
                            .size(DataboxTheme.space.space20)
                            .noRippleClickable {
                                directoryScreenState.updateSortBottomSheetView(true)
                            },
                        imageVector = Icons.AutoMirrored.Filled.Sort,
                        contentDescription = "Sort",
                        tint = DataboxTheme.colorScheme.primaryIconColor
                    )
                    Icon(
                        modifier = modifier
                            .size(DataboxTheme.space.space20)
                            .noRippleClickable {
                                directoryScreenState.updateViewBottomSheetView(true)
                            },
                        imageVector = Icons.Filled.GridView,
                        contentDescription = "GridView",
                        tint = DataboxTheme.colorScheme.primaryIconColor
                    )
                }
            }
        }
        if (fileList.isEmpty())
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier,
                    verticalArrangement = Arrangement.spacedBy(DataboxTheme.space.space4),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = modifier.size(DataboxTheme.space.space68),
                        imageVector = Icons.Filled.Folder,
                        contentDescription = "Folder",
                        tint = DataboxTheme.colorScheme.primaryIconColor
                    )
                    DataboxText(
                        textStyle = DataboxTextStyle.No3,
                        text = "폴더가 비어있습니다",
                        color = DataboxTheme.colorScheme.secondaryTextColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        else {

            LazyColumn(
                modifier.scrollBar(directoryScreenState.lazyListState).padding(horizontal = DataboxTheme.space.space20),
                state = directoryScreenState.lazyListState
            ) {
                items(fileList) { file ->
                    when (file) {
                        is DataboxFileType.DirectoryType ->
                            DirectoryTypeItem(modifier, file, navigateDirectoryToDirectory)

                        is DataboxFileType.FileType ->
                            FileTypeItem(modifier, file)

                        is DataboxFileType.ImageType ->
                            ImageFileTypeItem(modifier, file)
                    }
                }
            }
        }
    }
}