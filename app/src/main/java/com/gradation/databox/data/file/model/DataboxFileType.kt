package com.gradation.databox.data.file.model

import java.io.File
import java.nio.file.Path
import java.time.LocalDateTime

sealed class DataboxFileType(
    val file: File,
    val path: Path,
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
        name: String,
        absolutePath: String,
        creationTime: LocalDateTime,
        lastAccessTime: LocalDateTime,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(
        file, path, name, absolutePath, creationTime, lastAccessTime, lastModifiedTime
    )


    class FileType(
        val size: Long,
        file: File,
        path: Path,
        name: String,
        absolutePath: String,
        creationTime: LocalDateTime,
        lastAccessTime: LocalDateTime,
        lastModifiedTime: LocalDateTime,
    ) : DataboxFileType(
        file, path, name, absolutePath, creationTime, lastAccessTime, lastModifiedTime
    )

}



