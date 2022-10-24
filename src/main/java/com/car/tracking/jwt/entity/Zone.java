package com.car.tracking.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
public class Zone {
    @Id
    @GeneratedValue
    private long id;
    private boolean isAllow;
    @Column(name = "fromHour", columnDefinition = "TIME")
    @JsonFormat(pattern="hh:mm:ss")
    private Time fromHour;
    @Column(name = "toHour", columnDefinition = "TIME")
    @JsonFormat(pattern="hh:mm:ss")
    private Time toHour;
    private int[] weekDays;
    private boolean notification;
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
    @JsonIgnore
    @OneToMany(mappedBy = "zone")
    private List<PolygonPoint> coordinatList;
}
