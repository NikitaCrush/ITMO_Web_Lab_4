package org.bogdans.model

import jakarta.persistence.*

@Entity
class Token(
    @Column
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column
    val token: String,
    @Column
    val isExpired: Boolean,
    @Column
    val isRevoked: Boolean,
    @OneToOne
    val user: User
)
