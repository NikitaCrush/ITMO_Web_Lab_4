package org.bogdans.dto

/**
 * DTO for authentication response.
 *
 * @property token JWT token.
 * @property message Response message.
 */
data class AuthenticationResponse(
    val token: String,
    val message: String = "Authentication successful."
)
