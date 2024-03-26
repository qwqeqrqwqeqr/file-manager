package com.gradation.databox.data.file

import android.util.Log
import java.io.File

fun getDirectorySize(directory: File): Long {
    var length: Long = 0
    directory.listFiles()?.forEach { file ->
        length += if (file.isFile)
            file.length()
        else getDirectorySize(file)
    }
    return length;
}


fun createDirectory(path: String, name: String): Boolean {
    File(path + File.separator + name).also {
        return try {
            if (!it.exists()) it.mkdirs()
            else false
        } catch (error: Error) {
            return false
        }
    }
}

fun List<String>.deleteFile(): Boolean {
    return try {
        map { path -> File(path) }.forEach { file -> file.delete() }
        true
    } catch (error: Error) {
        return false
    }
}


fun moveFile(pathList:List<String>, targetPath:String): Boolean {

    val targetDirectory = File(targetPath)
    return try {
        pathList.map { path -> File(path) }.forEach {
            it.renameTo(targetDirectory)
        }
        true
    }  catch (error: Error) {
        return false
    }
}
fun copyFile(pathList:List<String>, targetPath:String): Boolean {

    val targetDirectory = File(targetPath)
    return try {
        pathList.map { path -> File(path) }.forEach {
            it.copyTo(targetDirectory)
        }
        true
    }  catch (error: Error) {
        return false
    }
}