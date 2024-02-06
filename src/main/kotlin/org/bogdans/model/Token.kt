package org.bogdans.model

import jakarta.persistence.*

/**
 * Entity class representing an authentication token in the system.
 *
 * @property id Unique identifier of the token.
 * @property token The JWT token string.
 * @property user The user to whom the token was issued.
 */
@Entity
@Table(name = "tokens")
data class Token(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val token: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)
