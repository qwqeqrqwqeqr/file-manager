package com.gradation.databox.feature.directory.data

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gradation.databox.core.ui.navigation.Key.DIRECTORY_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DirectoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {



    private var _path : MutableStateFlow<String> = MutableStateFlow(savedStateHandle[DIRECTORY_KEY]?:"")
    val path: StateFlow<String> = _path.asStateFlow()
}