package org.bogdans.controller

import org.bogdans.dto.UserRegistrationDto
import org.bogdans.service.UserService
import org.bogdans.util.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
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
        userService.registerUser(userDto) // what if duplicate key exceiption?
        return ResponseEntity.ok().build()
    }

    @PostMapping("/authenticate")
    fun createAuthenticationToken(@RequestBody userDto: UserRegistrationDto): ResponseEntity<Any> /* don't use <Any> */ {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userDto.username, userDto.password))
            val userDetails = userDetailsService.loadUserByUsername(userDto.username)
            val token = jwtTokenUtil.generateToken(userDetails)
            return ResponseEntity.ok(mapOf("token" to token))
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }



}
