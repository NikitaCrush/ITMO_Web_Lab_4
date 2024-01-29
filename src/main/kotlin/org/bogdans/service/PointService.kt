package org.bogdans.service

import org.bogdans.model.Point
import org.bogdans.model.User
import org.bogdans.repository.PointRepository
import org.bogdans.util.AreaCheckUtil
import org.springframework.stereotype.Service

@Service
class PointService(private val pointRepository: PointRepository) {

    fun processPoint(x: Double, y: Double, r: Double, currentTime: String, user: User): Point {
        val result = AreaCheckUtil.check(x, y, r)
        val point = Point(0, x, y, r, result, currentTime, user)
        return pointRepository.save(point)
    }

    fun clearPointsForUser(user: User) {
        val points = pointRepository.findAllByUser(user)
        pointRepository.deleteAll(points)
    }

    fun getPointsForUser(user: User): List<Point> {
        return pointRepository.findAllByUser(user)
    }
}
