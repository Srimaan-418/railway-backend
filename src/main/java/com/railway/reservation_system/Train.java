package com.railway.reservation_system;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "trains")
@Data
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainName;

    @Column(unique = true)
    private String trainNumber;

    private int totalSeats;
}
