package com.car.tracking.jwt.controller.androidApi;

import com.car.tracking.jwt.entity.db.LiveData;
import com.car.tracking.jwt.repository.CarRepository;
import com.car.tracking.jwt.repository.LiveDataRepository;
import com.car.tracking.jwt.repository.UserRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
public class AndroidApiController {
    @Autowired
    GpsDataService gpsDataService;
    @Autowired
    LiveDataRepository liveDataRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/liveAndroidData")
    public List<LiveData> liveAndroidData() throws IOException, ParseException {
userRepository.deleteById(17l);
        return liveDataRepository.findAll();
    }




    @PostMapping("/liveAndroidData")
    public String data(@RequestBody LiveData liveData) {
        LiveData liveDataModel = liveDataRepository.findByCar_Id(liveData.getId());
        liveDataModel.setLatitude(liveData.getLatitude());
        liveDataModel.setLongitude(liveData.getLongitude());
        liveDataModel.setSpeed(liveData.getSpeed());
        liveDataModel.setHeading(liveData.getHeading());
        liveDataModel.setCurrentDateTime(liveData.getCurrentDateTime());
        liveDataModel.setAltitude(liveData.getAltitude());
        liveDataRepository.save(liveDataModel);

        return "live data save";
    }

}
