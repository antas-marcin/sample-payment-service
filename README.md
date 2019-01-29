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

## Working with docker compose

In order to run the project with a mongodb one can use docker-compose.

The simplest way to do it is to run **mvn clean package docker-compose:up** command.

```shell
mvn clean package docker-compose:up
```

Then the payments api is accessible under this address: **http://localhost:8080/api/v1/payments**

## Payments API

This section describes payment version 1 of the payments api:

Payments API follows JSON API specification (https://jsonapi.org/).

Payments API implementation uses **crnk.io** library to ease building JSON:API compliant services.  

Sample payloads are stored in /test directory.

1. Get all payments

```bash
GET /api/v1/payments
```

returns:
 - 200 - returns all of the data stored in DB

Example:

```bash
curl -v -X GET -H "Content-Type: application/vnd.api+json" http://localhost:8080/api/v1/payments
```

2. Get payment

parameter id - id of the payment

```bash
GET /api/v1/payments/{id}
```

returns:
 - 200 - payment object
 - 404 - if payment object was not found

Example:

```bash
curl -v -X GET -H "Content-Type: application/vnd.api+json" http://localhost:8080/api/v1/payments/216d4da9-e59a-4cc6-8df3-3da6e7580b77
```

3. Save payment

http body - Payment json object

```bash
POST /api/v1/payments
```

returns:
 - 201 - payment object

Example:

```bash
curl -v -X POST -H "Content-Type: application/vnd.api+json" -d @test/new-payment.json http://localhost:8080/api/v1/payments
```

4. Update payment

parameter id - id of the payment

http body - Payment json object

```bash
PATCH /api/v1/payments/{id}
```

returns:
 - 200 - payment object
 - 404 - if payment object was not found

Example:

```bash
curl -v -X PATCH -H "Content-Type: application/vnd.api+json" -d @test/sample-payment-modified.json http://localhost:8080/api/v1/payments/sample
```

5. Delete payment

parameter id - is the the id of the payment

```bash
DELETE /api/v1/payments/{id}
```
returns:
 - 204 - if successful
 - 404 - if payment object was not found

Example:

```bash
curl -X DELETE -H "Content-Type: application/vnd.api+json" http://localhost:8080/api/v1/payments/sample
```

## TODO

* Enhance validation of the payment resource
* Explore whether Party / SponsorParty objects should be saved in a separate tables on DB
* Expand current HATEOAS implementation (define other relations)
* Add `swagger` for REST API documentation
* Add support for `Kubernetes`
* Add monitoring of payments service
* Add performance tests