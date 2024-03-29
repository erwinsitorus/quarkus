# Quarkus Project

This project uses Quarkus and PostgreSQL.

If you want to learn more about Quarkus And PostgreSQL, please visit its website: https://quarkus.io/ and https://www.postgresql.org/.

## Preparation

> Create database and setting configuration in application.yaml

## Running the application in dev mode

You can run your application in dev mode that enables live coding using, and will automatically run db-migration:
```shell script
./mvnw compile quarkus:dev
```
> **_NOTE:_**  Open Swagger at http://localhost:8081/q/swagger-ui

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8081/q/dev/.


## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/ct-corp-test-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.
