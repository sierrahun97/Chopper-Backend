# Instrucciones para Configurar y Ejecutar Chopper Backend

## Paso 1: Verificar Java

Abre PowerShell y ejecuta:
```powershell
java -version
```

Si no tienes Java 17 o superior, descárgalo de: https://adoptium.net/

## Paso 2: Configurar PostgreSQL

### 2.1 Crear la base de datos

Abre el cliente psql o pgAdmin y ejecuta:

```sql
CREATE DATABASE chopper_backend;
```

### 2.2 Ejecutar el script DDL

Opción A - Usando psql desde la línea de comandos:
```powershell
cd C:\Users\jp200\OneDrive\Escritorio\Desarrollo\Java\Chopper-Backend\chopper
psql -U postgres -d chopper_backend -f database_schema.sql
```

Opción B - Usando pgAdmin:
1. Abre pgAdmin
2. Conecta a tu servidor PostgreSQL
3. Selecciona la base de datos "chopper_backend"
4. Abre el archivo "database_schema.sql"
5. Ejecuta el script (F5)

### 2.3 Verificar las credenciales

El proyecto está configurado para conectarse con:
- Usuario: `postgres`
- Contraseña: `postgres`

Si tu contraseña es diferente, edita el archivo:
`src/main/resources/application.properties`

Y cambia la línea:
```properties
spring.datasource.password=TU_CONTRASEÑA_AQUI
```

## Paso 3: Ejecutar la Aplicación

### Opción A: Ejecutar el JAR (más rápido)

```powershell
cd C:\Users\jp200\OneDrive\Escritorio\Desarrollo\Java\Chopper-Backend\chopper
java -jar target/chopper-0.0.1-SNAPSHOT.jar
```

### Opción B: Compilar y ejecutar (si modificaste el código)

```powershell
cd C:\Users\jp200\OneDrive\Escritorio\Desarrollo\Java\Chopper-Backend\chopper
.\mvnw.cmd clean package -DskipTests
java -jar target/chopper-0.0.1-SNAPSHOT.jar
```

## Paso 4: Verificar que Funciona

### 4.1 Revisar los logs

Cuando la aplicación inicie, deberías ver en la consola:

```
Hibernate: create table if not exists clientes ...
Hibernate: create table if not exists productos ...
...
Started ChopperApplication in X.XXX seconds
```

Esto significa que la aplicación está conectada correctamente a PostgreSQL.

### 4.2 Probar los endpoints

Abre un navegador o Postman y prueba:

**Obtener todos los clientes:**
```
GET http://localhost:8080/cliente/traer
```

Deberías ver una respuesta JSON con los clientes de prueba.

**Obtener todos los productos:**
```
GET http://localhost:8080/producto/traer
```

### 4.3 Crear un nuevo cliente

Usando Postman, cURL o cualquier cliente HTTP:

```bash
curl -X POST http://localhost:8080/cliente/crear ^
  -H "Content-Type: application/json" ^
  -d "{\"nombre_cliente\":\"Test User\",\"email\":\"test@example.com\",\"contrasena\":\"123456\",\"telefono\":\"555-9999\",\"rol\":\"CLIENTE\",\"is_vip\":false}"
```

## Solución de Problemas Comunes

### Error: "Connection refused" o "Cannot connect to database"

**Causa:** PostgreSQL no está ejecutándose o no está escuchando en el puerto 5432.

**Solución:**
1. Verifica que PostgreSQL esté ejecutándose:
   - Abre el Administrador de Servicios de Windows (services.msc)
   - Busca "PostgreSQL" y verifica que esté "En ejecución"
   - Si no, haz clic derecho → Iniciar

2. Verifica el puerto:
   - Por defecto PostgreSQL usa el puerto 5432
   - Si cambiaste el puerto, actualiza `application.properties`

### Error: "password authentication failed"

**Causa:** La contraseña en `application.properties` no coincide con tu PostgreSQL.

**Solución:**
1. Edita `src/main/resources/application.properties`
2. Cambia `spring.datasource.password=postgres` por tu contraseña real
3. Guarda el archivo
4. Reinicia la aplicación

### Error: "database does not exist"

**Causa:** No se creó la base de datos `chopper_backend`.

**Solución:**
```sql
CREATE DATABASE chopper_backend;
```

### Error: "JAVA_HOME is not set"

**Causa:** Java no está instalado o la variable de entorno no está configurada.

**Solución:**
1. Instala Java 17 o superior desde https://adoptium.net/
2. Configura JAVA_HOME:
   ```powershell
   setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot"
   ```
3. Cierra y abre una nueva ventana de PowerShell

## Endpoints Disponibles

### Clientes
- `GET /cliente/traer` - Listar todos los clientes
- `GET /cliente/buscar/{id}` - Buscar cliente por ID
- `POST /cliente/crear` - Crear nuevo cliente
- `PUT /cliente/editar/{id}` - Actualizar cliente
- `DELETE /personas/borrar/{id}` - Eliminar cliente
- `POST /cliente/buscar/email?email=xxx` - Buscar por email

### Productos
- `GET /producto/traer` - Listar todos los productos
- `GET /producto/buscar/{id}` - Buscar producto por ID
- `POST /producto/crear` - Crear nuevo producto
- `PUT /producto/editar/{id}` - Actualizar producto
- `DELETE /producto/borrar/{id}` - Eliminar producto

### Ventas
- `GET /venta/traer` - Listar todas las ventas
- `GET /venta/buscar/{id}` - Buscar venta por ID
- `POST /venta/crear/{idCliente}` - Crear nueva venta
- `POST /detalle-venta/crear/{idCliente}` - Crear venta con detalles

### Detalles de Venta
Los detalles se crean automáticamente cuando creas una venta completa.

## Estructura de la Base de Datos

```
chopper_backend
├── clientes (id_cliente, nombre_cliente, email, contrasena, telefono, rol, is_vip)
├── productos (id_producto, codigo_producto, nombre_producto, precio, categoria_producto, descripcion_producto, stock, descuento_vip, url)
├── ventas (id_venta, codigo_venta, fecha, total, cliente_id)
└── detalleventa (id_detalle_venta, cantidad, precio_unitario, subtotal, venta_id, producto_id)
```

## Datos de Prueba

El script `database_schema.sql` incluye datos de prueba:
- 3 clientes (incluyendo un admin)
- 5 productos de tecnología

## Puerto de la Aplicación

La aplicación se ejecuta en: **http://localhost:8080**

## Configuración CORS

La aplicación está configurada para aceptar peticiones desde:
- http://127.0.0.1:5500 (Live Server)

Si necesitas agregar más orígenes, edita:
`src/main/java/com/backend/chopper/config/WebConfig.java`

