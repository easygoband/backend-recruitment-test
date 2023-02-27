# Welcome to ZSSN (Zombie Survival Social Network) 🧟

![Version](https://img.shields.io/badge/version-0.1.0-blue.svg?cacheSeconds=2592000)
[![Documentation](https://img.shields.io/badge/documentation-yes-brightgreen.svg)](https://github.com/eduardo-rdguez/zssn/blob/main/RUN.md)

## Specs

- Java `8`
- Spring Boot `2.7.9`
- Kotlin `1.6.10`
- Groovy `3.0.15`
- Spock Framework `2.0`
- Gradle `7.6`
- OpenAPI `1.6.12`

## Run

To start ZSSN app:

* Runs this project as a Spring Boot application with `./gradlew bootRun`
* Now you can visit [`localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html) from your browser to view
  the **Swagger documentation**.
  * Click on an endpoint to view its details, including input and output parameters, requests and responses, and more.

**Note**: This app uses H2 DB so the data will be deleted when you restart or stop the app.

## Tests

Run tests this project:

```sh
./gradlew test
```

## Postman

Use the Postman ZSSN collection to test the endpoints and ensure that the app is working as expected.

https://www.postman.com/eduardo-rdguez/workspace/easygoband

## Author

- Website: <https://eduardo-rdguez.github.io/>
- Github: [@eduardo-rdguez](https://github.com/eduardo-rdguez)
