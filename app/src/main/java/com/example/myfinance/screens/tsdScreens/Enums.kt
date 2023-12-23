package com.example.myfinance.screens.tsdScreens

//FILE IS UNACTIVE
//CONSIDER DELETION
enum class SpeedUnitsEnum {
    Kmph,
    MilesPerHour,
    MinPerKm,
    MinPerMile
}

// Convert enum to user-friendly string
fun SpeedUnitsEnum.toDisplayString(): String {
    return when (this) {
        SpeedUnitsEnum.Kmph -> "Km/h"
        SpeedUnitsEnum.MilesPerHour -> "Mi/h"
        SpeedUnitsEnum.MinPerKm -> "Min/km"
        SpeedUnitsEnum.MinPerMile -> "Min/mile"
    }
}

// Convert string to enum
fun String.toDropdownOption(): SpeedUnitsEnum? {
    return when (this) {
        "Km/h" -> SpeedUnitsEnum.Kmph
        "Mi/h" -> SpeedUnitsEnum.MilesPerHour
        "Min/km" -> SpeedUnitsEnum.MinPerKm
        "Min/mile" -> SpeedUnitsEnum.MinPerMile
        else -> null
    }
}