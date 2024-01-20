package org.bogdans.dto

data class AuthenticationResponse(
    val token: String,
    val message: String = "Authentication successful."
)
