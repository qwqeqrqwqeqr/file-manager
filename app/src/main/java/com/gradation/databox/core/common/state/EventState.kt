package com.gradation.databox.core.common.state

sealed interface EventState {

    data class Success(val message:String? = null) : EventState
    data class Fail(val message:String = "") : EventState
    data object None: EventState
}