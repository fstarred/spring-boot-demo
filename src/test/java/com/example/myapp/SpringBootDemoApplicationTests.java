package com.example.myapp;

import com.example.myapp.mongodb.data.PersonRepository;
import com.example.myapp.mongodb.model.PersonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootDemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenPeople_whenGetPeople_thenStatus200() throws Exception {

        mvc.perform(get("/people").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }

    @Test
    void givenPeople_whenInsertPeople_thenStatus201() throws Exception {

        final var input = new PersonDTO(null, "John","McGillis");

        final ResultActions resultActions = mvc.perform(post("/people")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                ;

        // clear entry
        final String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        final var output = objectMapper.readValue(contentAsString, PersonDTO.class);

        Assertions.assertDoesNotThrow(() -> repository.deleteById(output.id()));

    }
}
