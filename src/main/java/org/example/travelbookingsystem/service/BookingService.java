package org.example.travelbookingsystem.service;

import org.example.travelbookingsystem.dto.booking.BookingResponseDTO;
import org.example.travelbookingsystem.mapper.booking.BookingMapper;
import org.example.travelbookingsystem.model.Booking;
import org.example.travelbookingsystem.model.Flight;
import org.example.travelbookingsystem.model.Hotel;
import org.example.travelbookingsystem.model.User;
import org.example.travelbookingsystem.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    public void createBooking(User user, Flight flight, Hotel hotel) {
        Booking booking = bookingMapper.toEntity(user, flight, hotel);
         bookingRepository.save(booking);
        System.out.println("Booking created");
    }

    public BookingResponseDTO getBookingById(long id) {
        Booking booking= bookingRepository.getBookingById(id);
        return bookingMapper.toDTO(booking);
    }

    public void removeBookingById(long id) {
        bookingRepository.deleteById(id);
        System.out.println("Booking removed");
    }

    public void updateBooking(long id, User user, Flight flight, Hotel hotel) {
        Booking booking = bookingRepository.getBookingById(id);

        booking.setUser(user);
        booking.setFlight(flight);
        booking.setHotel(hotel);

         bookingRepository.save(booking);
        System.out.println("Booking updated");
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}

