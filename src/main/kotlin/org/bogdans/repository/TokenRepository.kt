package org.bogdans.repository

import org.bogdans.model.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.relational.core.mapping.Table
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    fun findByToken(token: String): Optional<Token>
}
