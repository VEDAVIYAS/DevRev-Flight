package com.Foodcourt.fc.service;

import com.Foodcourt.fc.Entity.Bookings;
import com.Foodcourt.fc.Entity.Flight;
import com.Foodcourt.fc.Repository.BookingsRepository;
import com.Foodcourt.fc.Repository.FlightRepository;
import com.Foodcourt.fc.dto.BookingsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingsServiceImpl implements BookingsService{

    @Autowired
    BookingsRepository bookingsRepository;

    @Autowired
    UserService userService;

    @Autowired
    FlightService flightService;

    @Override
    public String saveBooking(BookingsDTO bookingsDTO) {
        System.out.println(bookingsDTO);
        Bookings bookings=new Bookings();
        bookings.setBookingCost(bookingsDTO.getBookingCost());
        bookings.setFlightId(bookingsDTO.getFlightId());
        bookings.setUser(userService.getCurrentUser());
        bookings.setTicketCounts(bookingsDTO.getTicketCounts());
        String s= flightService.ticketCount(bookingsDTO.getFlightId(),bookingsDTO.getTicketCounts());
        if(s.equals("booked")) {
            bookingsRepository.save(bookings);
        }
        return s;

    }

    @Override
    public List<Bookings> getUserBooking() {
       return bookingsRepository.findAllByUser(userService.getCurrentUser());
    }

    @Override
    public List<Bookings> getFlightBooking(int flightId) {
        return bookingsRepository.findAllByFlightId(flightId);
    }
}
