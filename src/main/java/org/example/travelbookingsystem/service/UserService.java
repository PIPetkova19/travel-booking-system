package org.example.travelbookingsystem.service;

import org.example.travelbookingsystem.dto.flight.FlightResponseDTO;
import org.example.travelbookingsystem.dto.user.UserRequestDTO;
import org.example.travelbookingsystem.dto.user.UserResponseDTO;
import org.example.travelbookingsystem.mapper.user.UserMapper;
import org.example.travelbookingsystem.model.Flight;
import org.example.travelbookingsystem.model.User;
import org.example.travelbookingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void createUser(UserRequestDTO dto) {
        User user=userMapper.toEntity(dto);
        userRepository.save(user);
        System.out.println("User added");
    }

    public void removeUserById(long id) {
        userRepository.deleteById(id);
        System.out.println("User deleted");
    }


    public void updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        user.setName(dto.name());
        user.setEmail(dto.email());

         userRepository.save(user);
        System.out.println("User updated");
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    public UserResponseDTO getUserById(long id) {
        User user=userRepository.getUserById(id);
        return userMapper.toDTO(user);
    }

    public User getUserEntityById(long id) {
        return userRepository.getUserById(id);
    }
}
