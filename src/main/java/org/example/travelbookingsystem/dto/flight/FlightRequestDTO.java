package org.example.travelbookingsystem.dto.flight;

import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

//ot klient kam sarvar
public record FlightRequestDTO(
         String origin,
         String destination,
         LocalDateTime departure,
         LocalDateTime arrival,
         @Positive
         double price,
         @Positive
         int availableSeats) { }
