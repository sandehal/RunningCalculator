import com.example.myfinance.data.Knots
import com.example.myfinance.data.MeterPerSec

class Kmph(var kmph: Float) : EnhetFart() {
    fun settMinPerMile(): MinPerMile {
        val x = kmph
        val faktor = 96.58.toFloat()
        val minPerMile = faktor / x
        val minutter = minPerMile.toInt()
        val desimalSekunder = minPerMile - minutter
        val sekunder = desimalSekunder * 60
        return MinPerMile(minutter, sekunder)
    }

    fun settMinPerKm(): MinPerKm {
        val x = kmph
        val minPerKm = 60 / x
        val minutter = minPerKm.toInt()
        val desimalSekunder = minPerKm - minutter
        val sekunder = desimalSekunder * 60
        return MinPerKm(minutter, sekunder)
    }

    fun settMilesPerHour(): MilesPerHour {
        val x = kmph
        val faktor = 0.62.toFloat()
        val milesPerHour = x * faktor
        return MilesPerHour(milesPerHour)
    }

    fun settKnots(): Knots {
        val x = kmph
        val faktor = 0.539957f
        val knots = x*faktor
        return Knots(knots)
    }

    fun settMeterPerSec(): MeterPerSec {
        val mps = kmph / 3.6f
        return MeterPerSec(mps)
    }

    override fun settKmPh(): Kmph {
        return this
    }

    override fun toString(): String {
        return String.format("%.1f", kmph).plus("km/h")
    }

    override fun repr(): String {
        return "kmph"
    }
}