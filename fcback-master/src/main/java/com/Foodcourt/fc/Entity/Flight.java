package com.Foodcourt.fc.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "flightName")
    private String flightName;


    @Column(name = "startingLocation")
    private String startingLocation;

    @Column(name = "destination")
    private String destination;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="departureTime")
    private Date departureTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "landingTime")
    private Date landingTime;

    @Column(name = "ticketCost")
    private int ticketCost;

    @Column(name = "totalSeats")
    private int totalSeats;

    @Column(name = "availableSeats")
    private int availableSeats;

    public Flight() {
    }

    public Flight(String flightName, String startingLocation, String destination, Date departureTime, Date landingTime, int ticketCost, int totalSeats, int availableSeats) {
        this.flightName = flightName;
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.ticketCost = ticketCost;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(Date landingTime) {
        this.landingTime = landingTime;
    }

    public int getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(int ticketCost) {
        this.ticketCost = ticketCost;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
