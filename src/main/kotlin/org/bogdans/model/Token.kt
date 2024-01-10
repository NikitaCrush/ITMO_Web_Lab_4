package org.bogdans.model

import jakarta.persistence.*

@Entity
class Token(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val token: String,
    val expired: Boolean,
    val revoked: Boolean,
    @ManyToOne
    val user: User
)
