package org.bogdans.repository

import org.bogdans.model.Point
import org.bogdans.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository interface for point entities. Extends JpaRepository to provide standard database operations.
 */
@Repository
interface PointRepository : JpaRepository<Point, Long> {
    /**
     * Finds all points for a given user.
     *
     * @param user User entity.
     * @return List of Point entities.
     */
    fun findAllByUser(user: User): List<Point>
}
