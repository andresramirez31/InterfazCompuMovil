package com.Booktrace.interfazcompumovil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * ViewModel containing the app data and methods to process the data
 */
class FormViewModel : ViewModel() {
    // Form UI state
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    var name by mutableStateOf("")
        private set

    /*
    * Update the name
    */
    fun updateName(nameUpdate: String){
        name = nameUpdate
    }
}