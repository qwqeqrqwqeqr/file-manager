package com.gradation.databox.feature.directory.data.model

sealed interface SortType {
    data class Name(val name: String = "이름") : SortType
    data class Size(val name: String = "파일크기") : SortType
    data class LastModifiedTime(val name: String = "수정날짜") : SortType
    data class CreateTime(val name: String = "생성날짜") : SortType

    fun getTitleName(): String =
        when (this) {
            is Name -> this.name
            is CreateTime -> this.name
            is LastModifiedTime -> this.name
            is Size -> this.name
        }


}
