# webflux-demo

## Run
`./gradlew bootRun`

## API

`GET http://localhost:8080/person/tarek1`

`GET http://localhost:8080/people/tcrone1,fbodba1,kfletcher1,cmartin1,tarek1`

`GET http://localhost:8080/reactive/person/tcrone1`

`GET http://localhost:8080/reactive/people/tcrone1,fbodba1,kfletcher1,cmartin1,tarek1`

## Simulates error call every 20th call

Check the current request count

`GET http://localhost:8080/db/count`