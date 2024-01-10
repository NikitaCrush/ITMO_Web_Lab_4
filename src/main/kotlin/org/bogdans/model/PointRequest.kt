package org.bogdans.model

import jakarta.validation.constraints.*

data class PointRequest(
    @field:NotNull @field:Min(-3) @field:Max(3)
    val x: Double,

    @field:NotNull @field:Min(-3) @field:Max(5)
    val y: Double,

    @field:NotNull @field:Min(-3) @field:Max(3)
    val r: Double
)
