package com.example.myfinance.mitm

import Kmph
import MilesPerHour
import MinPerKm
import MinPerMile
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RunConverterViewModel (
    private val _runConverterUiState: MutableStateFlow<RunConverterUiState> = MutableStateFlow(
        RunConverterUiState(
            fraEnhet = "",
            tilEnhet = "",
            fraVerdi = 0.toFloat(),
            tilVerdi = "",
            minutter = 0,
            sekunder = 0.toFloat(),
            fraUnit = null,
            tilUnit = null
        )
    )
): ViewModel(){
    val runConverterUiState =_runConverterUiState.asStateFlow()

    fun createUnit(fraEnhet: String) {
        //Setter opp innsendt enhet
        when (fraEnhet) {
            "km/h" -> {
                _runConverterUiState.value.fraUnit = runConverterUiState.value.fraVerdi?.let {
                    Kmph(
                        it
                    )
                }
                println(runConverterUiState.value.fraUnit)
            }
            "m/h" -> {
                _runConverterUiState.value.fraUnit = runConverterUiState.value.fraVerdi?.let {
                    MilesPerHour(
                        it
                    )
                }
            }
            "min/km" -> {
                _runConverterUiState.value.fraVerdi = 0.toFloat()
                _runConverterUiState.value.fraUnit =
                    runConverterUiState.value.minutter?.let { runConverterUiState.value.sekunder?.let { it1 ->
                        MinPerKm(it,
                            it1
                        )
                    } }
            }
            "min/mile" -> {
                _runConverterUiState.value.fraVerdi = 0.toFloat()
                _runConverterUiState.value.fraUnit =
                    runConverterUiState.value.minutter?.let { runConverterUiState.value.sekunder?.let { it1 ->
                        MinPerMile(it,
                            it1
                        )
                    } }
            }
        }
    }
    fun convertUnit() {
        println("For: " + runConverterUiState.value.fraUnit)
        when (runConverterUiState.value.tilEnhet) {
            "km/h" -> _runConverterUiState.value.tilUnit = runConverterUiState.value.fraUnit?.settKmPh()
            "m/h" -> _runConverterUiState.value.tilUnit = runConverterUiState.value.fraUnit?.settKmPh()?.settMilesPerHour()
            "min/km" -> _runConverterUiState.value.tilUnit = runConverterUiState.value.fraUnit?.settKmPh()?.settMinPerKm()
            "min/mile" -> _runConverterUiState.value.tilUnit = runConverterUiState.value.fraUnit?.settKmPh()?.settMinPerMile()
        }
        println("Etter: " + runConverterUiState.value.tilUnit)

    }
}