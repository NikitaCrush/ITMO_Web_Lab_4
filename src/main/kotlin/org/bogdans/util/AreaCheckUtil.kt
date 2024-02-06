package org.bogdans.util

/**
 * Utility object for area check calculations.
 */
object AreaCheckUtil {
    /**
     * Checks if a point (x, y) is within a certain area defined by radius r.
     *
     * @param x X coordinate of the point.
     * @param y Y coordinate of the point.
     * @param r Radius for the area check.
     * @return Boolean indicating if the point is within the area.
     */
    fun check(x: Double, y: Double, r: Double): Boolean {
        return when {
            x <= 0 && y <= 0 && -x <= r / 2 && y >= -r -> true
            x >= 0 && y <= 0 && (x - y) <= r / 2 -> true
            x <= 0 && y >= 0 && (x * x + y * y) <= r * r / 4 -> true
            else -> false
        }
    }
}
