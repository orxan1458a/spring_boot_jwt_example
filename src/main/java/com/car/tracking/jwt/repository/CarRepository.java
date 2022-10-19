package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car,Long> {
}
