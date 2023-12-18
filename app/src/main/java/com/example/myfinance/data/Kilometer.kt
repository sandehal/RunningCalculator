class Kilometer internal constructor(var km: Float) : EnhetDistanse() {
    override fun hentDistanseKm(): Float {
        return km
    }

    override fun settDistanse(d: Float) {
        km = d
    }

    override fun toKilometer(): Kilometer {
        return this
    }

    override fun toString(): String {
        return km.toString() + "km"
    }

    override fun repr(): String {
        return "Km"
    }
}