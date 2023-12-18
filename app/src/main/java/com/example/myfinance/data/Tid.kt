import android.util.Log

class Tid internal constructor(var timer: Int, var minutter: Int, var sekunder: Int) :
    EnhetTid() {
    private fun hentAntMinutter(): Float {
        val desimalSekunder = (sekunder / 60f)
        val timerTilMinutter = (timer * 60f)
        return minutter + timerTilMinutter + desimalSekunder
    }

    fun kalkulerFart(distanse: EnhetDistanse): Kmph {
        val lengde: Float = distanse.toKilometer().hentDistanseKm()
        val antMinutter = hentAntMinutter()
        val antTimer = antMinutter / 60f
        val fart = lengde / antTimer
        return Kmph(fart)
    }

    override fun toString(): String {
        if (timer == 0) {
            if (minutter == 0) {return "$sekunder s"}
            return "$minutter m $sekunder s"
        }
        return timer.toString() + "h " + minutter + "m " + sekunder + "s"
    }
}