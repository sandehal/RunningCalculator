package com.example.myfinance.mitm

import Kilometer
import Kmph
import Tid
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DistanceConversionViewModel(
    private val _ToSpeedConversionUiState: MutableStateFlow<ToSpeedConversionUiState> = MutableStateFlow(
        ToSpeedConversionUiState(
            timer = 0,
            minutter = 0,
            sekunder = 0,
            distanse = 0f,
            distanseEnhet = Kilometer(0f),
            resultUnit = Kmph(0f)
        )
    )
): ViewModel() {
    val ToSpeedConversionUiState = _ToSpeedConversionUiState.asStateFlow()

    fun CalculateSpeed() {
        val timeObj = Tid(ToSpeedConversionUiState.value.timer,
            ToSpeedConversionUiState.value.minutter,
            ToSpeedConversionUiState.value.sekunder)
        _ToSpeedConversionUiState.value.distanseEnhet.settDistanse(ToSpeedConversionUiState.value.distanse)
        _ToSpeedConversionUiState.value.resultUnit  = timeObj.kalkulerFart(ToSpeedConversionUiState.value.distanseEnhet)
    }
}