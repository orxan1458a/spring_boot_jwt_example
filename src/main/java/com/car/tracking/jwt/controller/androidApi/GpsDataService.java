package com.car.tracking.jwt.controller.androidApi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class GpsDataService {
    public GpsData[] allGpsData() throws IOException, ParseException {
        GpsData[] allGpsData;
        URL url = new URL("http://localhost:9090/liveAndroidData");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            scanner.close();
            JSONParser parse = new JSONParser();
//            butun melumatlar gpsInfodadir
            JSONArray gpsInfo = (JSONArray) parse.parse(String.valueOf(informationString));
            System.out.println(gpsInfo);
            Gson gson = new Gson();
            allGpsData = gson.fromJson(String.valueOf(gpsInfo), GpsData[].class);

        }
        return allGpsData;
    }
}
