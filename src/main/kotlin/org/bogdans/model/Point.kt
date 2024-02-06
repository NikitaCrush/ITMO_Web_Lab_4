package org.bogdans.model

import jakarta.persistence.*

/**
 * Entity class representing a point in the system.
 *
 * @property id Unique identifier of the point.
 * @property x X coordinate of the point.
 * @property y Y coordinate of the point.
 * @property r Radius for the point.
 * @property result Result of the area check for the point.
 * @property currentTime Time when the point was created or processed.
 * @property user User who created the point.
 */
@Entity
class Point(
    @Column
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column
    val x: Double,
    @Column
    val y: Double,
    @Column
    val r: Double,
    @Column
    val result: Boolean,
    @Column(name = "\"current_time\"")
    val currentTime: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)
