package com.gradation.databox.data.model

import java.io.File
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes
import java.time.LocalDateTime

sealed class DataboxFileType(
    val file: File,
    val path: Path,
    val attrs: BasicFileAttributes,
    val name: String,
    val absolutePath: String,
    val creationTime: LocalDateTime,
    val lastAccessTime: LocalDateTime,
    val lastModifiedTime: LocalDateTime,
) {
    class DirectoryType(
        val itemSize: Long,
        file: File,
        path: Path,
        attrs: BasicFileAttributes,
        name: String,
        absolutePath: String,
        creationTime: LocalDateTime,
        lastAccessTime: LocalDateTime,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(
        file, path, attrs, name, absolutePath, creationTime, lastAccessTime, lastModifiedTime
    )


    class FileType(
        val size: Long,
        file: File,
        path: Path,
        attrs: BasicFileAttributes,
        name: String,
        absolutePath: String,
        creationTime: LocalDateTime,
        lastAccessTime: LocalDateTime,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(
        file, path, attrs, name, absolutePath, creationTime, lastAccessTime, lastModifiedTime
    )

}



