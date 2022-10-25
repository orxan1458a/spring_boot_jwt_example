package com.car.tracking.jwt.entity.db;

import com.car.tracking.jwt.entity.db.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    @JsonIgnore
    @OneToOne(mappedBy = "driver",fetch = FetchType.LAZY)
    private Car car;


}
