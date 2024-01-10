package org.bogdans.service

import org.bogdans.model.User
import org.bogdans.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun findUserByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}
