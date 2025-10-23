package com.railway.reservation_system;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    private LocalDate travelDate;

    private LocalDate bookingDate;

    @Column(unique = true)
    private String pnrNumber;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
