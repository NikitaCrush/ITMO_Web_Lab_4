package org.bogdans.repository

import org.bogdans.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.relational.core.mapping.Table
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}
