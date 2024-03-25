package com.gradation.databox.feature.directory.data.model

sealed interface ViewType {

    data object Grid : ViewType
    data object List : ViewType
}