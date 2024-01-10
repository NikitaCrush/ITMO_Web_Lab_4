package org.bogdans.model

import jakarta.persistence.*

@Entity
data class Point(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val x: Double,
    val y: Double,
    val r: Double,
    val result: Boolean,
    val currentTime: String
)
