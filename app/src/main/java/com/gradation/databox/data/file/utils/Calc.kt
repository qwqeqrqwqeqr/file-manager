package com.gradation.databox.data.file.utils

import java.io.File

fun calcDirectorySize(directory: File): Long {
    return directory.listFiles()?.filter { !it.isDirectory }?.sumOf { it.length() } ?: 0L
}

