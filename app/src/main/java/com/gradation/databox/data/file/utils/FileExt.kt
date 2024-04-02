package com.gradation.databox.data.file.utils


import com.gradation.databox.domain.model.file.FileExtension
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.name

const val MAX_BYTE_LENGTH = 255

fun String.getByteSize(): Int = toByteArray(Charsets.UTF_8).size


fun String.validateFileName(): Boolean {
    return (isNotEmpty()
            && getByteSize() < MAX_BYTE_LENGTH
            && !contains('/')
            && !contains('\\')
            && !contains('?')
            && !contains(':')
            && !contains('\"')
            && !contains('\'')
            && !contains('<')
            && !contains('>')
            && !contains('|')
            && !contains('*')
            )
}


fun Path.getExtension(): FileExtension {
    val index = this.name.lastIndexOf(".")
    return when (this.name.substring(index + 1)) {
        "jpg" -> FileExtension.JPG
        "JPG" -> FileExtension.JPG
        "png" -> FileExtension.PNG
        "PNG" -> FileExtension.PNG
        "jpeg" -> FileExtension.JPEG
        "JPEG" -> FileExtension.JPEG
        else -> FileExtension.DEFAULT
    }
}



fun getDirectorySize(directory: File): Long {
    var length: Long = 0
    directory.listFiles()?.forEach { file ->
        length += if (file.isFile)
            file.length()
        else getDirectorySize(file)
    }
    return length;
}
