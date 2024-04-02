package com.gradation.databox.data.file.datasource

import com.gradation.databox.core.common.state.DataState
import com.gradation.databox.domain.model.file.FileType
import com.gradation.databox.domain.model.file.PathTree
import kotlinx.coroutines.flow.Flow
import java.io.File

interface FileDataSource {

    suspend fun getFileTypeList(path: String): Flow<DataState<List<FileType>>>

    suspend fun getPathTreeList(path: String): Flow<DataState<List<PathTree>>>

    suspend fun createDirectory(path: String, name: String): Flow<DataState<Unit>>

    suspend fun deleteFile(filePathList: List<String>): Flow<DataState<Long>>

    suspend fun moveFile(filePathList: List<String>, destinationPath: String): Flow<DataState<Unit>>

    suspend fun copyFile(filePathList: List<String>, destinationPath: String): Flow<DataState<Unit>>

}