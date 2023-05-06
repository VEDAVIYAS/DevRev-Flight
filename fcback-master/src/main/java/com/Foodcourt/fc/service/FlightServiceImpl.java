package com.Foodcourt.fc.service;

import com.Foodcourt.fc.Entity.Flight;
import com.Foodcourt.fc.Repository.FlightRepository;
import com.Foodcourt.fc.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    FlightRepository flightRepository;

    @Override
    public void saveFlight(FlightDTO flightDTO) {
        Flight flight=new Flight();
        flight.setFlightName(flightDTO.getFlightName());
        flight.setStartingLocation(flightDTO.getStartingLocation());
        flight.setDestination(flightDTO.getDestination());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setLandingTime(flightDTO.getLandingTime());
        flight.setTotalSeats(flightDTO.getTotalSeats());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setTicketCost(flightDTO.getTicketCost());
        flightRepository.save(flight);
    }

    @Override
    public List<Flight> fetchAll()
    {
        return flightRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> fetchDeparture(Date departure_time) {
         return flightRepository.findAllByDepartureTime(departure_time);
    }

    @Override
    public String ticketCount(int id,int ticketCounts) {
        Flight flight=flightRepository.findById(id);
        int n=flight.getAvailableSeats()-ticketCounts;
        if(n<0)
        {
            return "seats not enough";
        }
        flight.setAvailableSeats(n);
        flightRepository.save(flight);
        return "booked";
    }

    @Override
    public List<Flight> getFlightsByDate(Date date) {
        List<Flight> res = new ArrayList<>();
        List<Flight> allFlights = flightRepository.findAll();
        for(Flight flight:allFlights){
            Date flightDate = flight.getDepartureTime();
            if(flightDate.getTime()-date.getTime()>0){
                res.add(flight);
            }
        }
        return res;
    }
}
