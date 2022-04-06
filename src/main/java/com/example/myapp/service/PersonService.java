package com.example.myapp.service;

import com.example.myapp.exception.NotFoundException;
import com.example.myapp.mapper.PersonMapper;
import com.example.myapp.mongodb.data.PersonRepository;
import com.example.myapp.mongodb.model.PersonDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * mongodb
 */
@Service
public class PersonService {

    @Autowired
    Logger logger;

    @Autowired
    PersonMapper mapper;

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> list() {
        return repository.findAll().stream().map(mapper::personToPersonDTO).toList();
    }

    @Cacheable("person")
    public PersonDTO get(final String id) {
        logger.info("looking for id: {}", id);
        return mapper.personToPersonDTO(repository.findById(id).orElseThrow(NotFoundException::new));
    }

    @CacheEvict("person")
    public void delete(final String id) {
        logger.info("removing person of id: {}", id);
        repository.findById(id).ifPresentOrElse(o -> repository.deleteById(id), () -> {
            throw new NotFoundException();
        });
    }

    public PersonDTO create(PersonDTO input) {
        return mapper.personToPersonDTO(repository.insert(mapper.personDTOToPerson(input)));
    }

}
