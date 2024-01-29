package org.bogdans.repository

import org.bogdans.model.Point
import org.bogdans.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PointRepository : JpaRepository<Point, Long> {
    fun findAllByUser(user: User): List<Point>
}
