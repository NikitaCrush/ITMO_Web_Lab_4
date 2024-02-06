package org.bogdans.model

import jakarta.persistence.*

/**
 * Entity class representing a user in the system.
 *
 * @property id Unique identifier of the user.
 * @property username Unique username of the user.
 * @property password Password of the user (should be stored in a hashed form).
 */
@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String
)
