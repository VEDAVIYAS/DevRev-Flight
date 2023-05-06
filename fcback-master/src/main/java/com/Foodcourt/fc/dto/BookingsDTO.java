package com.Foodcourt.fc.dto;

import com.Foodcourt.fc.Entity.User;


public class BookingsDTO {
    private int flightId;

    private int ticketCounts;

    private int bookingCost;


    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getTicketCounts() {
        return ticketCounts;
    }

    public void setTicketCounts(int ticketCounts) {
        this.ticketCounts = ticketCounts;
    }

    public int getBookingCost() {
        return bookingCost;
    }

    public void setBookingCost(int bookingCost) {
        this.bookingCost = bookingCost;
    }

    @Override
    public String toString() {
        return "BookingsDTO{" +
                "flightId=" + flightId +
                ", ticketCounts=" + ticketCounts +
                ", bookingCost=" + bookingCost +
                '}';
    }
}
