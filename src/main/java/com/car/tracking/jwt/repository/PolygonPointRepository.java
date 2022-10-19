package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.PolygonPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolygonPointRepository extends CrudRepository<PolygonPoint,Long> {
}
