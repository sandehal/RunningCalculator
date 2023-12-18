import kotlin.math.roundToInt

class MinPerKm internal constructor(var minutter: Int, var sekunder: Float) : EnhetFart() {
    var desimalSekunder = 0f
    override fun settKmPh(): Kmph {
        desimalSekunder = sekunder / 60
        val x = minutter + desimalSekunder
        val kmph = 60 / x
        return Kmph(kmph)
    }

    override fun toString(): String {
        val sek: String = String.format("%.1f", sekunder)
        return "${minutter}m ${sek}s "
    }

    override fun repr(): String {
        return "min.sek"
    }
}