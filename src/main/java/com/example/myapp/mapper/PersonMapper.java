package com.example.myapp.mapper;

import com.example.myapp.mongodb.model.Person;
import com.example.myapp.mongodb.model.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person personDTOToPerson(PersonDTO personDTO);

    PersonDTO personToPersonDTO(Person person);

}
