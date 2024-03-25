package com.gradation.databox.data.file.utils

import android.os.Environment
import com.gradation.databox.data.file.model.FileExtension
import com.gradation.databox.data.file.model.PathTree
import java.io.File
import java.util.LinkedList


fun String.getPathTreeList(): List<PathTree> {

    val pathTreeList: LinkedList<PathTree> = LinkedList()

    val dataAbsolutePath = Environment.getExternalStorageDirectory().absolutePath

    split(File.separator).reduce { absolutePath, currentPath ->
        pathTreeList.add(
            PathTree(
                absolutePath = "$absolutePath/$currentPath",
                name = currentPath
            )
        )
        "$absolutePath/$currentPath"
    }


    return if (contains(dataAbsolutePath)) {
        pathTreeList.filterNot {
            dataAbsolutePath.split(File.separator).contains(it.name)
        }.toMutableList().also {
            it.add(0, PathTree(name = "sdcard", absolutePath = dataAbsolutePath))
        }
    } else pathTreeList
}

fun File.getExtension(): FileExtension {
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