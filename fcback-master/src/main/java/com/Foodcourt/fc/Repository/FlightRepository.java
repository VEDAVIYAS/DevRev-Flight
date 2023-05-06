package com.Foodcourt.fc.Repository;

import com.Foodcourt.fc.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {


    public List<Flight> findAllByDepartureTime(Date departure_time);

    public Flight findById(int id);


}
