package com.gradation.databox.core.utils.mapper



fun Long.toText(): String {
    val kiloByte = 1024L
    val megaByte = kiloByte * 1024
    val gigaByte = megaByte * 1024

    return when {
        this < kiloByte -> "$this B"
        this < megaByte -> String.format("%.1f KB", this.toDouble() / kiloByte)
        this < gigaByte -> String.format("%.2f MB", this.toDouble() / megaByte)
        else -> String.format("%.2f GB", this.toDouble() / gigaByte)
    }
}