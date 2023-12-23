package com.example.myfinance.data

import EnhetFart
import Kmph

class Knots(val speed: Float): EnhetFart() {
    override fun settKmPh(): Kmph {
        return Kmph(speed*1.852f)
    }

    override fun toString(): String {
        return String.format("%.1f", speed).plus("kn")
    }
    override fun repr(): String {
        return "knots"
    }
}