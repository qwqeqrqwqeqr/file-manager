package com.gradation.databox.domain.mapper.type

import com.gradation.databox.domain.model.type.AscendingType


fun String.toAscendingType(): AscendingType =
    when (this) {
        AscendingType.Ascending.toString() -> AscendingType.Ascending
        AscendingType.Descending.toString() -> AscendingType.Descending
        else ->  AscendingType.Ascending
    }