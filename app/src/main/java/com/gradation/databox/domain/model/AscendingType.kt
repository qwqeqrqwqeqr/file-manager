package com.gradation.databox.domain.model

sealed interface AscendingType {

    data object Ascending : AscendingType
    data object Descending : AscendingType
}



