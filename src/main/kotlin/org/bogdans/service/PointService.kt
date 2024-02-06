package org.bogdans.service

import org.bogdans.model.Point
import org.bogdans.model.User
import org.bogdans.repository.PointRepository
import org.bogdans.util.AreaCheckUtil
import org.springframework.stereotype.Service

/**
 * Service class for point-related business logic.
 *
 * @property pointRepository Repository for accessing point data.
 */
@Service
class PointService(private val pointRepository: PointRepository) {

    /**
     * Processes and saves a new point.
     *
     * @param x X coordinate of the point.
     * @param y Y coordinate of the point.
     * @param r Radius for the point.
     * @param currentTime Time when the point was created or processed.
     * @param user User who created the point.
     * @return The saved Point entity.
     */
    fun processPoint(x: Double, y: Double, r: Double, currentTime: String, user: User): Point {
        val result = AreaCheckUtil.check(x, y, r)
        val point = Point(0, x, y, r, result, currentTime, user)
        return pointRepository.save(point)
    }

    /**
     * Clears all points for a given user.
     *
     * @param user User entity.
     */
    fun clearPointsForUser(user: User) {
        val points = pointRepository.findAllByUser(user)
        pointRepository.deleteAll(points)
    }

    /**
     * Retrieves all points for a given user.
     *
     * @param user User entity.
     * @return List of Point entities.
     */
    fun getPointsForUser(user: User): List<Point> {
        return pointRepository.findAllByUser(user)
    }
}
