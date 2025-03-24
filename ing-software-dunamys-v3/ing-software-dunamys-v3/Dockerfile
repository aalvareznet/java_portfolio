# Imagen base de Java (ajusta la versión según tu preferencia)
FROM eclipse-temurin:21-jre-alpine

# Crea y define el directorio de trabajo
WORKDIR /app

# Copia el JAR generado en la carpeta target al contenedor
COPY target/ing-software-dunamys-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que corre tu aplicación (por defecto, 8080)
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
