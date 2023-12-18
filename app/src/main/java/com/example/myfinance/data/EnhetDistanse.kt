abstract class EnhetDistanse : Enhet() {
    abstract fun hentDistanseKm(): Float
    abstract fun settDistanse(d: Float)
    abstract fun repr(): String
    abstract fun toKilometer(): Kilometer
}