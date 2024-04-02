package com.gradation.databox.domain.mapper

import java.nio.file.attribute.FileTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun FileTime.toLocalDateTime(): LocalDateTime {
    return toInstant()
        .atZone(ZoneId.systemDefault()).toLocalDateTime()
}


fun LocalDateTime.toText(): String {
    val now = LocalDate.now()
    return if (now.toEpochDay() == this.toLocalDate()?.toEpochDay()) {
        DateTimeFormatter.ofPattern("hh:mm a").let { formatter ->
            this.format(formatter)
        }

    }
    else if (now.year == this.toLocalDate()?.year && now.month == this.toLocalDate()?.month) {
        DateTimeFormatter.ofPattern("yyyy/MM/dd").let { formatter ->
            this.format(formatter)
        }

    }
    else {
        DateTimeFormatter.ofPattern("yyyy/MM").let { formatter ->
            this.format(formatter)
        }
    }
}