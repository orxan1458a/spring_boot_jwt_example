package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.db.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
}
