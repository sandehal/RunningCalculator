package com.example.myfinance.mitm

import Enhet
import EnhetFart

data class RunConverterUiState(
    var fraEnhet: String,
    var tilEnhet: String,
    var fraVerdi: Float?,
    var tilVerdi: String,
    var minutter: Int?,
    var sekunder: Float?,

    var fraUnit: EnhetFart?,
    var tilUnit: EnhetFart?
) {
}