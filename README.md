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
