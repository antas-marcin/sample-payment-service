# Sample Payment Service

This project is an implementation of the sample payments api.

This project uses a simple implementation of in-memory database.

This project can load payments.json into in-memory database at startup, this behaviour is controlled by load.sample.data property which is set false by default.

## Prerequisites

In order to successfully launch project one needs to install:

1. Java 8
2. Maven 3+

## Development

In order to build the application one needs to issue:

```bash
mvn clean install
```

In order to run the application one needs to issue:

```bash
mvn spring-boot:run
```

## Payment API

This section describes payment version 1 of the payments api:

1. Get all payments

```bash
GET /api/payment
```

returns:
 - 200 - returns all of the data stored in DB

Example:

```bash
curl -v -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/payment
```

2. Get payment

parameter id - id of the payment

```bash
GET /api/payment/{id}
```

returns:
 - 200 - payment object
 - 404 - if payment object was not found

Example:

```bash
curl -v -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/payment/216d4da9-e59a-4cc6-8df3-3da6e7580b77
```

3. Save payment

parameter id - id of the payment

http body - Payment json object

```bash
POST /api/payment
```

returns:
 - 200 - payment object
 - 404 - if payment object was not found

Example:

```bash
curl -v -X POST -H "Content-Type: application/json" -d @test/sample-payment.json http://localhost:8080/api/v1/payment
```

4. Update payment

parameter id - id of the payment

http body - Payment json object

```bash
PUT /api/payment/{id}
```

returns:
 - 200 - payment object
 - 404 - if payment object was not found

Example:

```bash
curl -v -X PUT -H "Content-Type: application/json" -d @sample-payment-modified.json http://localhost:8080/api/v1/payment/sample
```


5. Delete payment

parameter id - is the the id of the payment

```bash
DELETE /api/payment/{id}
```
returns:
 - 200 - if successful
 - 404 - if payment object was not found

Example:

```bash
curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/payment/sample
```
