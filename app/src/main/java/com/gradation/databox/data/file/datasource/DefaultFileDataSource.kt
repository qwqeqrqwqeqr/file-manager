package com.gradation.databox.data.file.datasource

import android.os.Environment
import android.util.Log
import com.gradation.databox.core.common.state.DataState
import com.gradation.databox.data.file.utils.createDataboxFileType
import com.gradation.databox.di.DispatcherProvider
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
import java.util.stream.Collectors
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

    override suspend fun deleteFile(filePath: String): Flow<DataState<Long>> = flow {
        try {
            var deleteFileCount = 0L
            Files.walk(Paths.get(filePath))
                .collect(Collectors.toList())
                .reversed()
                .also { deleteFileCount += it.count() }
                .forEach{ Files.delete(it) }
            emit(DataState.Success(deleteFileCount))
        } catch (error: Throwable) {
            emit(DataState.Fail(error.toString()))
        }
    }.flowOn(dispatcherProvider.io)

    override suspend fun moveFile(
        sourcePath: String,
        destinationPath: String
    ): Flow<DataState<Unit>> = flow {
        try {
            Paths.get(destinationPath).also { target ->
                Paths.get(sourcePath).also { source ->
                    Files.move(
                        source,
                        target.resolve(source.fileName),
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }
            }
            emit(DataState.Success(Unit))
        } catch (error: Throwable) {
            emit(DataState.Fail(error.toString()))
        }

    }.flowOn(dispatcherProvider.io)

    override suspend fun copyFile(
        sourcePath: String,
        destinationPath: String
    ): Flow<DataState<Unit>> = flow {
        try {
            val dPath = Paths.get(destinationPath)
            val sPath = Paths.get(sourcePath)

            Files.walk(sPath)
                .forEach { source ->
                    val target = dPath.resolve(sPath.relativize(source))
                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING)
                }


            emit(DataState.Success(Unit))
        } catch (error: Throwable) {
            emit(DataState.Fail(error.toString()))
        }
    }.flowOn(dispatcherProvider.io)
}

