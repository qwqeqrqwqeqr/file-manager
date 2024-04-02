package com.gradation.databox.feature.directory.data.state

import com.gradation.databox.domain.model.file.FileType

sealed interface DirectoryUiState {
    data class Success(val fileList: List<FileType>) : DirectoryUiState
    data object Empty : DirectoryUiState
    data object Loading : DirectoryUiState
}