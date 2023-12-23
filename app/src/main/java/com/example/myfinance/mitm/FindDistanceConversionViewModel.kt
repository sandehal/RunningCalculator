package com.example.myfinance.mitm

import Kmph
import Tid
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FindDistanceConversionViewModel(
    val _ToDistanceConversionUiState: MutableStateFlow<ToDistanceConversionUiState> = MutableStateFlow(
        ToDistanceConversionUiState(
            fartEnhet = Kmph(0f),
            tidEnhet = Tid(0,0,0)
        )
    )
): ViewModel() {
    val ToDistanceConversionUiState = _ToDistanceConversionUiState.asStateFlow()
}