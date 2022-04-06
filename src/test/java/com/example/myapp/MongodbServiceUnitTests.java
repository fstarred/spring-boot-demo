package com.example.myapp;

import com.example.myapp.mongodb.data.PersonRepository;
import com.example.myapp.mongodb.model.Person;
import com.example.myapp.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MongodbServiceUnitTests {

    @Autowired
    PersonService service;

    @MockBean
    PersonRepository repository;

    @Test
    void list() {
        final var output = new Person();
        output.setId(UUID.randomUUID().toString());
        output.setFirstName("Bilbo");
        output.setLastName("Baggins");

        Mockito.when(repository.findAll()).thenReturn(List.of(output));

        assertEquals(1, service.list().size());
    }

}
