# dog-api-demo

Ejercicio usa la API de "Dog API" (https://dog.ceo/dog-api/) como fuente de datos

## Para Ejecutar

```
mvn spring-boot:run
```

Se publica el endpoint get http://localhost:8080/v1/breed/{breed} donde {breed} es el parametro de entrada

## Documentacion API

Se implementa Swagger para la documentación, la interfaz se puede consultar en http://localhost:8080/swagger-ui.html

## Testing

Se genera reporte estatico para la cobertura de los test, se puede consultar en ${basedir}/target/site/jacoco/index.html ejecutando:

```
mvn test
```

