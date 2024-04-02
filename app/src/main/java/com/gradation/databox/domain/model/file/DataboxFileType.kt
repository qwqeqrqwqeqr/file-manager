package com.gradation.databox.domain.model.file

import java.time.LocalDateTime

sealed class FileType(
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
    ) : FileType(name, absolutePath, size, creationTime, lastAccessTime, lastModifiedTime)


    class DefaultFileType(
        name: String,
        absolutePath: String,
        size: Long,
        creationTime: LocalDateTime,
        lastAccessTime: LocalDateTime,
        lastModifiedTime: LocalDateTime,
    ) : FileType(name, absolutePath, size, creationTime, lastAccessTime, lastModifiedTime)

    class ImageType(
        name: String,
        absolutePath: String,
        size: Long,
        creationTime: LocalDateTime,
        lastAccessTime: LocalDateTime,
        lastModifiedTime: LocalDateTime,
    ) : FileType(name, absolutePath, size, creationTime, lastAccessTime, lastModifiedTime)

}



