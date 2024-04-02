package com.gradation.databox.feature.directory.data.model

sealed interface ModeType {

    data object View : ModeType
    data object Edit : ModeType
    data class Copy(val isCopy: Boolean) : ModeType
}

