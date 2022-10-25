package com.car.tracking.jwt.controller;

import com.car.tracking.jwt.entity.db.*;
import com.car.tracking.jwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @PostMapping("/updateZone/{id}")
    public void updateZone(@PathVariable Long id,@RequestBody Zone zone,@RequestBody PolygonPoint polygonPoint){
        List<Zone> list=new ArrayList<>();
        List<Zone> allZones=zoneRepository.findAll();
        for(int i=0;i<allZones.size();i++){
            if(allZones.get(i).getCar().getId()==id){
                allZones.get(i).setAllow(zone.isAllow());
                allZones.get(i).setNotification(zone.isNotification());
                allZones.get(i).setFromHour(zone.getFromHour());
                allZones.get(i).setToHour(zone.getToHour());
            }
        }
        List<PolygonPoint> list2=new ArrayList<>();
        List<PolygonPoint> allPoints=new ArrayList<>();
        for(int i=0;i<allPoints.size();i++){
            if(allPoints.get(i).getZone().getId()==id){
                allPoints.get(i).setLatitude(polygonPoint.getLatitude());
                allPoints.get(i).setLongitude(polygonPoint.getLongitude());
            }
        }
        zoneRepository.save(zone);

    }
    @GetMapping("/allZones")
    public Iterable<Zone> allZones(){

        return zoneRepository.findAll();
    }
    @GetMapping("/allZones/{id}")
    public List<Zone> getZones(@PathVariable Long id ){
        List<Zone> list=new ArrayList<>();
        List<Zone> allZones=zoneRepository.findAll();
        for(int i=0;i<allZones.size();i++){
            if(allZones.get(i).getCar().getId()==id){
                list.add(allZones.get(i));
            }
        }
        return list;
    }
    @GetMapping("/polygonCoordinates/{id}")
    public List<PolygonPoint> getPolygonCoordinates(@PathVariable Long id ){
        List<PolygonPoint> list=new ArrayList<>();
        List<PolygonPoint> allCoordinates=polygonPointRepository.findAll();
        for(int i=0;i<allCoordinates.size();i++){
            if(allCoordinates.get(i).getZone().getId()==id){
                list.add(allCoordinates.get(i));
            }
        }
        return list;
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
