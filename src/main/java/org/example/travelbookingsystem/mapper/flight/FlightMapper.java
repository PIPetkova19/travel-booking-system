package org.example.travelbookingsystem.mapper.flight;

import org.example.travelbookingsystem.dto.flight.FlightRequestDTO;
import org.example.travelbookingsystem.dto.flight.FlightResponseDTO;
import org.example.travelbookingsystem.model.Flight;
import org.springframework.stereotype.Component;

//sprong se griji za jivota na obekta ne nie
@Component
public class FlightMapper {
    public Flight toEntity(FlightRequestDTO dto) {
        Flight flight = new Flight();
        flight.setOrigin(dto.origin());
        flight.setDestination(dto.destination());
        flight.setDeparture(dto.departure());
        flight.setArrival(dto.arrival());
        flight.setPrice(dto.price());
        flight.setAvailableSeats(dto.availableSeats());
        return flight;
    }

    public FlightResponseDTO toDTO(Flight flight) {
         return new FlightResponseDTO(
                flight.getId(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getPrice(),
                flight.getAvailableSeats()
                );
    }
}