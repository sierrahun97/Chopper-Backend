# Chopper Backend - Sistema de Ventas

API REST desarrollada con Spring Boot para gestión de ventas, clientes y productos.

## Requisitos Previos

1. **Java 17 o superior** - [Descargar Java](https://adoptium.net/)
2. **PostgreSQL 12 o superior** - [Descargar PostgreSQL](https://www.postgresql.org/download/)
3. **Maven** (incluido en el proyecto como wrapper)

## Configuración de la Base de Datos

### 1. Crear la Base de Datos

Ejecuta los siguientes comandos en PostgreSQL:

```sql
CREATE DATABASE chopper_backend;
```

### 2. Ejecutar el Script DDL

Conéctate a la base de datos y ejecuta el archivo `database_schema.sql`:

```bash
psql -U postgres -d chopper_backend -f database_schema.sql
```

O desde pgAdmin, abre el archivo y ejecútalo.

### 3. Configuración de Credenciales

El archivo `application.properties` está configurado con:
- **Host:** localhost
- **Puerto:** 5432
- **Base de datos:** chopper_backend
- **Usuario:** postgres
- **Contraseña:** postgres

Si tus credenciales son diferentes, modifica el archivo:
`src/main/resources/application.properties`

## Instalación y Ejecución

### Opción 1: Usando el JAR precompilado

```bash
java -jar target/chopper-0.0.1-SNAPSHOT.jar
```

### Opción 2: Compilar y ejecutar

```bash
# Windows
.\mvnw.cmd clean package -DskipTests
java -jar target/chopper-0.0.1-SNAPSHOT.jar

# Linux/Mac
./mvnw clean package -DskipTests
java -jar target/chopper-0.0.1-SNAPSHOT.jar
```

### Opción 3: Ejecutar con Maven

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

## Verificar la Conexión

Una vez iniciada la aplicación, verifica en los logs:

```
Hibernate: create table if not exists clientes (...)
...
Started ChopperApplication in X seconds
```

Si ves estos mensajes, la aplicación está conectada correctamente a PostgreSQL.

## Endpoints de la API

### Clientes

- **GET** `/cliente/traer` - Obtener todos los clientes
- **GET** `/cliente/buscar/{id}` - Buscar cliente por ID
- **POST** `/cliente/crear` - Crear nuevo cliente
- **PUT** `/cliente/editar/{id}` - Actualizar cliente
- **DELETE** `/personas/borrar/{id}` - Eliminar cliente
- **POST** `/cliente/buscar/email` - Buscar cliente por email

### Productos

- **GET** `/producto/traer` - Obtener todos los productos
- **GET** `/producto/buscar/{id}` - Buscar producto por ID
- **POST** `/producto/crear` - Crear nuevo producto
- **PUT** `/producto/editar/{id}` - Actualizar producto
- **DELETE** `/producto/borrar/{id}` - Eliminar producto

### Ventas

- **GET** `/venta/traer` - Obtener todas las ventas
- **GET** `/venta/buscar/{id}` - Buscar venta por ID
- **POST** `/venta/crear/{idCliente}` - Crear nueva venta
- **POST** `/venta/crearDetalle/{idCliente}` - Crear venta con detalles
- **PUT** `/venta/editar/{id}` - Actualizar venta
- **DELETE** `/venta/borrar/{id}` - Eliminar venta

## Ejemplo de Uso con cURL

### Crear un cliente:

```bash
curl -X POST http://localhost:8080/cliente/crear \
  -H "Content-Type: application/json" \
  -d '{
    "nombre_cliente": "Juan Pérez",
    "email": "juan@example.com",
    "contrasena": "123456",
    "telefono": "555-1234",
    "rol": "CLIENTE",
    "is_vip": false
  }'
```

### Obtener todos los clientes:

```bash
curl http://localhost:8080/cliente/traer
```

## Estructura del Proyecto

```
chopper/
├── src/main/java/com/backend/chopper/
│   ├── ChopperApplication.java      # Clase principal
│   ├── config/                       # Configuraciones
│   ├── controller/                   # Controladores REST
│   ├── dto/                         # Data Transfer Objects
│   ├── model/                       # Entidades JPA
│   ├── repository/                  # Repositorios JPA
│   └── service/                     # Lógica de negocio
├── src/main/resources/
│   └── application.properties       # Configuración de la aplicación
├── database_schema.sql              # Script DDL de PostgreSQL
└── pom.xml                         # Dependencias Maven
```

## Tecnologías Utilizadas

- **Spring Boot 3.3.5** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL** - Base de datos
- **Lombok** - Reducción de código boilerplate
- **Maven** - Gestión de dependencias

## Solución de Problemas

### Error: "Connection refused"

Verifica que PostgreSQL esté ejecutándose:

```bash
# Windows
pg_ctl status

# Linux
sudo systemctl status postgresql
```

### Error: "Database does not exist"

Crea la base de datos manualmente:

```sql
CREATE DATABASE chopper_backend;
```

### Error: "JAVA_HOME not found"

Configura la variable de entorno JAVA_HOME:

```bash
# Windows
setx JAVA_HOME "C:\Program Files\Java\jdk-17"

# Linux/Mac
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
```

## Contacto

Para más información o reportar problemas, contacta al equipo de desarrollo.

