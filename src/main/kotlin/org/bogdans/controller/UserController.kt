package org.bogdans.controller

import org.bogdans.dto.AuthenticationResponse
import org.bogdans.dto.MessageResponse
import org.bogdans.dto.UserRegistrationDto
import org.bogdans.service.UserService
import org.bogdans.util.JwtTokenUtil
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*

/**
 * Controller class for user-related operations.
 *
 * @property userService Service for user-related business logic.
 * @property authenticationManager Manager for the authentication process.
 * @property jwtTokenUtil Utility class for JWT operations.
 * @property userDetailsService Service for user details retrieval.
 */
@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailsService
) {

    /**
     * Endpoint for user registration.
     *
     * @param userDto Data Transfer Object containing user registration information.
     * @return ResponseEntity indicating the result of the registration operation.
     */
    @PostMapping("/register")
    fun registerUser(@RequestBody userDto: UserRegistrationDto): ResponseEntity<Any> {
        return try {
            userService.registerUser(userDto) // Attempt to register the user.
            ResponseEntity.ok().build() // Return OK response if successful.
        } catch (e: DataIntegrityViolationException) {
            // Handle case where username is already in use.
            ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists")
        }
    }

    /**
     * Endpoint for user authentication.
     *
     * @param userDto Data Transfer Object containing user authentication information.
     * @return ResponseEntity with the authentication token or error message.
     */
    @PostMapping("/authenticate")
    fun createAuthenticationToken(@RequestBody userDto: UserRegistrationDto): ResponseEntity<AuthenticationResponse> {
        return try {
            // Authenticate the user with the provided username and password.
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userDto.username, userDto.password))
            val userDetails = userDetailsService.loadUserByUsername(userDto.username) // Load user details.
            val token = jwtTokenUtil.generateToken(userDetails) // Generate JWT token.
            ResponseEntity.ok(AuthenticationResponse(token)) // Return the token in the response.
        } catch (ex: Exception) {
            // Handle authentication failure.
            ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(AuthenticationResponse("", "Authentication failed."))
        }
    }

    /**
     * Endpoint for user logout. Note: JWT token is stateless, so client-side should handle the token clearance.
     *
     * @return ResponseEntity indicating the result of the logout operation.
     */
    @PostMapping("/logout")
    fun logout(): ResponseEntity<MessageResponse> {
        // No server-side operation needed for JWT logout.
        return ResponseEntity.ok(MessageResponse("Logout successful. Please delete your token client-side."))
    }
}
