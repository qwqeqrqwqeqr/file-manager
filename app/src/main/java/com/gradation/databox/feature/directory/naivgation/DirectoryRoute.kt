package com.gradation.databox.feature.directory.naivgation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.data.file.utils.toDataboxFileTypeList
import com.gradation.databox.feature.directory.data.DirectoryViewModel
import com.gradation.databox.feature.directory.ui.DirectoryScreen


@Composable
fun DirectoryRoute(
    modifier: Modifier,
    navigateDirectoryToDirectory: (String) -> Unit,
    directoryViewModel: DirectoryViewModel = hiltViewModel()
) {
    val directoryPath: String by directoryViewModel.path.collectAsStateWithLifecycle()
    val fileList: List<DataboxFileType> = directoryPath.toDataboxFileTypeList()


    DirectoryScreen(
        modifier,
        directoryPath,
        fileList,
        navigateDirectoryToDirectory
    )

}
