package org.bogdans.model

import jakarta.persistence.*

@Entity
class Point(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
