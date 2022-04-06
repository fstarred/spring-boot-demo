package com.example.myapp.api;


import com.example.myapp.mongodb.model.PersonDTO;
import com.example.myapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * mongodb
 */
@RestController
@RequestMapping("/people")
public class PersonApi {

    @Autowired
    PersonService service;

    @GetMapping
    List<PersonDTO> list() {
        return service.list();
    }

    @GetMapping("{id}")
    PersonDTO get(@PathVariable final String id) {
        return service.get(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable final String id) {
        service.delete(id);
    }

    @PostMapping
    ResponseEntity<PersonDTO> create(@RequestBody @Valid final PersonDTO input) {
        return new ResponseEntity<>(service.create(input), HttpStatus.CREATED);
    }

}
