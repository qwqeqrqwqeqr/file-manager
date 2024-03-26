package com.gradation.databox.feature.directory.data.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.gradation.databox.core.ui.event.EventState
import com.gradation.databox.data.file.copyFile
import com.gradation.databox.data.file.createDirectory
import com.gradation.databox.data.file.deleteFile
import com.gradation.databox.data.file.moveFile

class FileState(
    val updateEventState: (EventState) -> Unit,
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
                if (createDirectory(name = fileEvent.name, path = fileEvent.path)) {
                    updateEventState(EventState.Success("폴더를 생성하였습니다"))

                } else {
                    updateEventState(EventState.Fail("폴더생성을 실패하였습니다"))
                }
            }

            is FileEvent.CopyFile -> {
                copyFile(selectedFileList, fileEvent.targetPath)
                selectedFileList.clear()
                updateEventState(EventState.Success("파일을 복사하였습니다"))
            }

            is FileEvent.MoveFile -> {
                moveFile(selectedFileList, fileEvent.targetPath)
                selectedFileList.clear()
                updateEventState(EventState.Success("파일을 이동하였습니다"))
            }

            is FileEvent.SelectFile -> selectedFileList.add(fileEvent.filePath)
            is FileEvent.UnselectFile -> selectedFileList.remove(fileEvent.filePath)
            FileEvent.Clear -> selectedFileList.clear()
            FileEvent.DeleteFile -> {
                selectedFileList.apply {
                    deleteFile()
                    updateEventState(EventState.Success("${this.size}개의 파일(폴더)을 삭제하였습니다"))
                }
            }
        }
    }
}