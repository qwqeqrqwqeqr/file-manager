package com.gradation.databox.domain.mapper.type

import com.gradation.databox.domain.model.type.SortType


fun String.toSortType(): SortType =
    when (this) {
        SortType.Name.toString() -> SortType.Name
        SortType.Size.toString() -> SortType.Size
        SortType.LastModifiedTime.toString() -> SortType.LastModifiedTime
        SortType.CreateTime.toString() -> SortType.CreateTime
        else -> SortType.Name
    }