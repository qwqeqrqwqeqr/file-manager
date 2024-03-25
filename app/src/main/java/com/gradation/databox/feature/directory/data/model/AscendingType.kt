package com.gradation.databox.feature.directory.data.model

sealed interface AscendingType {

    data object Ascending : AscendingType
    data object Descending : AscendingType
}