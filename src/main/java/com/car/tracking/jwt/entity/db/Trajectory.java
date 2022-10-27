package com.car.tracking.jwt.entity.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trajectory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double latitude;
    private double longitude;
    @Column(name = "current_date_time", columnDefinition = "TIMESTAMP")
    @JsonFormat(pattern="HH:mm:ss dd/MM/yyyy")
    private LocalDateTime currentDateTime;
    @ManyToOne
    @JoinColumn(name = "liveData_id", nullable = false)
    private LiveData liveData;
}
