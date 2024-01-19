package org.bogdans.controller

import org.bogdans.model.Point
import org.bogdans.repository.UserRepository
import org.bogdans.service.PointService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/points")
class PointController(
    private val pointService: PointService,
    private val userRepository: UserRepository
) {

    @PostMapping
    fun processPoint(
        @RequestParam x: Double,
        @RequestParam y: Double,
        @RequestParam r: Double,
        @RequestParam currentTime: String,
        principal: Principal
    ): ResponseEntity<Point> {
        val username = principal.name
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found: $username")

        val point = pointService.processPoint(x, y, r, currentTime, user)
        return ResponseEntity.ok(point)
    }
}
