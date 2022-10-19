package com.car.tracking.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Zone {
    @Id
    @GeneratedValue
    private long id;
    private boolean isAllow;
    @Column(name = "fromHour", columnDefinition = "TIMESTAMP")
    private LocalTime fromHour;
    @Column(name = "toHour", columnDefinition = "TIMESTAMP")
    private LocalTime toHour;
    private int[] weekDays;
    private boolean isZoneShow;
    private boolean notification;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "driver_id", nullable = true)
    private Driver driver;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "zone")
    private List<PolygonPoint> coordinatList;
}
