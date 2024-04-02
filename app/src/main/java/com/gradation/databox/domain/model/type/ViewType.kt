package com.gradation.databox.domain.model.type

sealed interface ViewType {

    data object Grid : ViewType
    data object List : ViewType
}


