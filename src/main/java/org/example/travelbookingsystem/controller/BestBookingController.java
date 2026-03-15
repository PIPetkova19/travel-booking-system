package org.example.travelbookingsystem.controller;

import org.example.travelbookingsystem.facade.BookingFacade;
import org.example.travelbookingsystem.model.User;
import org.example.travelbookingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bestBookings")
public class BestBookingController {

    private final BookingFacade bookingFacade;
    private final UserService userService;

    public BestBookingController(BookingFacade bookingFacade, UserService userService) {
        this.bookingFacade = bookingFacade;
        this.userService = userService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("bookings", bookingFacade.getAllBookings());
        return "bookings/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "bestBookings/add";
    }

    @PostMapping("/add")
    public String add(@RequestParam Long userId,
                      @RequestParam String destination,
                      @RequestParam double maxFlightPrice,
                      @RequestParam double maxHotelPrice) {

        User user = userService.getUserEntityById(userId);

        bookingFacade.bookBestTrip(user, destination, maxFlightPrice, maxHotelPrice);

        return "redirect:/bestBookings";
    }
}