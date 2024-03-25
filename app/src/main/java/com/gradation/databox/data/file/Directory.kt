package com.gradation.databox.data.file

import java.io.File

fun getDirectorySize(directory: File): Long {
    var length: Long = 0
    directory.listFiles()?.forEach { file ->
        length += if (file.isFile)
            file.length()
        else getDirectorySize(file)
    }
    return length;
}


fun createDirectory(path: String, name: String): Boolean {
    File(path + File.separator + name).also {
        return try {
            if (!it.exists()) it.mkdirs()
            else false
        } catch (error: Error) {
            return false
        }
    }
}