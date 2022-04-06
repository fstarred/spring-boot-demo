package com.example.myapp.service;

import com.example.myapp.exception.NotFoundException;
import com.example.myapp.mapper.UserMapper;
import com.example.myapp.mysql.data.UserRepository;
import com.example.myapp.mysql.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository repository;

    public Iterable<UserDTO> list() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(userMapper::userToUserDTO).toList();
    }

    public UserDTO get(final int id) {
        return userMapper.userToUserDTO(repository.findById(id).orElseThrow(NotFoundException::new));
    }

    public void delete(final int id) {
        repository.findById(id).ifPresentOrElse(o -> repository.deleteById(id), () -> {
            throw new NotFoundException();
        });
    }

    public UserDTO create(UserDTO input) {
        return userMapper.userToUserDTO(repository.save(userMapper.userDTOToUser(input)));
    }

}
