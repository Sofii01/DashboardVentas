#  Dashboard de Ventas

Proyecto web desarrollado con **Spring Boot**, **MySQL**, **JasperReports** y **Docker**, que permite gestionar y generar **reportes de ventas** de distintas formas:
- Reporte con **todas las ventas**
- Reporte **agrupado por mes**
- Generación de una **factura individual** por venta

---

## Estructura del Proyecto

- **Back**: Spring Boot + JPA + JasperReports
- **Base de Datos**: MySQL
- **Contenedores**: Docker y Docker Compose
- **Documentación**: Swagger UI (`/swagger-ui/index.html`)

---

## Requisitos

- Java 17+
- Maven
- Docker + Docker Compose

---

### Archivo de configuración `.env`

Antes de levantar el proyecto, crear un archivo `.env` en la raíz del proyecto con el siguiente contenido:

```env
DB_HOST=mysql_cont_sales
DB_PORT=<port>
DB_NAME=ventas --sugerido --
DB_USERNAME=<username>
DB_PASSWORD=<password>
DB_OPTS=allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
```

### Datos de prueba
Se incluye un script SQL con datos de prueba: src/main/resources/db/inserts.sql

Podés importarlo en tu instancia MySQL para hacer pruebas rápidamente.
### Cómo levantar el proyecto con Docker
1. Compilar el proyecto
  ``` mvn clean package -DskipTests```
2. Crear la imagen del backend
   ``` docker build -t dashboardventas .```
3. Iniciar los contenedores (App + MySQL)
   ```docker-compose up --build ```
### Endpoints útiles
   /api/report/sale → Reporte de todas las ventas

/api/report/saleByMonth/{month} → Reporte por mes (ej. /saleByMonth/{month})

/api/report/sale/{id} → Reporte individual tipo factura
### Reportes
Todos los reportes son generados en formato PDF. Pueden visualizarse, descargarse o enviarse a imprimir desde el cliente (frontend).

Hecho con 💻 por Sofia Britos