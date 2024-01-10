package org.bogdans.model

import jakarta.persistence.*

@Entity(name = "\"User\"")
data class User(
    @Column
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column
    val username: String,
    @Column
    val password: String // This should be a hashed password
)
