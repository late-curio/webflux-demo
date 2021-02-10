package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping
class PersonController {

    @Autowired
    PersonService personService

    @GetMapping("/person/{id}")
    ResponseEntity<Person> getPersonById(@PathVariable String id) {
        Person person = personService.findPersonById(id)
        person ? ResponseEntity.ok(person) : new ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/people/{ids}")
    ResponseEntity<List<Person>> findAllPeopleByIds(@PathVariable List<String> ids) {
        ResponseEntity.ok(personService.findAllPeopleByIds(ids))
    }

    @GetMapping("/reactive/person/{id}")
    Mono<Person> getPersonByIdReactive(@PathVariable String id) {
        personService.findPersonByIdReactive(id)
    }

    @GetMapping("/reactive/people/{ids}")
    Flux<Person> findAllPeopleByIdsReactive(@PathVariable List<String> ids) {
        personService.findAllPeopleByIdsReactive(ids)
    }
}
