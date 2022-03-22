# Hibernate Demo on Heroku

<img src="https://www.noroff.no/images/docs/vp2018/Noroff-logo_STDM_vertikal_RGB.jpg" alt="banner" width="450"/>

[![standard-readme compliant](https://img.shields.io/badge/standard--readme-OK-green.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)
[![web](https://img.shields.io/static/v1?logo=heroku&message=Online&label=Heroku&color=430098)](https://accelerate-hibernate-demo.herokuapp.com/swagger-ui/index.html)
[![container](https://img.shields.io/static/v1?logo=docker&message=Registry&label=Docker&color=2496ED)](https://gitlab.com/noroff-accelerate/java/projects/hibernate-with-ci/container_registry)
[![pipeline status](https://gitlab.com/noroff-accelerate/java/projects/hibernate-with-ci/badges/master/pipeline.svg)](https://noroff-accelerate.gitlab.io/java/projects/hibernate-with-ci/tests)
[![coverage report](https://gitlab.com/noroff-accelerate/java/projects/hibernate-with-ci/badges/master/coverage.svg)](https://noroff-accelerate.gitlab.io/java/projects/hibernate-with-ci/coverage)
<!-- [![codecov](https://codecov.io/gl/noroff-accelerate:java:projects/hibernate-with-ci/branch/master/graph/badge.svg?token=F6DZDTNS86)](https://codecov.io/gl/noroff-accelerate:java:projects/hibernate-with-ci) -->

Demo deployment of a Spring application to Heroku

## Table of Contents

- [Background](#background)
- [Install](#install)
- [Usage](#usage)
- [Maintainers](#maintainers)
- [Contributing](#contributing)
- [License](#license)

## Background

Hibernate is an Object Relational Model library that provides an easy and standardized way to write Java Spring applications and interact with a variety of databases, and across multiple dialects of SQL. This demo primarily demonstrates the use of Hibernate to build a simple REST API that is secured using Spring Security.

This demo requires several external services:

- [x] Postgres is a mature and extensible SQL database that is supported by pretty much everything and isn't tainted by Microsoft. Google it. 
- [ ] Keycloak is an easily deployed identity provider, maintained by Red Hat and designed for ease of developing applications applications secured using one of many SSO authentication mechanisms such as OAuth2, OpenID Connect, or SAML2.

### Deploying Postgres

Postgres is deployed using Docker, using the Docker Compose configuration `docker-compose.yml`. This is setup to accept two environment variables:

- `POSTGRES_DB` &mdash; the name of the postgres database
- `POSTGRES_PASSWORD` &mdash; the only environment variable that is strictly required by the Postgres image.

These are specified in a file that you must create called `.env` (without file extension). This is done by copying `.env.example` and modify values to your preference.

You can then start the Postgres server by running the following command:

```shell
docker-compose up -d postgres
```

### OAuth2/OpenID Connect Authentication via Keycloak

> TODO

### Initial Application Setup

When generating a new application that includes Spring Security, the default configuration locks the entire application behind a basic login page. If this is acceptable then no further configuration is necessary, however this behaviour can be disabled.

The default username to get past the login screen is `user` and a random password is generated and logged to _stdout_ on each run of the application. More complex authentication schemes will be elaborated on later.

To disable this behaviour, or to return the application to the typical behaviour of an app _without_ spring security installed, the following class needs to be added to your project:

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Disable authorization checks for all requests
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                // Disable CSRF protection
                .csrf(csrf -> csrf.disable());
    }
}
```

### CI Setup

As with the previous demo, this repository shows a Spring application with a complete CI setup to test, build, and deploy the final application on Heroku.

The CI pipeline will:

- Run tests, proceeding if the tests pass.
- Build the project into a production artifact, proceeding if successful.
- Package the built application into a Docker image, proceeding if successful.
- Push the Docker image to the local registry on Gitlab. This requires a manual trigger.
- Trigger the build pipeline on Heroku to pull the latest image and replace the current running dyno.

### Additional Libraries and Considerations

Some additional considerations are addressed with the addition of additional libraries.

#### [ModelMapper](http://modelmapper.org/getting-started/)

Projecting database objects (DBOs) to data transfer objects (DTOs) can be cumbersome, however there exists the ModelMapper library which analyzes two types and creates a mapping between them with a single function call. In that automatic fails, a manual mapping may be created to maintain the core use of the library across your application.

#### [JNanoId](https://github.com/aventrix/jnanoid)

A Java port of the popular NanoId Javascript library. This tiny library is a secure, URL-friendly, unique string ID generator for use in databases and APIs that are less cumbersome than UUIDs (21 characters vs. 36) and do not leak intelligence like auto-incrementing integers.

### A Primer on URIs

URIs take the following general form:

```
scheme://username:password@target/path/to/your/resource?query=data&foo=bar#fragment
```

Where `target` can be something like a network address of the form: `host[:port]`. For example:

- `foo.example.com`
- `hello.herokuapp.com`
- `localhost:8080`

The API endpoints for the `CustomerContoller` look something like this:

```
GET http://localhost:8080/api/customer
GET http://localhost:8080/api/customer/:id
POST http://localhost:8080/api/customer
PUT http://localhost:8080/api/customer/:id
PATCH http://localhost:8080/api/customer/:id
DELETE http://localhost:8080/api/customer/:id
```

## Install

Gradle will automatically initialize itself and download necessary dependencies the first time the wrapper is run. No explicit installation necessary.

## Usage

For Linux/macOS users, open a terminal and run:

```sh
./gradlew bootRun
```

For Windows users, use `gradlew.bat` instead of `gradlew` in PowerShell.

## Acknowledgements

- `books.csv` ([DOI](https://doi.org/10.5281/zenodo.4265096)) &mdash; The author is grateful for the use of a freely available database of Books from the Goodreads API by  Lorena Casanova Lozano and Sergio Costa Planells.

## Maintainers

[Greg Linklater (@EternalDeiwos)](https://gitlab.com/EternalDeiwos)

## Contributing

PRs accepted.

Small note: If editing the README, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

## License

MIT © 2022 Noroff Accelerate AS
