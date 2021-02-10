package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.retry.Retry

import java.time.Duration
import java.time.temporal.Temporal

@Service
class PersonService {

    @Autowired
    ObjectMapper objectMapper

    List<Person> findAllPeopleByIds(List<String> ids) {
        ids.collect {findPersonById(it)}
    }

    Person findPersonById(String id) {
        String json = new URL("http://localhost:8080/db/person/${id}").text
        objectMapper.readValue(json, Person.class)
    }

    Flux<Person> findAllPeopleByIdsReactive(List<String> ids) {
        Flux.fromIterable(ids)
        .flatMap { getPersonReactive(it) }
    }

    Mono<Person> findPersonByIdReactive(String id) {
        getPersonReactive(id)
    }

    private Mono<Person> getPersonReactive(String id) {
        WebClient.builder()
                .baseUrl("http://localhost:8080/person/${id}")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .retrieve()
                .bodyToMono(Person)
                .retryWhen(Retry.max(1))
                .timeout(Duration.ofSeconds(1000))
    }
}
