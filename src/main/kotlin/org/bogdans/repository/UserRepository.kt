package org.bogdans.repository

import org.bogdans.model.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository interface for user entities. Extends JpaRepository to provide standard database operations.
 */
interface UserRepository : JpaRepository<User, Long> {
    /**
     * Finds a user by username.
     *
     * @param username Username of the user to find.
     * @return User entity if found, otherwise null.
     */
    fun findByUsername(username: String): User?
}
