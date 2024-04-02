package com.gradation.databox.domain.mapper

import com.gradation.databox.domain.model.AscendingType


fun String.toAscendingType(): AscendingType =
    when (this) {
        AscendingType.Ascending.toString() -> AscendingType.Ascending
        AscendingType.Descending.toString() -> AscendingType.Descending
        else ->  AscendingType.Ascending
    }