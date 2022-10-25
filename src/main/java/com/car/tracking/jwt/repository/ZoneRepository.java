package com.car.tracking.jwt.repository;
import com.car.tracking.jwt.entity.db.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone,Long> {
}
