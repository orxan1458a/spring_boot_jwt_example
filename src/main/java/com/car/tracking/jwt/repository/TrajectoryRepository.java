package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.db.Trajectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajectoryRepository extends JpaRepository<Trajectory,Long> {
}
