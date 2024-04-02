package com.gradation.databox.domain.mapper

import com.gradation.databox.domain.model.ViewType


fun String.toViewType(): ViewType =
    when (this) {
        ViewType.Grid.toString() -> ViewType.Grid
        ViewType.List.toString() -> ViewType.List
        else -> ViewType.Grid
    }