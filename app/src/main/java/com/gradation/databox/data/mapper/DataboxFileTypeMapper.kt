package com.gradation.databox.data.mapper

import com.gradation.databox.core.utils.mapper.toLocalDateTime
import com.gradation.databox.data.model.DataboxFileType
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes


fun File.toDataboxFileType():DataboxFileType =
    this.let {  file ->
            val path = Paths.get(file.absolutePath)
            val attrs: BasicFileAttributes =
                Files.readAttributes(path, BasicFileAttributes::class.java)

            if (attrs.isDirectory) {
                DataboxFileType.DirectoryType(
                    itemSize = file.listFiles()?.size?.toLong() ?: 0L,
                    file = file,
                    path = path,
                    attrs = attrs,
                    name = file.name,
                    absolutePath = file.absolutePath,
                    creationTime = attrs.creationTime().toLocalDateTime(),
                    lastAccessTime = attrs.lastAccessTime().toLocalDateTime(),
                    lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                )
            } else {
                DataboxFileType.FileType(
                    file = file,
                    path = path,
                    attrs = attrs,
                    name = file.name,
                    absolutePath = file.absolutePath,
                    size = attrs.size(),
                    creationTime = attrs.creationTime().toLocalDateTime(),
                    lastAccessTime = attrs.lastAccessTime().toLocalDateTime(),
                    lastModifiedTime = attrs.lastModifiedTime().toLocalDateTime()
                )
            }
    }


