# Scheduler Management

## Solution

Tt was created a Spring Boot RESTfull API. using the following the following technologies:

- Java 8
- MongoDB
- Spring Data MongoDB
- Spring DI
- Spring Boot
- REST API
- Docker
- Docker-compose
- Gradle

I used as principle the `Clean Architecture` as most as possible. It was not complete `Clean Architecture` because I used a Spring Boot and
MongoDB, but if you have a look deeply on this repository you will be able to see that no extra knowledge is required to test the application.

I also used as principle `KISS` (Keep it simple, stupid).

The project structure, and the responsibilities are stated below:

`api` is the package that contains the REST API definition for the game domain. 
It is split in the following sub-packages: `controller`, `exception`, `model`, `service`.

**Note:** I have decided to keep the application as simple as possible, so I didn't create a custom Exception, Exception handler, etc. Once this is not goal for this project.

`configuration` is the package that contains the custom configuration required for the application. One of them is
the **SwaggerConfig** to document the REST API. The other configuration is the Jackson object mapper. 

`persistence`: is the package that contains the entity **Job** as the main entity of the application. Also on this package there is
a sub-package called `repository` that contains an interface called **JobRepository** used as a database abstraction layer.

I decided to use the SpringData to access the database methods because it is quite simple and fast to create a new query on database, 
it is only required a new method signature, sometimes a `@Query` definition.


## Design patterns

- Object Mother + Fluent Builder
- Singleton for bean instantiation
- Builder

## Prerequisites

It is necessary the followings to build the program:
- Latest docker version 
- docker login performed as well (once the mongodb image will be downloaded)
- docker-compose
- Latest gradle version
- JDK 1.8+

To run the program, it is required:
- JRE 1.8+
- docker
- docker-compose


## How to run

To build the application execute the following command on terminal:

```
gradle clean build
```

To run the application execute the following command on terminal:

```
docker-compose up mongo-db
docker-compose up app
```

## How to test

You can use the following rest calls to validate the application:

> Create a job

```
curl --header "Content-Type: application/json" \ 
     --request POST \ 
     localhost:8080/schedulermanagement/v1/jobs
```

Body sample
```json
{
    "description": "test-8",
    "conclusionDeadline": "2020-07-12 13:00:00",
    "estimatedTime": 6
}
```
**NOTE:** I didn't create a exception to validate the date format, but It is easy to do. But the idea of this repository is not work on these kind of validations. The format that should be used is: `yyyy-MM-dd HH:mm:ss`


> Retrieve the jobs

```
curl --header "Content-Type: application/json" \ 
     --request GET \ 
     localhost:8080/schedulermanagement/v1/jobs
```

> Create the scheduler

```
curl --header "Content-Type: application/json" \ 
     --request POST \ 
     localhost:8080/schedulermanagement/v1/jobs?from=2020-07-01 23:00:00&from=2020-07-20 00:00:00
```

## Documentation

Besides this documentation, the RESTfull API is also documented by SWAGGER, to check out 
the SWAGGER documentation, run the application and access the following link: [API documentation](http://localhost:8080/swagger-ui.html)