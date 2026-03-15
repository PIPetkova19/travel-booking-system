package org.example.travelbookingsystem.repository;

import org.example.travelbookingsystem.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    Hotel getHotelById(Long hotelId);
}
