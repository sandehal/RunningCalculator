class Miles internal constructor(var miles: Float) : EnhetDistanse() {
    override fun hentDistanseKm(): Float {
        return (miles * 1.609).toFloat()
    }

    override fun settDistanse(d: Float) {
        miles = d
    }

    override fun toKilometer() : Kilometer {
        return Kilometer(miles*1.609f)
    }

    override fun toString(): String {
        return "$miles miles"
    }

    override fun repr(): String {
        return "Miles"
    }
}