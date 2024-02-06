package org.bogdans.service

import org.bogdans.model.User
import org.bogdans.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SpringUser


/**
 * Custom user details service implementing UserDetailsService to load user-specific data.
 *
 * @property userRepository Repository for accessing user data.
 */
@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    /**
     * Loads the user details required by Spring Security framework.
     *
     * @param username Username of the user to load.
     * @return UserDetails containing user information.
     * @throws UsernameNotFoundException if user not found.
     */
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")
        return SpringUser(user.username, user.password, emptyList())
    }
}
