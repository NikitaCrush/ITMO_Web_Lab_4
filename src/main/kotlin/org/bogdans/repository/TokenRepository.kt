package org.bogdans.repository

import org.bogdans.model.Token
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TokenRepository : JpaRepository<Token, Long> {
    fun findByToken(token: String): Optional<Token>
}
