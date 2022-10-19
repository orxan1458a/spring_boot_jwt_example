package com.car.tracking.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolygonPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double latitude;
    private double longitude;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;
}
