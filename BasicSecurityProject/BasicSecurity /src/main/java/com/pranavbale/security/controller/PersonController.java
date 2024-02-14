package com.pranavbale.security.controller;

import com.pranavbale.security.entity.PersonData;
import com.pranavbale.security.entity.UserInfo;
import com.pranavbale.security.service.PersonService;
import com.pranavbale.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody UserInfo userInfo) {
        userService.createUser(userInfo);
        return "User Create Successfully";
    }

    @PostMapping("/create")
    public PersonData createPerson(@RequestBody PersonData personData) {
        return personService.createPerson(personData);
    }

    @GetMapping("find/{id}")
    public PersonData getPersonById(@PathVariable UUID id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/findAll")
    public List<PersonData> getAllPerson() {
        return personService.getAllPerson();
    }

    @PutMapping("/update")
    public PersonData updatePerson(@RequestBody PersonData personData) {
        return personService.updatePerson(personData);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable UUID id) {
        return  personService.deletePerson(id);
    }
}
