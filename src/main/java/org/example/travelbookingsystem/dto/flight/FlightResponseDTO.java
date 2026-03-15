package org.example.travelbookingsystem.dto.flight;

import java.time.LocalDateTime;

//ot sarvar kam client
public record FlightResponseDTO (long id,
                                 String origin,
                                String destination,
                                LocalDateTime departure,
                                LocalDateTime arrival,
                                double price,
                                int availableSeats){ }
