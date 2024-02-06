package org.bogdans.service

import org.bogdans.dto.UserRegistrationDto
import org.bogdans.model.User
import org.bogdans.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Service class for user-related business logic.
 *
 * @property userRepository Repository for accessing user data.
 * @property passwordEncoder Encoder for hashing passwords.
 */
@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    /**
     * Registers a new user in the system.
     *
     * @param userDto Data Transfer Object containing user registration information.
     * @return The saved User entity.
     */
    fun registerUser(userDto: UserRegistrationDto): User {
        val user = User(
            username = userDto.username,
            password = passwordEncoder.encode(userDto.password) // Encode the password before saving.
        )
        return userRepository.save(user) // Save the user entity to the database.
    }
}