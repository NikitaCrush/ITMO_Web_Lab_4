package org.bogdans.dto

/**
 * DTO for point data.
 *
 * @property x X coordinate.
 * @property y Y coordinate.
 * @property r Radius.
 * @property currentTime Time when the point is created or processed.
 */
data class PointDto(
    val x: Double,
    val y: Double,
    val r: Double,
    val currentTime: String
)