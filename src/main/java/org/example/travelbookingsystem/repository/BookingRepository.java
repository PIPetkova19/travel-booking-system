package org.example.travelbookingsystem.repository;

import org.example.travelbookingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    Booking findBookingById(long id);
    Booking getBookingById(long id);
}
