package com.gradation.databox.data.file.utils

const val MAX_BYTE_LENGTH = 255

fun String.getByteSize(): Int = toByteArray(Charsets.UTF_8).size


fun String.validateFileName(): Boolean {
    return (isNotEmpty()
        && getByteSize() < MAX_BYTE_LENGTH
        && !contains('/')
        && !contains('\\')
        && !contains('?')
        && !contains(':')
        && !contains('"')
        && !contains('<')
        && !contains('>')
        && !contains('|')
        && !contains('*')
    )
}