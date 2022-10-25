package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.db.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}
