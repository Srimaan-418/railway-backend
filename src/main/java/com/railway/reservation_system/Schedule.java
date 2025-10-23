package com.railway.reservation_system;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    @Column(name = "stop_number")
    private int stopNumber; // The order of the stop in the route (e.g., 1, 2, 3...)
}