package com.gradation.databox.feature.directory.data.state

sealed interface FileEvent {
    data class CreateDirectory(val name: String, val path: String) : FileEvent
    data class SelectFile(val filePath: String): FileEvent
    data class UnselectFile(val filePath: String): FileEvent
    data class CopyFile(val targetPath: String) : FileEvent
    data class MoveFile(val targetPath: String) : FileEvent
    data object DeleteFile : FileEvent
    data object Clear : FileEvent

}