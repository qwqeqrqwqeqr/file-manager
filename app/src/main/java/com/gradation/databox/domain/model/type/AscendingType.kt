package com.gradation.databox.domain.model.type

sealed interface AscendingType {

    data object Ascending : AscendingType
    data object Descending : AscendingType
}



