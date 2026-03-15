package org.example.travelbookingsystem.facade;

import org.example.travelbookingsystem.model.Booking;
import org.example.travelbookingsystem.model.Flight;
import org.example.travelbookingsystem.model.Hotel;
import org.example.travelbookingsystem.model.User;
import org.example.travelbookingsystem.service.BookingService;
import org.example.travelbookingsystem.service.FlightService;
import org.example.travelbookingsystem.service.HotelService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingFacade {
    private final FlightService flightService;
    private final HotelService hotelService;
    private final BookingService bookingService;

    public BookingFacade(FlightService flightService, HotelService hotelService, BookingService bookingService) {
        this.flightService = flightService;
        this.hotelService = hotelService;
        this.bookingService = bookingService;
    }

    public void bookBestTrip(User user,String destination,double maxPrice, double pricePerNight) {
        Flight flight = flightService.findBestFlights(destination,maxPrice);
        if (flight == null) throw new IllegalArgumentException("Flight not available");
        Hotel hotel=hotelService.findBestHotels(destination,pricePerNight);
        if (hotel == null) throw new IllegalArgumentException("Hotel not available");
        bookingService.createBooking(user,flight,hotel);
        System.out.println("Best trip booked");
    }

    public void bookTrip(User user,long flightId, long hotelId) {
        Flight flight = flightService.reserveFlightEntity(flightId);
        if (flight == null) throw new IllegalArgumentException("Flight not available");
        Hotel hotel=hotelService.reserveHotelEntity(hotelId);
        if (hotel == null) throw new IllegalArgumentException("Hotel not available");
        bookingService.createBooking(user,flight,hotel);
        System.out.println("Booked a trip");
    }

    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
