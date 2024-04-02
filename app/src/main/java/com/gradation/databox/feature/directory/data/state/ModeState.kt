package com.gradation.databox.feature.directory.data.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.gradation.databox.feature.directory.data.model.ModeType

class ModeState {

    var modeType: ModeType by mutableStateOf(ModeType.View)


    val updateModeType: (ModeType) -> Unit = { modeType = it }


}