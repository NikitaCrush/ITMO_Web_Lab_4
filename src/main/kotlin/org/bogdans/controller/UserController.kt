package org.bogdans.controller

import org.bogdans.model.User
import org.bogdans.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping("/{username}")
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<User> {
        val user = userService.findUserByUsername(username)
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity.notFound().build()
    }
}
