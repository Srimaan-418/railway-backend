package com.railway.reservation_system;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {
	@Transactional // Ensures the operation is atomic
	public Booking cancelBooking(String pnrNumber, String userEmail) {
	    // Find the booking by PNR
	    Booking booking = bookingRepository.findByPnrNumber(pnrNumber)
	            .orElseThrow(() -> new RuntimeException("Booking with PNR " + pnrNumber + " not found."));

	    // Verify the booking belongs to the current user
	    if (!booking.getUser().getEmail().equals(userEmail)) {
	        throw new RuntimeException("You are not authorized to cancel this booking.");
	    }

	    // Check if the booking is already cancelled or if it's too late to cancel (add time-based rules later if needed)
	    if (booking.getStatus() == BookingStatus.CANCELLED) {
	        throw new RuntimeException("This booking is already cancelled.");
	    }

	    // Update the status
	    booking.setStatus(BookingStatus.CANCELLED);

	    // Save the updated booking
	    return bookingRepository.save(booking);
	}
	public List<Booking> getMyBookings(String userEmail) {
	    User user = userRepository.findByEmail(userEmail)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	    return bookingRepository.findByUser(user);
	}
	
    @Autowired private BookingRepository bookingRepository;
    @Autowired private TrainRepository trainRepository;
    @Autowired private UserRepository userRepository;

    public Booking createBooking(BookingRequest bookingRequest, String userEmail) {
        // 1. Find the user and train from the database
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Train train = trainRepository.findById(bookingRequest.getTrainId())
                .orElseThrow(() -> new RuntimeException("Train not found"));

        // 2. Check for seat availability
        long existingBookings = bookingRepository.countByTrainAndTravelDate(train, bookingRequest.getTravelDate());
        if (existingBookings >= train.getTotalSeats()) {
            throw new RuntimeException("No seats available on this train for the selected date.");
        }

        // 3. If seats are available, create the booking
        Booking newBooking = new Booking();
        newBooking.setUser(user);
        newBooking.setTrain(train);
        newBooking.setTravelDate(bookingRequest.getTravelDate());
        newBooking.setBookingDate(LocalDate.now());
        newBooking.setStatus(BookingStatus.CONFIRMED);
        newBooking.setPnrNumber(UUID.randomUUID().toString().toUpperCase().substring(0, 10)); // Generate a PNR

        return bookingRepository.save(newBooking);
    }
}