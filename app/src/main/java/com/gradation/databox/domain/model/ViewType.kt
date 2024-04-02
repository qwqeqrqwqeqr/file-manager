package com.gradation.databox.domain.model

sealed interface ViewType {

    data object Grid : ViewType
    data object List : ViewType
}


