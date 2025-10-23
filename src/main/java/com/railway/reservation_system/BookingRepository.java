package com.railway.reservation_system;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Counts how many bookings exist for a specific train on a given travel date
    long countByTrainAndTravelDate(Train train, LocalDate travelDate);
    List<Booking> findByUser(User user);
    Optional<Booking> findByPnrNumber(String pnrNumber);
}
