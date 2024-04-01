package com.gradation.databox.feature.permission.data.model



data class Permission(
    val name:PermissionText,
    val isGranted: Boolean
)
enum class PermissionText(val value:String){
    WRITE_EXTERNAL_STORAGE(value = WRITE_EXTERNAL_STORAGE_TEXT),
    READ_EXTERNAL_STORAGE(value = READ_EXTERNAL_STORAGE_TEXT),
    MANAGE_EXTERNAL_STORAGE(value = MANAGE_EXTERNAL_STORAGE_TEXT),
    UNKNOWN_PERMISSION(value = UNKNOWN_PERMISSION_TEXT)
}

fun String.toPermission():PermissionText=
    when(this){
        "android.permission.WRITE_EXTERNAL_STORAGE" -> PermissionText.WRITE_EXTERNAL_STORAGE
        "android.permission.READ_EXTERNAL_STORAGE" -> PermissionText.READ_EXTERNAL_STORAGE
        "android.permission.MANAGE_EXTERNAL_STORAGE" -> PermissionText.MANAGE_EXTERNAL_STORAGE
        else -> PermissionText.UNKNOWN_PERMISSION
    }

const val WRITE_EXTERNAL_STORAGE_TEXT = "저장소 쓰기 권한"
const val READ_EXTERNAL_STORAGE_TEXT = "저장소 읽기 권한"
const val MANAGE_EXTERNAL_STORAGE_TEXT = "저장소 관리 권한"
const val UNKNOWN_PERMISSION_TEXT = "알 수 없는 권한"