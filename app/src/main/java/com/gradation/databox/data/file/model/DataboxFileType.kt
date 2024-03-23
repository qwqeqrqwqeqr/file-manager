package com.gradation.databox.data.file.model

import java.time.LocalDateTime

sealed class DataboxFileType(
    val name: String,
    val absolutePath: String,
    val lastModifiedTime: LocalDateTime,
) {
    class DirectoryType(
        val itemSize: Long,
        name: String,
        absolutePath: String,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(name, absolutePath,  lastModifiedTime)


    class FileType(
        val size: Long,
        name: String,
        absolutePath: String,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(name, absolutePath,  lastModifiedTime)

    class ImageType(
        val size: Long,
        name: String,
        absolutePath: String,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(name, absolutePath, lastModifiedTime)

}



