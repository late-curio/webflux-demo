package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class PersonController {

    @Autowired
    PersonService personService

    @GetMapping("/person/{id}")
    ResponseEntity<Person> getPersonById(@PathVariable String id) {
        ResponseEntity.ok(personService.findPersonById(id))
    }

    @GetMapping("/people/{ids}")
    ResponseEntity<List<Person>> findAllPeopleByIds(@PathVariable List<String> ids) {
        ResponseEntity.ok(personService.findAllPeopleByIds(ids))
    }
}
