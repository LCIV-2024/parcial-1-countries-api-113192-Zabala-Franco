# Usa una imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Argumento para el archivo JAR
ARG JAR_FILE=target/*.jar

# Copia el archivo JAR generado en la construcción a la imagen
COPY ${JAR_FILE} app.jar

# Expone el puerto que usa tu aplicación (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
