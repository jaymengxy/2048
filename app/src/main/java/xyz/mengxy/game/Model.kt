package xyz.mengxy.game

/**
 * Created by Mengxy on 2019-11-27.
 */

data class Score(
        val total: Int,
        val max: Int,
        val score: Int
) {
    val isTotalMax: Boolean
        get() = total == max
}