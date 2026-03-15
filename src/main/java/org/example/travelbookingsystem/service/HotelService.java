package org.example.travelbookingsystem.service;

import org.example.travelbookingsystem.dto.flight.FlightResponseDTO;
import org.example.travelbookingsystem.dto.hotel.HotelRequestDTO;
import org.example.travelbookingsystem.dto.hotel.HotelResponseDTO;
import org.example.travelbookingsystem.mapper.hotel.HotelMapper;
import org.example.travelbookingsystem.model.Flight;
import org.example.travelbookingsystem.model.Hotel;
import org.example.travelbookingsystem.model.User;
import org.example.travelbookingsystem.repository.HotelRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    public void createHotel(HotelRequestDTO dto) {
        Hotel hotel = hotelMapper.toEntity(dto);
        hotelRepository.save(hotel);
        System.out.println("Hotel created");
    }

    public HotelResponseDTO reserveHotel(Long hotelId) {
        Hotel hotel = hotelRepository.getHotelById(hotelId);

        if (hotel.getAvailableRooms() <= 0) {
            throw new RuntimeException("Hotel is full, cannot reserve");
        }

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        hotelRepository.save(hotel);
        return hotelMapper.toDTO(hotel);
    }

    public List<HotelResponseDTO> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotelMapper::toDTO).toList();
    }

    public void updateHotel(Long id, HotelRequestDTO dto) {
        Hotel hotel = hotelRepository.getHotelById(id);

        hotel.setName(dto.name());
        hotel.setCity(dto.city());
        hotel.setCountry(dto.country());
        hotel.setPricePerNight(dto.pricePerNight());
        hotel.setAvailableRooms(dto.availableRooms());

        hotelRepository.save(hotel);
        System.out.println("Hotel updated");
    }

    public void removeHotelById(long id) {
        hotelRepository.deleteById(id);
        System.out.println("Hotel deleted");
    }

    public Hotel findBestHotels(String city, double maxPrice) {
        List<Hotel> hotelsByCity = hotelRepository.findAll().stream()
                .filter(h -> h.getCity().equals(city))
                .filter(h -> h.getAvailableRooms() > 0)
                .toList();

        if (hotelsByCity.isEmpty()) {
            throw new RuntimeException("No Hotel in this city " + city);
        }
        List<Hotel> hotelsByPrice = hotelRepository.findAll().stream()
                .filter(h -> h.getPricePerNight() <= maxPrice)
                .filter(h -> h.getAvailableRooms() > 0)
                .toList();

        if (hotelsByPrice.isEmpty()) {
            throw new RuntimeException("No Hotel in this price " + maxPrice);
        }

        return hotelsByCity.stream()
                .filter(hotelsByPrice::contains)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No matching hotels found"));
    }

    public HotelResponseDTO getHotelById(Long id) {
        Hotel hotel= hotelRepository.getHotelById(id);
        return hotelMapper.toDTO(hotel);
    }

    public Hotel reserveHotelEntity(long hotelId) {
        Hotel hotel = hotelRepository.getHotelById(hotelId);

        if (hotel.getAvailableRooms() <= 0) {
            throw new RuntimeException("Flight is full");
        }

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        hotelRepository.save(hotel);

        return hotel;
    }

    public Hotel getHotelEntityById(Long hotelId) {
        return hotelRepository.getHotelById(hotelId);
    }
}
