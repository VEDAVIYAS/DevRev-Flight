package com.Foodcourt.fc.service;

import com.Foodcourt.fc.Entity.Bookings;
import com.Foodcourt.fc.dto.BookingsDTO;

import java.util.List;

public interface BookingsService {
    public String saveBooking(BookingsDTO bookingsDTO);

    public List<Bookings> getUserBooking();

    public List<Bookings> getFlightBooking(int flightId);
}
