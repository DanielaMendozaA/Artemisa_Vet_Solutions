# Etapa 1: Compilación
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml .

# Descarga las dependencias sin compilar el proyecto
RUN mvn dependency:go-offline -B

# Copia todo el código fuente al contenedor
COPY src ./src

# Compila el proyecto y empaqueta el archivo JAR
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR desde la etapa de compilación
COPY --from=build /app/target/artemisa-ecommerce-1.0.0-SNAPSHOT.jar app.jar

# Expone el puerto en el que tu aplicación se ejecuta (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
