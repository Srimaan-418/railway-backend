package com.railway.reservation_system;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {
    private Long trainId;
    private LocalDate travelDate;
}