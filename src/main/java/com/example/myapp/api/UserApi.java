package com.example.myapp.api;

import com.example.myapp.mysql.model.UserDTO;
import com.example.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * mysql
 */
@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    UserService service;

    @GetMapping
    public Iterable<UserDTO> list() {
        return service.list();
    }

    @GetMapping("{id}")
    UserDTO get(@PathVariable final int id) {
        return service.get(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable final int id) {
        service.delete(id);
    }

    @PostMapping
    ResponseEntity<UserDTO> create (@RequestBody UserDTO input) {
        return new ResponseEntity<>(service.create(input), HttpStatus.CREATED);
    }

}
