package com.car.tracking.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String address;
    private String event;
    @Column(name = "current_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime currentDateTime;
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = true)
    @JsonBackReference
    private Car car;
    private double latitude;
    private double longitude;
    private double altitude;
    private int speed;
    private int heading;

}
