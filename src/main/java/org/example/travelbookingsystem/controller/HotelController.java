package org.example.travelbookingsystem.controller;

import jakarta.validation.Valid;
import org.example.travelbookingsystem.dto.hotel.HotelRequestDTO;
import org.example.travelbookingsystem.model.Flight;
import org.example.travelbookingsystem.model.Hotel;
import org.example.travelbookingsystem.model.User;
import org.example.travelbookingsystem.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public String listHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "hotels/list"; // templates/flights/list.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotels/add";
    }

    @PostMapping("/add")
    public String addHotel(@Valid @ModelAttribute HotelRequestDTO dto) {
        hotelService.createHotel(dto);
        return "redirect:/hotels";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("hotel", hotelService.getHotelById(id));
        return "hotels/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateHotel(@PathVariable Long id,@Valid @ModelAttribute HotelRequestDTO dto) {
        hotelService.updateHotel(id, dto);
        return "redirect:/hotels";
    }

    @PostMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.removeHotelById(id);
        return "redirect:/hotels";
    }
}
