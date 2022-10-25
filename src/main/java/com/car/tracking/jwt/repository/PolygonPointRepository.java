package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.db.PolygonPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolygonPointRepository extends JpaRepository<PolygonPoint,Long> {
}
