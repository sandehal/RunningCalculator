package com.example.myfinance.data

import EnhetDistanse
import Kilometer

class Meter internal constructor(var meter: Float) : EnhetDistanse() {
    override fun hentDistanseKm(): Float {
        return meter
    }

    override fun settDistanse(d: Float) {
        meter = d
    }

    override fun toKilometer(): Kilometer {
        return Kilometer(meter/1000)
    }

    override fun toString(): String {
        return "$meter m"
    }

    override fun repr(): String {
        return "Meter"
    }
}