package org.bogdans.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtTokenUtil {
    // Replace with a strong, randomly generated secret key
    private val secretKeyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).encoded
    private val jwtParser = Jwts.parserBuilder().setSigningKey(secretKeyBytes).build()

    fun generateToken(userDetails: UserDetails): String {
        return Jwts.builder()
            .setSubject(userDetails.username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // todo: move to reasonably named constant // 10 hours validity
            .signWith(Keys.hmacShaKeyFor(secretKeyBytes), SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = jwtParser.parseClaimsJws(token).body.expiration
        return expiration.before(Date())
    }

    fun extractUsername(token: String): String {
        return jwtParser.parseClaimsJws(token).body.subject
    }
}
