# repo-microservice-rommelchocho

**Consideraciones Test (Pruebas Unitarias y de Integración)

    Seguir los siguientes pasos para realizar las pruebas Unitarias y de Integración

    - cd repo-microservice-rommelchocho/ms-clientes-personas
    - mvn test

    (Se tiene una prueba para el Servicio, Una para el repositorio y la prueba de Integración para una petición completa como prueba de Integración. )

**Consideraciones Despliegue

    Compilar microservicio ms-clientes-personas
    - cd repo-microservice-rommelchocho/ms-clientes-personas
    - mvn clean package

    Compilar microservicio ms-cuentas.movimientos
    - cd repo-microservice-rommelchocho/ms-cuentas-movimientos
    - mvn clean package

    Desplegar solución completa
    - docker composer up -build

**Consideraciones Pruebas Aplicación

    Se tiene un archivo prueba_tecnica.postman_collection.json que se debe abrir con Postman
    Dentro de Postman ejecutar en el siguiente orden para cumplir con los casos de uso.
    - create cliente 1
    - create cliente 2
    - create cliente 3
    - create cuenta 1
    - create cuenta 2
    - create cuenta 3
    - create cuenta 4
    - create cuenta 5
    - create movimiento 1
    - create movimiento 2
    - create movimiento 3
    - create movimiento 4
    - reportemovimientos

    Para realizar otras pruebas tomar en cuenta el campo clientId cuando se crea un cliente que es nuestro identificador para los clientes.

