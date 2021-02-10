package com.example.demo

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Service
class PersonService {
    Map<String, Person> repo = [
            "fbodba1": new Person(
                    name: "Florence",
                    birthDate: LocalDate.of(2000, 1, 1),
                    email: "florence_bodba@optum.com"
            ),
            "cmartin1": new Person(
                    name: "Charlie",
                    birthDate: LocalDate.of(2000, 1, 1),
                    email: "charlie_martin@optum.com"
            ),
            "kfletcher1": new Person(
                    name: "Kent",
                    birthDate: LocalDate.of(2000, 1, 1),
                    email: "kent_fletcher@optum.com"
            ),
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

    List<Person> findAllPeopleByIds(List<String> ids) {
        ids.collect {findPersonById(it) }
    }

    Person findPersonById(String id) {
        sleep(1000)
        repo[id]
    }

    Flux<Person> findAllPeopleByIdsReactive(List<String> ids) {
        Flux.fromIterable(ids)
        .map{findPersonById(it) }
    }

    Mono<Person> findPersonByIdReactive(String id) {
        CompletableFuture<Person> completableFuture = new CompletableFuture<>()
        completableFuture.complete(findPersonById(id))
        Mono.fromFuture(completableFuture)
    }
}
