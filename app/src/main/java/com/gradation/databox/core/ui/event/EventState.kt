package com.gradation.databox.core.ui.event

sealed interface EventState {

    data class Success(val message:String? = null) : EventState
    data class Fail(val message:String = "") : EventState
    data object None: EventState
}