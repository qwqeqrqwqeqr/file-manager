package com.gradation.databox.feature.directory.data.state

import com.gradation.databox.core.ui.event.EventState
import com.gradation.databox.data.file.createDirectory

class FileState(
    val updateEventState: (EventState) -> Unit
) {

    val createDirectory: (String, String) -> Unit = { path, name ->
        onCurrentRoutineSetRoutineEvent(FileEvent.CreateDirectory(path = path, name = name))
    }


    private fun onCurrentRoutineSetRoutineEvent(fileEvent: FileEvent) {
        when (fileEvent) {
            is FileEvent.CreateDirectory -> {
                if (createDirectory(name = fileEvent.name, path = fileEvent.path)) {
                    updateEventState(EventState.Success("폴더를 생성하였습니다"))

                } else {
                    updateEventState(EventState.Fail("폴더생성을 실패하였습니다"))
                }
            }
        }
    }
}