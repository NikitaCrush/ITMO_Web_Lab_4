package org.bogdans.service

import org.bogdans.dto.UserRegistrationDto
import org.bogdans.model.User
import org.bogdans.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    fun registerUser(userDto: UserRegistrationDto): User {
        val user = User(
            username = userDto.username,
            password = passwordEncoder.encode(userDto.password)
        )
        return userRepository.save(user)
    }
}
