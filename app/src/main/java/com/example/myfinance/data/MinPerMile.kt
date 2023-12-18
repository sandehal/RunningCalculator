class MinPerMile internal constructor(var minutter: Int, var sekunder: Float) : EnhetFart() {
    var desimalSekunder: Float

    init {
        desimalSekunder = (sekunder / 60)
    }

    override fun settKmPh(): Kmph {
        val x = minutter + desimalSekunder
        val kmph = 95.8.toFloat() / x
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