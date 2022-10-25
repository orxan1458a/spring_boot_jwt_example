package com.car.tracking.jwt.entity.db;

import com.car.tracking.jwt.entity.db.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Transactional
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
    @JsonFormat(pattern="HH:mm:ss dd/MM/yyyy")
    private LocalDateTime currentDateTime;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "car_id")
    private Car car;
}
