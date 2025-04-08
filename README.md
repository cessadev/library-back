# 📚 Biblioteca Backend - Prueba Técnica

Este proyecto es una **aplicación de gestión de biblioteca** desarrollada como prueba técnica. Está construida con **Java 8**, **Spring Boot 2.7**, **MyBatis**, y una base de datos **Oracle 18.4** corriendo en contenedor Docker. Además, se utilizan **Flyway** para migraciones de base de datos y se adopta una **arquitectura hexagonal** para asegurar una separación clara de responsabilidades y facilitar el mantenimiento.

---

## 🚀 Tecnologías utilizadas

| Tecnología       | Versión       | Descripción                                      |
|------------------|----------------|--------------------------------------------------|
| Java             | 1.8            | Lenguaje principal                              |
| Spring Boot      | 2.7.18         | Framework para la construcción de la API REST   |
| MyBatis          | 2.2.2          | Mapeo objeto-relacional para Oracle              |
| Oracle DB        | 18.4 (Docker)  | Base de datos relacional                         |
| Flyway           | 8.5.13         | Migraciones automáticas de base de datos        |
| Maven            | 3.8.1+         | Herramienta de construcción y dependencias      |
| Arquitectura     | Hexagonal      | Separación entre capas de dominio, aplicación y adaptadores |

---

## 🧱 Arquitectura Hexagonal

La aplicación sigue el patrón de arquitectura hexagonal (también conocida como "puertos y adaptadores"). Este enfoque permite:

- Aislar la lógica de negocio del framework y detalles técnicos.
- Sustituir fácilmente adaptadores (como la base de datos o el controlador REST).
- Facilitar pruebas unitarias y de integración.

---

## ⚙️ Requisitos previos

- Java 8
- Maven 3.6+
- Docker & Docker Compose

---

## 📦 Compilación

Plugin de Maven utilizando:

```bash
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.8.1</version>
  <configuration>
    <source>1.8</source>
    <target>1.8</target>
    <parameters>true</parameters>
  </configuration>
</plugin>
```

Ubicate en la raiz del proyecto y ejecuta:

```bash
mvn clean package
```

## ✅ Levantar base de datos Oracle con Docker Compose

Asegúrate de tener Docker instalado. Luego ejecuta:

```bash
docker-compose up -d
```

## ▶️ Ejecuta la aplicación

Ubicate en la raíz del proyecto, y ejecuta:

```bash
mvn spring-boot:run
```

O bien desde las opciones de ejecución del IDE.

---

Esta prueba técnica fue desarrollada como parte de un proceso de selección.
Quedo atento a cualquier observación, mejora o ampliación que deseen discutir.

¡Gracias por su tiempo y evaluación!

