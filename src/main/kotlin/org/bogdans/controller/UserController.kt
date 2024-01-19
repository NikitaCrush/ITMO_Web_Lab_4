package org.bogdans.controller

import org.bogdans.dto.AuthenticationResponse
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

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailsService

) {

    @PostMapping("/register")
    fun registerUser(@RequestBody userDto: UserRegistrationDto): ResponseEntity<Any> {
        return try {
            userService.registerUser(userDto)
            ResponseEntity.ok().build()
        } catch (e: DataIntegrityViolationException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists")
        }
    }


    @PostMapping("/authenticate")
    fun createAuthenticationToken(@RequestBody userDto: UserRegistrationDto): ResponseEntity<AuthenticationResponse> {
        return try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userDto.username, userDto.password))
            val userDetails = userDetailsService.loadUserByUsername(userDto.username)
            val token = jwtTokenUtil.generateToken(userDetails)
            ResponseEntity.ok(AuthenticationResponse(token))
        } catch (ex: Exception) {
            ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(AuthenticationResponse("", "Authentication failed."))
        }
    }



}
