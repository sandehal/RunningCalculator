package com.example.myfinance.mitm

import Enhet
import EnhetDistanse
import EnhetFart
import EnhetTid
import Kmph

data class ToSpeedConversionUiState(var timer: Int,
                                    var minutter: Int,
                                    var sekunder: Int,
                                    var distanseEnhet: EnhetDistanse,
                                    var distanse: Float,
                                    var resultUnit: Kmph,
                                    var displayUnit: String,
                                    var displayResult: EnhetFart) {
}

data class ToDistanceConversionUiState(var fartEnhet: EnhetFart, val tidEnhet: EnhetTid) {
}

data class ToTimeConversionUiState(var timer: Int, var minutter: Int, var sekunder: Int, val fartEnhet: EnhetFart) {
}