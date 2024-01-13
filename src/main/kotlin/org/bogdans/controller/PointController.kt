package org.bogdans.controller

import org.bogdans.model.Point
import org.bogdans.service.PointService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/points")
class PointController(private val pointService: PointService) {

    @PostMapping
    fun processPoint(
        @RequestParam x: Double,
        @RequestParam y: Double,
        @RequestParam r: Double,
        @RequestParam currentTime: String
    ): ResponseEntity<Point> {
        // todo: user should see only points owned by him
        val point = pointService.processPoint(x, y, r, currentTime)
        return ResponseEntity.ok(point)
    }
}
