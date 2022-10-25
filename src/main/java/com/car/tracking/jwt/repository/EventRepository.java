package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.db.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}
