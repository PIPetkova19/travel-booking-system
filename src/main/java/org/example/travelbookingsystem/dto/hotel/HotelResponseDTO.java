package org.example.travelbookingsystem.dto.hotel;

import jakarta.validation.constraints.Positive;

public record HotelResponseDTO(long id,
                               String name,
                               String city,
                               String country,
                               double pricePerNight,
                               int availableRooms) { }
