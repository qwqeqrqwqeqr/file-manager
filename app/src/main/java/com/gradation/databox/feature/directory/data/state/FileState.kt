package com.gradation.databox.feature.directory.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.gradation.databox.core.common.state.DataState
import com.gradation.databox.core.common.state.EventState
import com.gradation.databox.data.file.datasource.FileDataSource
import com.gradation.databox.di.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FileState(
    val updateEventState: (EventState) -> Unit,
    private val fileDataSource: FileDataSource,
    private val viewModelScope: CoroutineScope,
    private val dispatcherProvider: DispatcherProvider,
    val selectedFileList: SnapshotStateList<String> = SnapshotStateList()
) {
    val createDirectory: (String, String) -> Unit = { path, name ->
        onFileEvent(FileEvent.CreateDirectory(path = path, name = name))
    }

    val selectFile: (String) -> Unit = { onFileEvent(FileEvent.SelectFile(it)) }
    val unselectFile: (String) -> Unit = { onFileEvent(FileEvent.UnselectFile(it)) }

    val copyFile: (String) -> Unit = { targetPath ->
        selectedFileList.forEach { sourcePath ->
            onFileEvent(FileEvent.CopyFile(sourcePath = sourcePath, targetPath = targetPath))
        }
        onFileEvent(FileEvent.Clear)
    }

    val moveFile: (String) -> Unit = { targetPath ->
        selectedFileList.forEach { sourcePath ->
            onFileEvent(FileEvent.MoveFile(sourcePath = sourcePath, targetPath = targetPath))
        }
        onFileEvent(FileEvent.Clear)
    }

    val deleteFile: () -> Unit = {
        selectedFileList.forEach { filePath ->
            onFileEvent(FileEvent.DeleteFile(filePath = filePath))
        }
        onFileEvent(FileEvent.Clear)
    }

    val clear: () -> Unit = { onFileEvent(FileEvent.Clear) }

    private fun onFileEvent(fileEvent: FileEvent) {
        when (fileEvent) {
            is FileEvent.CreateDirectory -> {
                viewModelScope.launch(dispatcherProvider.default + SupervisorJob()) {
                    fileDataSource.createDirectory(
                        name = fileEvent.name,
                        path = fileEvent.path
                    ).collect {
                        when (it) {
                            is DataState.Fail -> updateEventState(EventState.Fail("폴더생성을 실패하였습니다"))
                            is DataState.Success -> updateEventState(EventState.Success("폴더를 생성하였습니다"))
                        }
                    }


                }
            }

            is FileEvent.CopyFile -> {
                viewModelScope.launch(dispatcherProvider.default + SupervisorJob()) {
                    fileDataSource.copyFile(fileEvent.sourcePath, fileEvent.targetPath).collect {
                        when (it) {
                            is DataState.Fail -> {
                                updateEventState(EventState.Fail("파일 복사를 실패하였습니다."))
                            }

                            is DataState.Success -> {
                                updateEventState(EventState.Success("파일을 복사하였습니다"))
                            }
                        }
                    }
                }
            }

            is FileEvent.MoveFile -> {
                viewModelScope.launch(dispatcherProvider.default + SupervisorJob()) {
                    fileDataSource.moveFile(fileEvent.sourcePath, fileEvent.targetPath).collect {
                        when (it) {
                            is DataState.Fail -> {
                                updateEventState(EventState.Fail("파일 이동을 실패하였습니다."))
                            }

                            is DataState.Success -> {
                                updateEventState(EventState.Success("파일을 이동하였습니다"))
                            }
                        }
                    }
                }
            }

            is FileEvent.SelectFile -> selectedFileList.add(fileEvent.filePath)
            is FileEvent.UnselectFile -> selectedFileList.remove(fileEvent.filePath)
            FileEvent.Clear -> selectedFileList.clear()
            is FileEvent.DeleteFile -> {
                viewModelScope.launch(dispatcherProvider.default + SupervisorJob()) {
                    fileDataSource.deleteFile(fileEvent.filePath).collect {
                        when (it) {
                            is DataState.Fail -> updateEventState(EventState.Fail("파일 삭제를 실패하였습니다."))
                            is DataState.Success -> updateEventState(EventState.Success("${it.data}개의 파일(폴더)을 삭제하였습니다"))
                        }
                    }
                }
            }
        }
    }
}