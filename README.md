# Sistema de Gestión de Reservas

Sistema desarrollado con Spring Boot para la gestión integral de un restaurante. El sistema permite administrar clientes, mesas, turnos y reservas bajo una arquitectura limpia en capas, aplicando buenas prácticas de desarrollo, DTOs, mapeo eficiente y pruebas automatizadas.


## Detalles del Curso
* **Carrera:** Ingeniería en Desarrollo de Software
* **Materia:** Programación Orientada a Objetos Ciclo II / Segundo año
* **Grupo Teórico:** 02
* **Tutor:** Ing. Erick Adiel Trigueros Jerez

## Integrantes
* **Byron Josue Gomez Monge** (GM22118)
* **Brandon William Gomez Monge** (GM21057)
  
---

## Tecnologías y Stack

* **Java 21**
* **Spring Boot 4.1.1**
    * Spring Web
    * Spring Data JPA
* **H2 Database**
* **MapStruct**
* **Lombok**
* **JUnit 5 & Mockito**

---

## Diagrama Casos de Uso

![Diagrama de casos de uso](https://github.com/BrandonGomezUES/reservas-restaurante/blob/65d6a51c1380560c2dbfe73ad61d755e04ac6dc0/CasosDeUso.jpeg)

---

## Diagrama UML

![Diagrama UML](https://github.com/BrandonGomezUES/reservas-restaurante/blob/65d6a51c1380560c2dbfe73ad61d755e04ac6dc0/DiagramaUML.jpeg)

---

## Diagrama ER

![Diagrama UML](https://github.com/BrandonGomezUES/reservas-restaurante/blob/65d6a51c1380560c2dbfe73ad61d755e04ac6dc0/DiagramaER.jpeg)

---

## Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas tradicional, asegurando el desacoplamiento y la mantenibilidad:

```text
com.reservas.restaurante/
│
├── controller/     # Controladores REST (Endpoints y validaciones de entrada)
├── service/        # Lógica de negocio del restaurante
│   └── impl/       # Implementaciones reales de los servicios
├── repository/     # Interfaces de acceso a datos con Spring Data JPA
├── model/
│   ├── entity/     # Entidades de base de datos (Cliente, Mesa, Turno, Reserva)
│   └── dto/        # Objetos de transferencia de datos (Request / Response)
│   └── mapper/     # Interfaces de MapStruct para transformación de objetos
└── exception/      # Manejo global y centralizado de excepciones y errores HTTP

