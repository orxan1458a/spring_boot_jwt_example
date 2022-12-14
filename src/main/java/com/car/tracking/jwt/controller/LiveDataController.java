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
    @Autowired
    TrajectoryRepository trajectoryRepository;
    @PostMapping("/addDriver")
    public String addDriver(@RequestBody Driver driver) {
        driverRepository.save(driver);
        return "save edildi";
    }

    @PostMapping("/addCar")
    public String addCar(@RequestBody Car car) {
        carRepository.save(car);
        LiveData liveData = new LiveData();
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
    public Iterable<Car> allCars() {
        return carRepository.findAll();
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        carRepository.deleteById(69L);
        driverRepository.deleteById(70L);
        liveDataRepository.deleteById(71L);
        return "her shey silindi";
    }

    @GetMapping("/allLiveData")
    public Iterable<LiveData> allLiveData() {
        System.out.println("------");
        List<LiveData> dbLiveData= liveDataRepository.findAll();
        for(int i=0;i<dbLiveData.size();i++){
            Trajectory trajectory=new Trajectory();
            trajectory.setLiveData(dbLiveData.get(i));
            trajectory.setLatitude(dbLiveData.get(i).getLatitude());
            trajectory.setLongitude(dbLiveData.get(i).getLongitude());
            trajectory.setCurrentDateTime(dbLiveData.get(i).getCurrentDateTime());
            trajectoryRepository.save(trajectory);
        }
        return liveDataRepository.findAll();
    }

    @PostMapping("/createZone/{car_id}")
    public void createZone(@PathVariable long car_id,@RequestBody Zone zone){
        System.out.println(car_id);
        Car car=carRepository.findById(car_id).get();
        zone.setCar(car);
        zoneRepository.save(zone);
    }

    @PostMapping("/createPolygon/{car_id}")
    public void createPolygonPoints(@PathVariable long car_id,@RequestBody List<PolygonPoint> polygonPoints){

        Zone zone=zoneRepository.findAll().get(zoneRepository.findAll().size()-1);
       for(int i=0;i<polygonPoints.size();i++) {
           PolygonPoint polygonPoint=polygonPoints.get(i);
           polygonPoint.setZone(zone);
           polygonPointRepository.save(polygonPoint);
       }
    }



    @PostMapping("/getCarTrajectories")
    public List<Trajectory> getCarTrajectories(@RequestBody long id){
        System.out.println(id);
        System.out.println(liveDataRepository.findByCar_Id(id).getLatitude());
       long liveData_id= liveDataRepository.findByCar_Id(id).getId();
       List<Trajectory> dbTrajectoryPoints=trajectoryRepository.findAll();
       List<Trajectory> list=new ArrayList<>();
       for(int i=0;i<dbTrajectoryPoints.size();i++){
           if(dbTrajectoryPoints.get(i).getLiveData().getId()==liveData_id){
               list.add(dbTrajectoryPoints.get(i));
           }
       }
        return list;
    }

    @GetMapping("/allDrivers")
    public Iterable<Driver> allDriver() {
//        System.out.println(driverRepository.findAll());
        return driverRepository.findAll();
    }

    @PostMapping("/addZone")
    public String addZone(@RequestBody Zone zone) {
        zoneRepository.save(zone);
        return "Change save";
    }

    @PostMapping("/updateZone")
    public void updateZone(@RequestBody Zone zone) {
        System.out.println(zone.toString());
        List<PolygonPoint> points = new ArrayList<>();
        Zone _zone = zoneRepository.findById(zone.getId()).get();
        _zone.setFromHour(zone.getFromHour());
        _zone.setToHour(zone.getToHour());
        _zone.setNotification(zone.isNotification());
        _zone.setAllow(zone.isAllow());
        _zone.setWeekDays(zone.getWeekDays());
        zoneRepository.save(_zone);
    }

    @PostMapping("/deleteZone")
    public void deleteZone(@RequestBody long id) {
        System.out.println("zone deleted");
        zoneRepository.deleteById(id);
    }

    @GetMapping("/showZonePolygon/{id}")
    public List<PolygonPoint> showZonePolygon(@PathVariable long id) {
        List<PolygonPoint> dbData = polygonPointRepository.findAll();
        List<PolygonPoint> list = new ArrayList<>();
        for (int i = 0; i < dbData.size(); i++) {
            if (dbData.get(i).getZone().getId() == id) {
                list.add(dbData.get(i));
            }
        }
        System.out.println("zone showed");
        return list;
    }
    @GetMapping("/showAllZones/{id}")
    public List<List<PolygonPoint>> showAllZOnes(@PathVariable long id) {
        List<Zone> dbZoneData = zoneRepository.findAll();
        List<Zone> zoneList = new ArrayList<>();
        for (int i = 0; i < dbZoneData.size(); i++) {
            if (dbZoneData.get(i).getCar().getId() == id) {
                zoneList.add(dbZoneData.get(i));
            }
        }
        System.out.println(zoneList.size()+ " zone list size");
        List<PolygonPoint> dbPointData = polygonPointRepository.findAll();
        List<List<PolygonPoint>> pointList = new ArrayList<>();
        for (int i = 0; i < zoneList.size(); i++) {
            List<PolygonPoint> points=new ArrayList<>();
            pointList.add(points);
            for (int j = 0; j < dbPointData.size(); j++) {
                if (zoneList.get(i).getId() == dbPointData.get(j).getZone().getId()) {
                    pointList.get(i).add(dbPointData.get(j));
                    System.out.println( "--------------");

                }
            }
        }
        System.out.println("all zone showed");
        return pointList;
    }


    @PostMapping("/updatePolygon")
    public void updatePolygon(@RequestBody List<PolygonPoint> polygonPoints) {
        List<PolygonPoint> list = polygonPointRepository.findAll();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < polygonPoints.size(); j++) {
                if (list.get(i).getZone().getId() == polygonPoints.get(j).getZone().getId()) {
                    polygonPointRepository.deleteById(list.get(i).getId());
                    break;
                }
            }
        }

        for (int i = 0; i < polygonPoints.size(); i++) {
            PolygonPoint polygon_Point = new PolygonPoint();
            polygon_Point.setLatitude(polygonPoints.get(i).getLatitude());
            polygon_Point.setLongitude(polygonPoints.get(i).getLongitude());
            polygon_Point.setZone(zoneRepository.findById(polygonPoints.get(i).getZone().getId()).get());
            polygonPointRepository.save(polygon_Point);
        }
    }

    @GetMapping("/allZones")
    public Iterable<Zone> allZones() {

        return zoneRepository.findAll();
    }

    @GetMapping("/allZones/{id}")
    public List<Zone> getZones(@PathVariable Long id) {
        List<Zone> list = new ArrayList<>();
        List<Zone> allZones = zoneRepository.findAll();
        for (int i = 0; i < allZones.size(); i++) {
            if (allZones.get(i).getCar().getId() == id) {
                list.add(allZones.get(i));
            }
        }
        return list;
    }

    @GetMapping("/polygonCoordinates/{id}")
    public List<PolygonPoint> getPolygonCoordinates(@PathVariable Long id) {
        List<PolygonPoint> list = new ArrayList<>();
        List<PolygonPoint> allCoordinates = polygonPointRepository.findAll();
        for (int i = 0; i < allCoordinates.size(); i++) {
            if (allCoordinates.get(i).getZone().getId() == id) {
                list.add(allCoordinates.get(i));
            }
        }
        return list;
    }

    @PostMapping("/addCoordinate")
    public String addCoordinate(@RequestBody PolygonPoint polygonPoint) {
        polygonPointRepository.save(polygonPoint);
        return "change save";
    }

    @GetMapping("/allCoordinates")
    public Iterable<PolygonPoint> allCoordinates() {
        return polygonPointRepository.findAll();
    }

    @GetMapping("/allUsers")
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        userRepository.save(user);
        return "save user";
    }
}
