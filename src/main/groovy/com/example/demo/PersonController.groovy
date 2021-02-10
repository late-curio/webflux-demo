package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.CoreSubscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import java.time.LocalDate

@RestController
@RequestMapping("/person")
class PersonController {

    Map<String, Person> repo = [
            "tcrone1": new Person(
                    name: "Todd",
                    birthDate: LocalDate.of(1970, 10, 10),
                    email: "todd_crone@optum.com"
            ),
            "tarek1": new Person(
                    name: "Tarek",
                    birthDate: LocalDate.of(2000, 1, 1),
                    email: "ahmed.elshamekh@optum.com"
            )
    ]

    @GetMapping("/{id}")
    Mono<Person> getPersonById(@PathVariable String id) {
        def person = repo[id]
        if(person) {
            Mono.just(person)
        }
        else {
            Mono.empty()
        }
    }

    @GetMapping
    Flux<Person> getAllPeople() {
        Flux.fromIterable(repo.collect())
    }
}
