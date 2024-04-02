package com.gradation.databox.core.common.event

import com.gradation.databox.core.common.state.EventState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class EventManager @Inject constructor() {

    val eventState: MutableStateFlow<EventState> = MutableStateFlow(EventState.None)


    val updateEventState: (EventState) -> Unit = { eventState.value = it }
}