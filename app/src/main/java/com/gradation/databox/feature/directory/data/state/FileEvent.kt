package com.gradation.databox.feature.directory.data.state

sealed interface FileEvent {
    data class CreateDirectory(val name: String, val path: String) : FileEvent

}