package org.bogdans.util

object AreaCheckUtil {
    fun check(x: Double, y: Double, r: Double): Boolean {
        return when {
            x >= 0 && y >= 0 && x <= r && y <= r / 2 -> true
            x <= 0 && y <= 0 && (-x + -y) <= r -> true
            x <= 0 && y >= 0 && (x * x + y * y) <= r * r -> true
            else -> false
        }
    }
}
