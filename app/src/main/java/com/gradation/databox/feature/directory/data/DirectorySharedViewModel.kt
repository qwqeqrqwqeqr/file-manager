package com.gradation.databox.feature.directory.data

import androidx.lifecycle.ViewModel
import com.gradation.databox.core.ui.event.EventState
import com.gradation.databox.feature.directory.data.state.FileState
import com.gradation.databox.feature.directory.data.state.TypeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DirectorySharedViewModel @Inject constructor(
) : ViewModel() {


    val eventState: MutableStateFlow<EventState> = MutableStateFlow(EventState.None)
    val updateEventState: (EventState) -> Unit = { eventState.value = it }

    val fileState: FileState = FileState(updateEventState)
    val typeState: TypeState = TypeState()

}