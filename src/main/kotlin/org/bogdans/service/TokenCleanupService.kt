package org.bogdans.service

import org.bogdans.repository.TokenRepository
import org.bogdans.util.JwtTokenUtil
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class TokenCleanupService(
    private val tokenRepository: TokenRepository,
    private val jwtTokenUtil: JwtTokenUtil
) {
    @Scheduled(fixedDelay = 3600000) // every hour
    fun cleanupExpiredTokens() {
        val allTokens = tokenRepository.findAll()
        allTokens.forEach { token ->
            if (jwtTokenUtil.isTokenExpired(token.token)) {
                tokenRepository.delete(token)
            }
        }
    }
}
