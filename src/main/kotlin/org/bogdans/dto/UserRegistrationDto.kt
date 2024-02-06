package org.bogdans.dto

/**
 * DTO for user registration data.
 *
 * @property username Username for the new user.
 * @property password Password for the new user.
 */
data class UserRegistrationDto(
    val username: String,
    val password: String
)
