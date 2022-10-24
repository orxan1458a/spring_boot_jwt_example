package com.car.tracking.jwt.controller;

import com.car.tracking.jwt.entity.*;
import com.car.tracking.jwt.repository.*;
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
    @Autowired
    ZoneRepository zoneRepository;
    @Autowired
    PolygonPointRepository polygonPointRepository;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/addDriver")
    public String addDriver(@RequestBody Driver driver){
        driverRepository.save(driver);
       return "save edildi";
    }
    @PostMapping("/addCar")
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
        liveData.setCar(car);
        liveDataRepository.save(liveData);

        return "save edildi";
    }

    @GetMapping("/allCars")
    public Iterable<Car> allCars(){
        return carRepository.findAll();
    }

    @GetMapping("/deleteAll")
    public String deleteAll(){
        carRepository.deleteById(69L);
        driverRepository.deleteById(70L);
        liveDataRepository.deleteById(71L);
        return "her shey silindi";
    }

    @GetMapping("/allLiveData")
    public Iterable<LiveData> allLiveData(){

        return liveDataRepository.findAll();
    }

    @GetMapping("/allDrivers")
    public Iterable<Driver> allDriver(){
//        System.out.println(driverRepository.findAll());
        return driverRepository.findAll();
    }

    @PostMapping("/addZone")
    public String addZone(@RequestBody Zone zone){
        zoneRepository.save(zone);
        return "Change save";
    }
    @GetMapping("/allZones")
    public Iterable<Zone> allZones(){
        return zoneRepository.findAll();
    }

    @PostMapping("/addCoordinate")
    public String addCoordinate(@RequestBody PolygonPoint polygonPoint){
         polygonPointRepository.save(polygonPoint);
        return "change save";
    }

    @GetMapping("/allCoordinates")
    public Iterable<PolygonPoint> allCoordinates(){
        return polygonPointRepository.findAll();
    }

    @GetMapping("/allUsers")
    public Iterable<User> allUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        userRepository.save(user);
        return "save user";
    }
}
