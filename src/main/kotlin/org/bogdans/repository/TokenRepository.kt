package org.bogdans.repository

import org.bogdans.model.Token
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository interface for token entities. Extends JpaRepository to provide standard database operations.
 */
interface TokenRepository : JpaRepository<Token, Long> {
    /**
     * Finds a token by its string value.
     *
     * @param tokenValue The string value of the token.
     * @return The Token entity if found, otherwise null.
     */
    fun findByToken(tokenValue: String): Token?
}
