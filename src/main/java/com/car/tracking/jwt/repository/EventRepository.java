package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
}
