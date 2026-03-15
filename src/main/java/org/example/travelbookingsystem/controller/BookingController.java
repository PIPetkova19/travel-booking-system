package org.example.travelbookingsystem.controller;

import org.example.travelbookingsystem.facade.BookingFacade;
import org.example.travelbookingsystem.mapper.booking.BookingMapper;
import org.example.travelbookingsystem.mapper.flight.FlightMapper;
import org.example.travelbookingsystem.mapper.hotel.HotelMapper;
import org.example.travelbookingsystem.mapper.user.UserMapper;
import org.example.travelbookingsystem.model.Booking;
import org.example.travelbookingsystem.model.Flight;
import org.example.travelbookingsystem.model.Hotel;
import org.example.travelbookingsystem.model.User;
import org.example.travelbookingsystem.service.BookingService;
import org.example.travelbookingsystem.service.FlightService;
import org.example.travelbookingsystem.service.HotelService;
import org.example.travelbookingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingFacade bookingFacade;
    private final UserService userService;
    private final FlightService flightService;
    private final HotelService hotelService;
    private final BookingService bookingService;

    public BookingController(BookingFacade bookingFacade,
                             UserService userService,
                             FlightService flightService,
                             HotelService hotelService,
                             BookingService bookingService) {
        this.bookingFacade = bookingFacade;
        this.userService = userService;
        this.flightService = flightService;
        this.hotelService = hotelService;
        this.bookingService = bookingService;
    }

    //vzima vs bookings ot bazata i gi podava na html-a
    @GetMapping
    public String listBookings(Model model) {
        List<Booking> bookings = bookingFacade.getAllBookings();
        //model-za podavane na obekti ot controller kam view
        model.addAttribute("bookings", bookings);
        return "bookings/list"; // templates/bookings/list.html
    }

    //pokazva dannite za drop-down
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "bookings/add"; // templates/bookings/add.html
    }

    //kakvo stava pri submit
    @PostMapping("/add")
    public String addBooking(@RequestParam Long userId,
                             @RequestParam Long flightId,
                             @RequestParam Long hotelId) {

        User user=userService.getUserEntityById(userId);
       bookingFacade.bookTrip( user, flightId, hotelId);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("booking", bookingService.getBookingById(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "bookings/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateBooking(@PathVariable Long id,
                                @RequestParam Long userId,
                                @RequestParam Long flightId,
                                @RequestParam Long hotelId) {
        User user = userService.getUserEntityById(userId);
        Flight flight = flightService.getFlightEntityById(flightId);
        Hotel hotel = hotelService.getHotelEntityById(hotelId);

        bookingService.updateBooking(id, user, flight, hotel);
        return "redirect:/bookings";
    }

    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.removeBookingById(id);
        return "redirect:/bookings";
    }
}