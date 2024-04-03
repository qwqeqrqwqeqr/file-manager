package com.gradation.databox.core.common.permission

import android.Manifest
import android.os.Build


val permissionList: List<String> =

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        listOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO,
        )
    else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R)
        listOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE)
    else
        listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
