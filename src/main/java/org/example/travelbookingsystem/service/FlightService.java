package org.example.travelbookingsystem.service;
import org.example.travelbookingsystem.dto.flight.FlightRequestDTO;
import org.example.travelbookingsystem.dto.flight.FlightResponseDTO;
import org.example.travelbookingsystem.mapper.flight.FlightMapper;
import org.example.travelbookingsystem.model.Flight;
import org.springframework.stereotype.Service;
import java.util.List;
import org.example.travelbookingsystem.repository.FlightRepository;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
private final FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public void createFlight(FlightRequestDTO dto) {
        Flight flight = flightMapper.toEntity(dto);
        flightRepository.save(flight);
         flightMapper.toDTO(flight);
        System.out.println("Flight created");
    }

    public FlightResponseDTO reserveFlight(long flightId) {
        Flight flight = flightRepository.getFlightById(flightId);

        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("Flight is full");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);

        return flightMapper.toDTO(flight);
    }

    public List<FlightResponseDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDTO)
                .toList();
    }

    public FlightResponseDTO getFlightById(long id) {
        Flight flight = flightRepository.getFlightById(id);
        return flightMapper.toDTO(flight);
    }

    public FlightResponseDTO updateFlight(long id, FlightRequestDTO dto) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        flight.setOrigin(dto.origin());
        flight.setDestination(dto.destination());
        flight.setDeparture(dto.departure());
        flight.setArrival(dto.arrival());
        flight.setPrice(dto.price());
        flight.setAvailableSeats(dto.availableSeats());

        flightRepository.save(flight);

        return flightMapper.toDTO(flight);
    }

    public void removeFlightById(long id) {
        flightRepository.deleteById(id);
        System.out.println("Removed flight with id " + id);
    }


    public Flight findBestFlights(String destination, double maxPrice) {
        List<Flight> flightsByDestination = flightRepository.findAll().stream()
                .filter(flight -> flight.getDestination().equals(destination))
                .filter(flight -> flight.getAvailableSeats() > 0)
                .toList();

        if (flightsByDestination.isEmpty()) {
            throw new RuntimeException("No flights available to destination " + destination);
        }
        List<Flight> flightsByPrice = flightRepository.findAll().stream()
                .filter(f -> f.getPrice() <= maxPrice)
                .filter(flight -> flight.getAvailableSeats() > 0)
                .toList();
        if (flightsByPrice.isEmpty()) {
            throw new RuntimeException("No flights available for this price " + maxPrice);
        }

        return flightsByDestination.stream()
                .filter(flightsByPrice::contains)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No matching flights found"));
    }

    public Flight getFlightEntityById(long id) {
        return flightRepository.getFlightById(id);
    }

    public Flight reserveFlightEntity(long hotelId) {
        Flight flight = flightRepository.getFlightById(hotelId);

        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("Flight is full");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);

        return flight;
    }
}
