package pt.afsmeira.ffadventuresheet.util

import java.time.Instant
import kotlin.random.Random

object Dice {
    private val random = Random(Instant.now().toEpochMilli())

    fun roll(times: Int, sides: Int, handicap: Int = 0): Int {
        return (1..times).map { random.nextInt(1, sides) }.sum() + handicap
    }
}
