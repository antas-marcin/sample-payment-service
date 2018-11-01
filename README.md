# Sample Payment Service

This project is an implementation of the sample payments api.

This project uses a mongodb database.

## Prerequisites

In order to successfully launch project one needs to install:

1. Java 8
2. Maven 3+
3. Docker
4. Docker compose

## Development

In order to build the application one needs to issue:

```bash
mvn clean install
```

In order to start DB using docker one needs to issue:
```bash
docker run --name some-mongo -p 27017:27017 -d mongo:latest
```

In order to run the application one needs to issue:

```bash
mvn spring-boot:run
```

## Working with docker compose

In order to run the api with a mongodb one can use docker-compose.

Please not that in order to start the java container one must first build the project using maven.

## Payments API

This section describes payment version 1 of the payments api:

1. Get all payments

```bash
GET /api/payments
```

returns:
 - 200 - returns all of the data stored in DB

Example:

```bash
curl -v -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/payments
```

2. Get payment

parameter id - id of the payment

```bash
GET /api/payments/{id}
```

returns:
 - 200 - payment object
 - 404 - if payment object was not found

Example:

```bash
curl -v -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/payments/216d4da9-e59a-4cc6-8df3-3da6e7580b77
```

3. Save payment

parameter id - id of the payment

http body - Payment json object

```bash
POST /api/payments
```

returns:
 - 200 - payment object
 - 404 - if payment object was not found

Example:

```bash
curl -v -X POST -H "Content-Type: application/json" -d @test/new-payment.json http://localhost:8080/api/v1/payments
```

4. Update payment

parameter id - id of the payment

http body - Payment json object

```bash
PUT /api/payments/{id}
```

returns:
 - 200 - payment object
 - 404 - if payment object was not found

Example:

```bash
curl -v -X PUT -H "Content-Type: application/json" -d @test/sample-payment-modified.json http://localhost:8080/api/v1/payments/sample
```


5. Delete payment

parameter id - is the the id of the payment

```bash
DELETE /api/payments/{id}
```
returns:
 - 200 - if successful
 - 404 - if payment object was not found

Example:

```bash
curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/v1/payments/sample
```
