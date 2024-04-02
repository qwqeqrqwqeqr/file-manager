package com.gradation.databox.data.file.datasource

import android.os.Environment
import android.util.Log
import com.gradation.databox.core.common.state.DataState
import com.gradation.databox.di.DispatcherProvider
import com.gradation.databox.domain.mapper.file.createDataboxFileType
import com.gradation.databox.domain.model.file.FileType
import com.gradation.databox.domain.model.file.PathTree
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.LinkedList
import javax.inject.Inject

class DefaultFileDataSource @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) :
    FileDataSource {
    override suspend fun getFileTypeList(path: String): Flow<DataState<List<FileType>>> =
        flow {
            try {
                val fileList = File(path)
                    .listFiles { it -> it.canRead() }
                    ?.asSequence()
                    ?.filterNotNull()
                    ?.map { createDataboxFileType(it) }
                    ?.toList() ?: emptyList()
                emit(DataState.Success(fileList))

            } catch (error: Throwable) {
                emit(DataState.Fail(error.toString()))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun getPathTreeList(path: String): Flow<DataState<List<PathTree>>> = flow {
        try {
            val pathTreeList: LinkedList<PathTree> = LinkedList()
            val dataAbsolutePath = Environment.getExternalStorageDirectory().absolutePath

            path.split(File.separator).reduce { absolutePath, currentPath ->
                pathTreeList.add(
                    PathTree(
                        absolutePath = "$absolutePath/$currentPath",
                        name = currentPath
                    )
                )
                "$absolutePath/$currentPath"
            }


            emit(DataState.Success(if (path.contains(dataAbsolutePath))
                pathTreeList.filterNot {
                    dataAbsolutePath.split(File.separator).contains(it.name)
                }.toMutableList().also {
                    it.add(0, PathTree(name = "sdcard", absolutePath = dataAbsolutePath))
                } else
                pathTreeList)
            )
        } catch (error: Throwable) {
            emit(DataState.Fail(error.toString()))
        }
    }.flowOn(dispatcherProvider.io)


    override suspend fun createDirectory(path: String, name: String): Flow<DataState<Unit>> = flow {
        try {
            Paths.get(path + File.separator + name).also {
                Files.createDirectory(it)
                emit(DataState.Success(Unit))
            }
        } catch (error: Throwable) {
            emit(DataState.Fail(error.toString()))
        }
    }.flowOn(dispatcherProvider.io)

    override suspend fun deleteFile(filePathList: List<String>): Flow<DataState<Long>> = flow {
        try {
            var deleteFileCount = 0L
            filePathList.forEach { path ->
                Files.walk(Paths.get(path))
                    .sorted(Comparator.reverseOrder())
                    .also { deleteFileCount += it.count() }
                    .forEach { Files.delete(it) }
            }
            emit(DataState.Success(deleteFileCount))
        } catch (error: Throwable) {
            emit(DataState.Fail(error.toString()))
        }
    }.flowOn(dispatcherProvider.io)

    override suspend fun moveFile(
        filePathList: List<String>,
        destinationPath: String
    ): Flow<DataState<Unit>> = flow {
        try {
            Paths.get(destinationPath).also { destinationPath ->
                filePathList.map { Paths.get(it) }.forEach { source ->
                    Files.move(source, destinationPath.resolve(source.fileName), StandardCopyOption.REPLACE_EXISTING)
                }
            }
            emit(DataState.Success(Unit))
        } catch (error: Throwable) {
            emit(DataState.Fail(error.toString()))
        }

    }.flowOn(dispatcherProvider.io)

    override suspend fun copyFile(
        filePathList: List<String>,
        destinationPath: String
    ): Flow<DataState<Unit>> = flow {
        try {
            Paths.get(destinationPath).also { path ->
                filePathList.forEach {
                    Files.copy(Paths.get(it), path, StandardCopyOption.REPLACE_EXISTING)
                }
            }
            emit(DataState.Success(Unit))
        } catch (error: Throwable) {
            emit(DataState.Fail(error.toString()))
        }
    }.flowOn(dispatcherProvider.io)
}