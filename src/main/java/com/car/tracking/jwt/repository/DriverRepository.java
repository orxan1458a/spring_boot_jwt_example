package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Driver,Long> {
}
