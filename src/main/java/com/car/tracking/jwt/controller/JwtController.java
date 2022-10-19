package com.car.tracking.jwt.controller;

import com.car.tracking.jwt.entity.JwtRequest;
import com.car.tracking.jwt.entity.JwtResponse;
import com.car.tracking.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
////        System.out.println(jwtRequest.);
//        jwtRequest.setUserName("admin");
//        jwtRequest.setUserPassword("admin");
        return jwtService.createJwtToken(jwtRequest);
    }
}
