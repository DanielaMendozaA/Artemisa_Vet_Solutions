# Artemisa E-commerce

Plataforma de comercio electrónico para productos veterinarios, con autenticación y autorización JWT.

## Tecnologías utilizadas

* Backend:
    * Java 17
    * Spring Boot 3.3.3
    * Spring Data JPA
    * PostgreSQL 14.3
    * Spring Security 6.3.3
    * JWT (jjwt 0.9.1)
    * Swagger (springdoc-openapi 2.6.0)
    * Lombok 1.18.34
    * MapStruct 1.6.0

## Arquitectura

* **Backend (Spring Boot):**
    * Arquitectura hexagonal (Puertos y Adaptadores) para desacoplar la lógica de negocio.
    * Manejo estructurado de errores y excepciones.
    * Uso de DTOs y mappers para la comunicación entre capas.
    * Autenticación y autorización JWT utilizando Spring Security.

## Configuración e instalación

1. **Requisitos previos:**
    * Java 17 o superior
    * Node.js 16 o superior
    * Docker (opcional)
    * Docker Compose (opcional)

2. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/ArtemisaVetSolution/Artemisa-E-commerce-bc.git

## Levantar la aplicacion desde java

1. **Backend (Spring Boot):**
    *Asegúrate de tener PostgreSQL instalado y configurado en tu máquina.*
    *Navega a la carpeta artemisa-e-commerce-bc.*
    *Ejecuta mvn spring-boot:run.*

## Acceder a los servicios

    *E-commerce (Spring Boot): http://localhost:8080/v1/api*
    *API de usuarios (NestJS): http://localhost:3001*
    *Frontend (NestJS): http://localhost:5173*
    *Documentación de la API (Swagger): http://localhost:8080/v1/api/swagger-ui/index.html*

## Autenticación y autorización JWT
