package com.car.tracking.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    @JsonBackReference
    @OneToOne(mappedBy = "driver")
    private Car car;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    private List<Zone> zones;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    private List<Event> events;
}
