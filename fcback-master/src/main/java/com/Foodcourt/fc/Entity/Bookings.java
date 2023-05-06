package com.Foodcourt.fc.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "flight_id")
    private int flightId;

    @Column(name = "ticketCounts")
    private int ticketCounts;

    @Column(name = "bookingCost")
    private int bookingCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    public Bookings() {
    }

    public Bookings(int flightId, int ticketCounts, int bookingCost, User user) {
        this.flightId = flightId;
        this.ticketCounts = ticketCounts;
        this.bookingCost = bookingCost;
        this.user = user;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
