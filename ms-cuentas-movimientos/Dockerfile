FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/ms-cuentas-movimientos-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
