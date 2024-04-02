package com.gradation.databox.domain.model

sealed interface SortType {
    data object Name : SortType
    data object Size : SortType
    data object LastModifiedTime : SortType
    data object CreateTime : SortType

}


