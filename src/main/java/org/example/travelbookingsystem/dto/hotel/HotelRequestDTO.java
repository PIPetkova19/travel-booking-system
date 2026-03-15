package org.example.travelbookingsystem.dto.hotel;

import jakarta.validation.constraints.Positive;

public record HotelRequestDTO(String name,
                              String city,
                              String country,
                              double pricePerNight,
                              @Positive
                              int availableRooms) { }
