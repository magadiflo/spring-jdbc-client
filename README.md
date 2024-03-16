# [Spring JdbcClient Example](https://www.knowledgefactory.net/2023/12/spring-jdbcclient-example.html)

Este tutorial fue tomado de la página web **Knowledge Factory Dev**

Si queremos entender un poco más sobre `Testcontainers` podemos ir a los siguientes enlaces:

- Proyecto con Testcontainers [spring-boot-testcontainers](https://github.com/magadiflo/spring-boot-testcontainers.git)
- Proyecto con Testcontainers
  [spring-boot-testcontainers-jdbc](https://github.com/magadiflo/spring-boot-testcontainers-jdbc.git)

---

## Creación del proyecto

Utilizando Spring Initializr creamos el proyecto con las siguientes dependencias y configuraciones:

![01.spring initializr](./assets/01.spring-initializr.png)

## Dependencias

````xml
<!--Spring Boot 3.2.3-->
<!--Java 21-->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Al agregar el Testcontainers, en automático se agregan
     las siguientes 3 dependencias -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-testcontainers</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>postgresql</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
````

## Configuración del application.yml

Para crear una tabla a través del `schema.sql`, debemos configurar `spring.sql.init.mode=always`.

Si está ejecutando la aplicación con una base de datos postgresql real, configure Spring Boot para usar PostgreSQL como
fuente de datos. Simplemente, estamos agregando la URL, el nombre de usuario y la contraseña de la base de
datos `PostgreSQL` en `src/main/resources/application.yml`.

````yaml
server:
  port: 8080
  error:
    include-message: always

spring:
  application:
    name: spring-jdbc-client

  datasource:
    url: jdbc:postgresql://localhost:5432/db_production
    username: postgres
    password: magadiflo

  sql:
    init:
      mode: always
````

## Creando el schema.sql y data.sql

Para generar un esquema de base de datos al inicio de la aplicación, el archivo `schema.sql` con los comandos SQL
correspondientes para la creación del esquema debe almacenarse en la carpeta de recursos.

````sql
CREATE TABLE IF NOT EXISTS students(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    gender VARCHAR(250) NOT NULL,
    age INT
);
````

Cargue algunos datos de prueba iniciales.

````sql
TRUNCATE TABLE students RESTART IDENTITY;

INSERT INTO students (name, email, age, gender)
VALUES('Pro', 'pro@knf.com', 60, 'Male'),
('Alpha', 'alpha@knf.com', 50, 'Male'),
('Beta', 'beta@knf.com', 40, 'Female'),
('Gama', 'gama@knf.com', 30, 'Male'),
('Pekka', 'pekka@knf.com', 20, 'Female'),
('Noob', 'noob@knf.com', 10, 'Male'),
('Noob2', 'noob2@knf.com', 5, 'Male'),
('Noob3', 'noob3@knf.com', 5, 'Male');
````

## Model - Student

Crearemos nuestro modelo de estudiante usando un `record` de java:

````java
public record Student(
        Long id,
        String name,
        String email,
        String gender,
        Integer age) {
}
````