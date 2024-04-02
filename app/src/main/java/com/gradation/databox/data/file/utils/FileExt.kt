package com.gradation.databox.data.file.utils


import com.gradation.databox.domain.mapper.toLocalDateTime
import com.gradation.databox.domain.model.file.FileExtension
import com.gradation.databox.domain.model.file.FileType
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
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


fun createDataboxFileType(file: File): FileType {


    val path = Paths.get(file.absolutePath)
    val attrs: BasicFileAttributes = Files.readAttributes(path, BasicFileAttributes::class.java)

    return if (file.isDirectory)
        FileType.DirectoryType(
            itemSize = file.listFiles()?.size?.toLong() ?: 0L,
            name = file.name,
            absolutePath = file.absolutePath,
            size = getDirectorySize(file),
            creationTime = attrs.creationTime().toLocalDateTime(),
            lastAccessTime = attrs.lastModifiedTime().toLocalDateTime(),
            lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
        ) else
        when (path.getExtension()) {
            FileExtension.JPG -> {
                FileType.ImageType(
                    name = file.name,
                    absolutePath = file.absolutePath,
                    size = file.length(),
                    creationTime = attrs.creationTime().toLocalDateTime(),
                    lastAccessTime = attrs.lastModifiedTime().toLocalDateTime(),
                    lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                )
            }

            FileExtension.PNG -> {
                FileType.ImageType(
                    name = file.name,
                    absolutePath = file.absolutePath,
                    size = file.length(),
                    creationTime = attrs.creationTime().toLocalDateTime(),
                    lastAccessTime = attrs.lastModifiedTime().toLocalDateTime(),
                    lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                )
            }

            FileExtension.JPEG -> {
                FileType.ImageType(
                    name = file.name,
                    absolutePath = file.absolutePath,
                    size = file.length(),
                    creationTime = attrs.creationTime().toLocalDateTime(),
                    lastAccessTime = attrs.lastModifiedTime().toLocalDateTime(),
                    lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                )
            }

            FileExtension.DEFAULT -> {
                FileType.DefaultFileType(
                    name = file.name,
                    absolutePath = file.absolutePath,
                    size = file.length(),
                    creationTime = attrs.creationTime().toLocalDateTime(),
                    lastAccessTime = attrs.lastModifiedTime().toLocalDateTime(),
                    lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                )
            }
        }

}