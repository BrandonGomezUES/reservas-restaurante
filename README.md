# Sistema de Gestión de Reservas

API REST profesional desarrollada con Spring Boot para la gestión integral de un restaurante. El sistema permite administrar clientes, mesas, turnos y reservas bajo una arquitectura limpia en capas, aplicando buenas prácticas de desarrollo, DTOs, mapeo eficiente y pruebas automatizadas.

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

