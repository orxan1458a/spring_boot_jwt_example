package com.car.tracking.jwt.repository;

import com.car.tracking.jwt.controller.androidApi.GpsData;
import com.car.tracking.jwt.entity.Car;
import com.car.tracking.jwt.entity.LiveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveDataRepository extends JpaRepository<LiveData, Long> {
    //    @Query("SELECT u FROM LiveData u WHERE u.id = ?1")
//    LiveData findbyId(Long id);
    LiveData findByCar_Id(long carId);
}
