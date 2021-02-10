package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PersonService {

    final ObjectMapper objectMapper
    final WebClient webClient

    PersonService(ObjectMapper objectMapper, WebClient.Builder builder) {
        this.objectMapper = objectMapper
        this.webClient = builder.baseUrl("http://localhost:8080/db/person")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
    }

    List<Person> findAllPeopleByIds(List<String> ids) {
        ids.collect {findPersonById(it) }
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
        webClient.get()
                .uri("/${id}")
                .retrieve()
                .bodyToMono(Person)
    }
}
