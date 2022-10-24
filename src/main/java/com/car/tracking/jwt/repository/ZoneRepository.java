package com.car.tracking.jwt.repository;
import com.car.tracking.jwt.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ZoneRepository extends JpaRepository<Zone,Long> {
}
