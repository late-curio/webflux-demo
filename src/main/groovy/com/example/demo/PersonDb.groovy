package com.example.demo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.time.LocalDate
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping
class PersonDb {

    Map<String, Person> REPO = [
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

    private AtomicInteger hits = new AtomicInteger(0)

    @GetMapping("/db/person/{id}")
    ResponseEntity<Person> getPersonById(@PathVariable String id) {
        if(hits.incrementAndGet() % 20 == 0) {
            new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        else {
            ResponseEntity.ok(findPersonById(id))
        }
    }

    @GetMapping("/db/count")
    ResponseEntity<Integer> cout() {
        ResponseEntity.ok(hits.get())
    }

    Person findPersonById(String id) {
        sleep(500)
        REPO[id]
    }
}
