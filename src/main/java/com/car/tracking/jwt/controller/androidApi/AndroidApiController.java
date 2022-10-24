package com.car.tracking.jwt.controller.androidApi;

import com.car.tracking.jwt.entity.LiveData;
import com.car.tracking.jwt.repository.CarRepository;
import com.car.tracking.jwt.repository.LiveDataRepository;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


@RestController
public class AndroidApiController {
    @Autowired
    GpsDataService gpsDataService;
    @Autowired
    LiveDataRepository liveDataRepository;
    @Autowired
    CarRepository carRepository;

    @GetMapping("/liveAndroidData")
    public List<LiveData> liveAndroidData() throws IOException, ParseException {

        return liveDataRepository.findAll();
    }


    @GetMapping("/live")
    public GpsData[] live() throws IOException, ParseException {
        for (int i = 0; i < gpsDataService.allGpsData().length; i++) {
            LiveData liveData = liveDataRepository.findByCar_Id(gpsDataService.allGpsData()[i].getId());
            liveData.setLatitude(gpsDataService.allGpsData()[i].getLatitude());
            liveData.setLongitude(gpsDataService.allGpsData()[i].getLongitude());
            liveDataRepository.save(liveData);
        }
        return gpsDataService.allGpsData();
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
