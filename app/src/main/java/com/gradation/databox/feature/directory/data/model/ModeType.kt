package com.gradation.databox.feature.directory.data.model

sealed interface ModeType {

    data object View : ModeType
    data object Edit : ModeType
    data class COPY(val isCopy: Boolean) : ModeType
}