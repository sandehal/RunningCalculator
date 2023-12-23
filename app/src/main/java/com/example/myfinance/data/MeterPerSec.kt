package com.example.myfinance.data

import EnhetFart
import Kmph

class MeterPerSec(val speed: Float): EnhetFart() {
    override fun settKmPh(): Kmph? {
        val kmps = speed/1000
        val kmpm = kmps/60
        val kmph = kmpm/60
        return Kmph(kmph)
    }

    override fun toString(): String {
        return String.format("%.1f", speed).plus("m/s")
    }
    override fun repr(): String {
        return "m/s"
    }
}