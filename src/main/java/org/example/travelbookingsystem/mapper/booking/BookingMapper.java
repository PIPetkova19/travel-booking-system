package org.example.travelbookingsystem.mapper.booking;

import org.example.travelbookingsystem.dto.booking.BookingResponseDTO;
import org.example.travelbookingsystem.dto.flight.FlightResponseDTO;
import org.example.travelbookingsystem.dto.hotel.HotelResponseDTO;
import org.example.travelbookingsystem.dto.user.UserResponseDTO;
import org.example.travelbookingsystem.model.Booking;
import org.example.travelbookingsystem.model.Flight;
import org.example.travelbookingsystem.model.Hotel;
import org.example.travelbookingsystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public Booking toEntity(User user, Flight flight, Hotel hotel) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setHotel(hotel);
        return booking;
    }

    public BookingResponseDTO toDTO(Booking booking) {
        User user = booking.getUser();
        Flight flight = booking.getFlight();
        Hotel hotel = booking.getHotel();

        UserResponseDTO userDTO = new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
        FlightResponseDTO flightDTO = new FlightResponseDTO(
                flight.getId(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getPrice(),
                flight.getAvailableSeats()
        );
        HotelResponseDTO hotelDTO = new HotelResponseDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getCity(),
                hotel.getCountry(),
                hotel.getPricePerNight(),
                hotel.getAvailableRooms()
        );

        return new BookingResponseDTO(
                booking.getId(),
                userDTO,
                flightDTO,
                hotelDTO
        );
    }
}