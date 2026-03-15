package org.example.travelbookingsystem.controller;

import jakarta.validation.Valid;
import org.example.travelbookingsystem.dto.flight.FlightRequestDTO;
import org.example.travelbookingsystem.model.Flight;
import org.example.travelbookingsystem.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public String listFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flights/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flights/add";
    }

    @PostMapping("/add")
    public String addFlight(@Valid @ModelAttribute FlightRequestDTO dto) {
        flightService.createFlight(dto);
        return "redirect:/flights";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getFlightById(id));
        return "flights/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateFlight(@PathVariable Long id,@Valid  @ModelAttribute FlightRequestDTO dto) {
        flightService.updateFlight(id, dto);
        return "redirect:/flights";
    }

    @PostMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        flightService.removeFlightById(id);
        return "redirect:/flights";
    }
}