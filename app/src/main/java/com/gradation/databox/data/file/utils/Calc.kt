package com.gradation.databox.data.file.utils


fun String.getByteSize(): Int = toByteArray(Charsets.UTF_8).size
