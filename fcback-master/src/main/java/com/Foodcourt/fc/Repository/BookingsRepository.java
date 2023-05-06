package com.Foodcourt.fc.Repository;


import com.Foodcourt.fc.Entity.Bookings;
import com.Foodcourt.fc.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings,Integer> {

    public List<Bookings> findAllByUser(User user);

    public List<Bookings> findAllByFlightId(int FlightId);
}
