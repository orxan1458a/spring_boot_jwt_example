package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.entity.LiveData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveDataRepository extends CrudRepository<LiveData,Long> {
}
