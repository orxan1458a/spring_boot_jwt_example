package com.car.tracking.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String carModel;
    private String carBrand;
    private String carColor;
    private String carNumber;
    private boolean allZoneShow;
    private boolean notification;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;
    @JsonIgnore
    @OneToOne(mappedBy = "car")
    private LiveData liveData;

}
