# Alura Challenge Back-End

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Linkedin](https://i.stack.imgur.com/gVE0j.png) LinkedIn Profile](https://www.linkedin.com/in/luiz-gustavo-duran-105a6557/)
&nbsp;

This API project is a result of [Alura Challenge Back-End](https://www.alura.com.br/challenges/back-end-2) whose mainly propose was to manage a budget.

## Features

- [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org)
- [MariaDB](https://mariadb.org)
- [springdoc-openapi](https://springdoc.org)

## Deploying the application to AWS
If you want to access the app, go to [OpenShift CLI](https://docs.openshift.org/latest/cli_reference/index.html):

```shell
oc new-app codecentric/springboot-maven3-centos~https://github.com/codecentric/springboot-sample-app
```

```shell
oc expose springboot-sample-app --hostname=www.example.com
```
## Sample User

#### Request
```json
{
   "username": "test",
   "password":"123456"
}
```

#### Response
```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjQ0Njc0NDc4LCJleHAiOjE2NDQ3NjA4Nzh9.dIz7oA0b8cuVWjHq-yjFfDA7hEaun3W3lrYdBqKRGdeZ239oq-1yLSt6SQceNWlAMxGm5vC0qg7XOghRO1iILw",
    "type": "Bearer",
    "id": 5,
    "username": "test",
    "email": "test@gmail.com",
    "roles": [
        "USER"
    ]
}
```

## Swagger Docs

It helps to automate the generation of API documentation using spring boot projects.

![ScreenShot](/images/openAPI3.png)

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
