package com.car.tracking.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LiveData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double latitude;
    private double longitude;
    private double altitude;
    private int speed;
    private int heading;
    @Column(name = "current_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime currentDateTime;
//    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
}
