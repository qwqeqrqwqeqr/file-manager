package com.gradation.databox.data.file.utils

import com.gradation.databox.core.utils.mapper.toLocalDateTime
import com.gradation.databox.data.file.model.DataboxFileType
import com.gradation.databox.data.file.model.FileExtension
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes

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
        val attrs: BasicFileAttributes = Files.readAttributes(path, BasicFileAttributes::class.java)


        if (file.isDirectory) {
            DataboxFileType.DirectoryType(
                itemSize = file.listFiles()?.size?.toLong() ?: 0L,
                name = file.name,
                absolutePath = file.absolutePath,
                size= calcDirectorySize(file),
                creationTime= attrs.creationTime().toLocalDateTime(),
                lastAccessTime=attrs.lastModifiedTime().toLocalDateTime(),
                lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
            )
        } else {
            when (file.getExtension()) {
                FileExtension.JPG -> {
                    DataboxFileType.ImageType(
                        name = file.name,
                        absolutePath = file.absolutePath,
                        size = file.length(),
                        creationTime= attrs.creationTime().toLocalDateTime(),
                        lastAccessTime=attrs.lastModifiedTime().toLocalDateTime(),
                        lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                    )
                }

                FileExtension.PNG -> {
                    DataboxFileType.ImageType(
                        name = file.name,
                        absolutePath = file.absolutePath,
                        size = file.length(),
                        creationTime= attrs.creationTime().toLocalDateTime(),
                        lastAccessTime=attrs.lastModifiedTime().toLocalDateTime(),
                        lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                    )
                }

                FileExtension.JPEG -> {
                    DataboxFileType.ImageType(
                        name = file.name,
                        absolutePath = file.absolutePath,
                        size = file.length(),
                        creationTime= attrs.creationTime().toLocalDateTime(),
                        lastAccessTime=attrs.lastModifiedTime().toLocalDateTime(),
                        lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                    )
                }

                FileExtension.DEFAULT -> {
                    DataboxFileType.FileType(
                        name = file.name,
                        absolutePath = file.absolutePath,
                        size = file.length(),
                        creationTime= attrs.creationTime().toLocalDateTime(),
                        lastAccessTime=attrs.lastModifiedTime().toLocalDateTime(),
                        lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                    )
                }


            }
        }
    }

