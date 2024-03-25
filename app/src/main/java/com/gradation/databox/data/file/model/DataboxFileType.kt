package com.gradation.databox.data.file.model

import java.time.LocalDateTime

sealed class DataboxFileType(
    val name: String,
    val absolutePath: String,
    val size: Long,
    val creationTime: LocalDateTime,
    val lastAccessTime: LocalDateTime,
    val lastModifiedTime: LocalDateTime,
) {
    class DirectoryType(
        val itemSize: Long,
        name: String,
        absolutePath: String,
        size: Long,
        creationTime: LocalDateTime,
        lastAccessTime: LocalDateTime,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(name, absolutePath, size, creationTime, lastAccessTime, lastModifiedTime)


    class FileType(
        name: String,
        absolutePath: String,
        size: Long,
        creationTime: LocalDateTime,
        lastAccessTime: LocalDateTime,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(name, absolutePath, size, creationTime, lastAccessTime, lastModifiedTime)

    class ImageType(
        name: String,
        absolutePath: String,
        size: Long,
        creationTime: LocalDateTime,
        lastAccessTime: LocalDateTime,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(name, absolutePath, size, creationTime, lastAccessTime, lastModifiedTime)

}



