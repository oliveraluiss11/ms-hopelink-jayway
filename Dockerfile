# Usa una imagen base de Java 21 de OpenJDK
FROM openjdk:21-jdk-slim

# Metadata de la imagen
LABEL maintainer="Luis Olivera loliverv11@gmail.com"
LABEL version="1.0"
LABEL description="Dockerfile for HopeLink application"

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY target/hopelink-0.0.1-SNAPSHOT.jar /app/hopelink.jar

# Ejecuta la aplicación al iniciar el contenedor
CMD ["java", "-jar", "hopelink.jar"]
