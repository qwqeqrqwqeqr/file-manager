package com.gradation.databox.data.file.utils

import java.io.File

fun calcDirectorySize(directory: File): Long {
    var length: Long = 0
    directory.listFiles()?.forEach { file ->
        length += if (file.isFile)
            file.length()
        else calcDirectorySize(file)
    }
    return length;
}

