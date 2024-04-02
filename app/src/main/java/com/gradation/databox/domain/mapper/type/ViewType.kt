package com.gradation.databox.domain.mapper.type

import com.gradation.databox.domain.model.type.ViewType


fun String.toViewType(): ViewType =
    when (this) {
        ViewType.Grid.toString() -> ViewType.Grid
        ViewType.List.toString() -> ViewType.List
        else -> ViewType.List
    }