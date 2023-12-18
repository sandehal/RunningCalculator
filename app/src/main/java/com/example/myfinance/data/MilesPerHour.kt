class MilesPerHour internal constructor(var milesPerHour: Float) : EnhetFart() {
    override fun settKmPh(): Kmph {
        val x = milesPerHour
        val faktor = 1.609
        val kmphDouble = x * faktor
        val kmph = kmphDouble.toFloat()
        return Kmph(kmph)
    }

    override fun toString(): String {
        return milesPerHour.toString() + "miles per hour"
    }

    override fun repr(): String {
        return "mph"
    }
}