package com.car.tracking.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Transactional
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String carModel;
    private String carBrand;
    private String carColor;
    private String carNumber;
    private boolean notification;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @OneToMany(mappedBy = "car")
    private List<Event> events;
    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private List<Zone> zones;
    @JsonIgnore
    @OneToOne(mappedBy = "car",fetch = FetchType.LAZY)
    private LiveData liveData;

}
