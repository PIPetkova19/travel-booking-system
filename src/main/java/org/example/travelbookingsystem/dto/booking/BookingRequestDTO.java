package org.example.travelbookingsystem.dto.booking;

public record BookingRequestDTO(    long userId,
                                    long flightId,
                                    long hotelId) {
}
