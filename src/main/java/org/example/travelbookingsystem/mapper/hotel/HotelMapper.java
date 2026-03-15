package org.example.travelbookingsystem.mapper.hotel;

import jakarta.validation.constraints.Positive;
import org.example.travelbookingsystem.dto.hotel.HotelRequestDTO;
import org.example.travelbookingsystem.dto.hotel.HotelResponseDTO;
import org.example.travelbookingsystem.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {
    public Hotel toEntity(HotelRequestDTO dto){
        Hotel hotel = new Hotel();
        hotel.setName(dto.name());
        hotel.setCity(dto.city());
        hotel.setCountry(dto.country());
        hotel.setPricePerNight(dto.pricePerNight());
        hotel.setAvailableRooms(dto.availableRooms());
        return hotel;
    }

    public HotelResponseDTO toDTO(Hotel hotel){
        return new HotelResponseDTO(hotel.getId(),
                hotel.getName(),
                hotel.getCity(),
                hotel.getCountry(),
                hotel.getPricePerNight(),
                hotel.getAvailableRooms());
    }
}