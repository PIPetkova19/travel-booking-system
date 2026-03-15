package org.example.travelbookingsystem.dto.booking;

import org.example.travelbookingsystem.dto.flight.FlightResponseDTO;
import org.example.travelbookingsystem.dto.hotel.HotelResponseDTO;
import org.example.travelbookingsystem.dto.user.UserResponseDTO;

public record BookingResponseDTO(long id,
                                 UserResponseDTO user,
                                 FlightResponseDTO flight,
                                 HotelResponseDTO hotel) {
}
