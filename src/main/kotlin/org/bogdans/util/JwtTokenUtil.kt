package org.bogdans.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

/**
 * Utility class for JWT operations such as token generation and validation.
 */
@Service
class JwtTokenUtil {
    private val secretKeyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).encoded
    private val jwtParser = Jwts.parserBuilder().setSigningKey(secretKeyBytes).build()
    private val tenHoursValidity = 1000 * 60 * 60 * 10

    /**
     * Generates a JWT token for the given UserDetails.
     * @param userDetails UserDetails
     * @return String JWT token
     */
    fun generateToken(userDetails: UserDetails): String {
        // Build JWT token with subject, issued date, expiration, and sign it.
        return Jwts.builder()
            .setSubject(userDetails.username) // Set the username as the subject of the token.
            .setIssuedAt(Date()) // Set the issue date to the current date.
            .setExpiration(Date(System.currentTimeMillis() + tenHoursValidity)) // Set the expiration date.
            .signWith(Keys.hmacShaKeyFor(secretKeyBytes), SignatureAlgorithm.HS256) // Sign the token with HS256 algorithm and secret key.
            .compact() // Compact it to a URL-safe string.
    }

    /**
     * Validates the token against userDetails and expiration.
     * @param token String JWT token
     * @param userDetails UserDetails
     * @return Boolean token validity
     */
    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token) // Extract username from token.
        // Validate token by checking username match and token expiration.
        return username == userDetails.username && !isTokenExpired(token)
    }

    /**
     * Checks if the token is expired.
     * @param token String JWT token
     * @return Boolean token expiration status
     */
    private fun isTokenExpired(token: String): Boolean {
        // Parse the token and check if the current date is after the expiration date.
        val expiration = jwtParser.parseClaimsJws(token).body.expiration
        return expiration.before(Date())
    }

    /**
     * Extracts the username (subject) from the token.
     * @param token String JWT token
     * @return String username
     */
    fun extractUsername(token: String): String {
        return jwtParser.parseClaimsJws(token).body.subject
    }
}
