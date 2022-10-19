package com.car.tracking.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String address;
    private String event;
    @Column(name = "current_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime currentDateTime;
    private double latitude;
    private double longitude;
    private double altitude;
    private int speed;
    private int heading;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
