package com.gradation.databox.feature.directory.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.gradation.databox.core.common.state.DataState
import com.gradation.databox.core.common.state.EventState
import com.gradation.databox.data.file.datasource.FileDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FileState(
    val updateEventState: (EventState) -> Unit,
    private val fileDataSource:FileDataSource,
    private val viewModelScope:CoroutineScope,
    val selectedFileList: SnapshotStateList<String> = SnapshotStateList()
) {
    val createDirectory: (String, String) -> Unit = { path, name ->
        onFileEvent(FileEvent.CreateDirectory(path = path, name = name))
    }

    val selectFile: (String) -> Unit = { onFileEvent(FileEvent.SelectFile(it)) }
    val unselectFile: (String) -> Unit = { onFileEvent(FileEvent.UnselectFile(it)) }

    val copyFile: (String) -> Unit = { path ->
        onFileEvent(FileEvent.CopyFile(targetPath = path))
    }

    val moveFile: (String) -> Unit = { path ->
        onFileEvent(FileEvent.MoveFile(targetPath = path))
    }

    val deleteFile: () -> Unit = { onFileEvent(FileEvent.DeleteFile) }

    val clear: () -> Unit = { onFileEvent(FileEvent.Clear) }

    private fun onFileEvent(fileEvent: FileEvent) {
        when (fileEvent) {
            is FileEvent.CreateDirectory -> {
                viewModelScope.launch {
                    fileDataSource.createDirectory(
                        name = fileEvent.name,
                        path = fileEvent.path
                    ).collect{
                        when(it){
                            is DataState.Fail -> updateEventState(EventState.Fail("폴더생성을 실패하였습니다"))
                            is DataState.Success -> updateEventState(EventState.Success("폴더를 생성하였습니다"))
                        }
                    }


                }
            }

            is FileEvent.CopyFile -> {
                viewModelScope.launch {
                    fileDataSource.copyFile(selectedFileList.toList(), fileEvent.targetPath).collect {
                        when(it){
                            is DataState.Fail -> {
                                updateEventState(EventState.Fail("파일 복사를 실패하였습니다."))
                            }
                            is DataState.Success ->{
                                selectedFileList.clear()
                                updateEventState(EventState.Success("파일을 복사하였습니다"))
                            }
                        }
                    }
                }
            }

            is FileEvent.MoveFile -> {
                viewModelScope.launch {
                    fileDataSource.moveFile(selectedFileList.toList(), fileEvent.targetPath).collect {
                        when(it){
                            is DataState.Fail -> {
                                updateEventState(EventState.Fail("파일 이동을 실패하였습니다."))
                            }
                            is DataState.Success ->{
                                selectedFileList.clear()
                                updateEventState(EventState.Success("파일을 이동하였습니다"))
                            }
                        }
                    }
                }
            }

            is FileEvent.SelectFile -> selectedFileList.add(fileEvent.filePath)
            is FileEvent.UnselectFile -> selectedFileList.remove(fileEvent.filePath)
            FileEvent.Clear -> selectedFileList.clear()
            FileEvent.DeleteFile -> {
                viewModelScope.launch {
                    fileDataSource.deleteFile(selectedFileList.toList()).collect{
                        when(it){
                            is DataState.Fail ->  updateEventState(EventState.Fail("파일 삭제를 실패하였습니다."))
                            is DataState.Success -> updateEventState(EventState.Success("${it.data}개의 파일(폴더)을 삭제하였습니다"))
                        }
                    }
                }
            }
        }
    }
}