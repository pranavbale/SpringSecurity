package com.pranavbale.security.service;

import com.pranavbale.security.entity.PersonData;
import com.pranavbale.security.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonData createPerson(PersonData personData) {

        return personRepository.save(personData);
    }

    public PersonData getPersonById(UUID id) {
        return personRepository.findById(id).get();
    }

    public List<PersonData> getAllPerson() {
        return personRepository.findAll();
    }

    public PersonData updatePerson(PersonData personData) {
        return personRepository.save(personData);
    }

    public String deletePerson(UUID id) {
        personRepository.deleteById(id);
        return "Person Deleted Successfully";
    }
}
