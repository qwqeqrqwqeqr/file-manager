package com.gradation.databox.core.common.state

sealed interface DataState<out T> {
    data class Success<out T>(val data: T, val message: String? = null) : DataState<T>
    data class Fail(val message: String) : DataState<Nothing>
}

