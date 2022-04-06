package com.example.myapp.mapper;

import com.example.myapp.mysql.model.User;
import com.example.myapp.mysql.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
