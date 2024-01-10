package org.bogdans.repository

import org.bogdans.model.Point
import org.springframework.data.jpa.repository.JpaRepository

interface PointRepository : JpaRepository<Point, Long>
