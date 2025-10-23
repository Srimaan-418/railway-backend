package com.railway.reservation_system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable; // Add import
import org.springframework.web.bind.annotation.PutMapping;
@CrossOrigin
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	@PutMapping("/{pnrNumber}/cancel")
	public ResponseEntity<?> cancelBooking(@PathVariable String pnrNumber, @AuthenticationPrincipal UserDetails userDetails) {
	    if (userDetails == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
	    }
	    try {
	        Booking cancelledBooking = bookingService.cancelBooking(pnrNumber, userDetails.getUsername());
	        return ResponseEntity.ok(cancelledBooking);
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	@GetMapping("/my-bookings")
	public ResponseEntity<List<Booking>> getMyBookings(@AuthenticationPrincipal UserDetails userDetails) {
	    if (userDetails == null) {
	         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Or handle appropriately
	    }
	    List<Booking> bookings = bookingService.getMyBookings(userDetails.getUsername());
	    return ResponseEntity.ok(bookings);
	}
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Booking newBooking = bookingService.createBooking(bookingRequest, userDetails.getUsername());
            return ResponseEntity.ok(newBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}