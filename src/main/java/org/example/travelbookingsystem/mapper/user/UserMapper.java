package org.example.travelbookingsystem.mapper.user;

import org.example.travelbookingsystem.dto.user.UserRequestDTO;
import org.example.travelbookingsystem.dto.user.UserResponseDTO;
import org.example.travelbookingsystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toDTO(User user){
        return new UserResponseDTO(user.getId(),user.getName(),user.getEmail());
    }

    public User toEntity(UserRequestDTO dto){
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        return user;
    }
}
