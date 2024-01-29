package org.bogdans.controller

import org.bogdans.dto.PointDto
import org.bogdans.model.Point
import org.bogdans.repository.UserRepository
import org.bogdans.service.PointService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api")
class PointController(
    private val pointService: PointService,
    private val userRepository: UserRepository
) {

    @PostMapping("/points")
    fun processPoint(
        @RequestBody pointDto: PointDto,
        principal: Principal
    ): ResponseEntity<Point> {
        val username = principal.name
        val user =
            userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found: $username")

        val point = pointService.processPoint(pointDto.x, pointDto.y, pointDto.r, pointDto.currentTime, user)
        return ResponseEntity.ok(point)
    }

    @PostMapping("/points/clear")
    fun clearUserPoints(principal: Principal): ResponseEntity<Any> {
        val username = principal.name
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found: $username")
        pointService.clearPointsForUser(user)
        return ResponseEntity.ok("Points cleared successfully.")
    }

    @GetMapping("/points")
    fun getUserPoints(principal: Principal): ResponseEntity<List<Point>> {
        val username = principal.name
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found: $username")
        val points = pointService.getPointsForUser(user)
        return ResponseEntity.ok(points)
    }
}
