package com.gradation.databox.core.utils

import android.Manifest
import android.os.Build


val permissionList: List<String> =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        listOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE)

    } else {
        listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

