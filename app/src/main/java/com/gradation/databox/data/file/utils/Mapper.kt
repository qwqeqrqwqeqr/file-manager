package com.gradation.databox.data.file.utils

import com.gradation.databox.core.utils.mapper.toLocalDateTime
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.data.file.model.FileExtension
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.FileTime

fun String.toDataboxFileTypeList(): List<DataboxFileType> =
    File(this)
        .listFiles()
        ?.asSequence()
        ?.filterNotNull()
        ?.filter { it.canRead() }
        ?.map { it.toDataboxFileType() }
        ?.toList() ?: emptyList()


fun File.toDataboxFileType(): DataboxFileType =
    this.let { file ->
        val path = Paths.get(file.absolutePath)
        val lastModifiedTime: FileTime = Files.getLastModifiedTime(path)

        if (file.isDirectory) {
            DataboxFileType.DirectoryType(
                itemSize = file.listFiles()?.size?.toLong() ?: 0L,
                name = file.name,
                absolutePath = file.absolutePath,
                lastModifiedTime = lastModifiedTime.toLocalDateTime()
            )
        } else {
            when (file.getExtension()) {
                FileExtension.JPG -> {
                    DataboxFileType.ImageType(
                        name = file.name,
                        absolutePath = file.absolutePath,
                        size = file.length(),
                        lastModifiedTime = lastModifiedTime.toLocalDateTime()
                    )
                }

                FileExtension.PNG -> {
                    DataboxFileType.ImageType(
                        name = file.name,
                        absolutePath = file.absolutePath,
                        size = file.length(),
                        lastModifiedTime =lastModifiedTime.toLocalDateTime()
                    )
                }

                FileExtension.JPEG -> {
                    DataboxFileType.ImageType(
                        name = file.name,
                        absolutePath = file.absolutePath,
                        size = file.length(),
                        lastModifiedTime = lastModifiedTime.toLocalDateTime()
                    )
                }

                FileExtension.DEFAULT -> {
                    DataboxFileType.FileType(
                        name = file.name,
                        absolutePath = file.absolutePath,
                        size = file.length(),
                        lastModifiedTime = lastModifiedTime.toLocalDateTime()
                    )
                }


            }
        }
    }

