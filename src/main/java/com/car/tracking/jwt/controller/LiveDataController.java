package com.car.tracking.jwt.controller;

import com.car.tracking.jwt.entity.Car;
import com.car.tracking.jwt.entity.Driver;
import com.car.tracking.jwt.entity.LiveData;
import com.car.tracking.jwt.entity.User;
import com.car.tracking.jwt.repository.CarRepository;
import com.car.tracking.jwt.repository.DriverRepository;
import com.car.tracking.jwt.repository.LiveDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class LiveDataController {
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    LiveDataRepository liveDataRepository;
    @PostMapping("/add-driver")
    public String addDriver(@RequestBody Driver driver){
        driverRepository.save(driver);
       return "save edildi";
    }
    @PostMapping("/add-car")
    public String addCar(@RequestBody Car car){
        carRepository.save(car);
        LiveData liveData=new LiveData();
        liveData.setCar(car);
        liveData.setAltitude(1);
        liveData.setHeading(23);
        liveData.setLongitude(43);
        liveData.setLatitude(45);
        liveData.setSpeed(90);
        liveData.setCurrentDateTime(LocalDateTime.now());
        liveDataRepository.save(liveData);

        return "save edildi";
    }
    @GetMapping("/deleteAll")
    public String deleteAll(){
        carRepository.deleteById(69L);
        driverRepository.deleteById(70L);
        liveDataRepository.deleteById(71L);
        return "her shey silindi";
    }
    @GetMapping("/all-live-data")
    public Iterable<LiveData> allLiveData(){
        return liveDataRepository.findAll();
    }
}
