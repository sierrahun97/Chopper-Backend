# PRUEBAS DE ENDPOINTS - Chopper Backend

## Requisitos
- Aplicación ejecutándose en `http://localhost:8080`
- PostgreSQL con la base de datos `chopper_backend` creada
- Script `database_schema.sql` ejecutado (para tener datos de prueba)

## Herramientas para Probar
- **Navegador Web** (para GET simples)
- **Postman** (recomendado)
- **cURL** (línea de comandos)
- **Thunder Client** (extensión de VS Code)

---

## 1. ENDPOINTS DE CLIENTES

### 1.1 Obtener Todos los Clientes
```http
GET http://localhost:8080/cliente/traer
```

**Con cURL (PowerShell):**
```powershell
curl http://localhost:8080/cliente/traer
```

**Respuesta esperada:**
```json
[
    {
        "id_cliente": 1,
        "nombre_cliente": "Juan Pérez",
        "email": "juan@example.com",
        "contrasena": "123456",
        "telefono": "555-0001",
        "rol": "CLIENTE",
        "is_vip": false
    },
    {
        "id_cliente": 2,
        "nombre_cliente": "María García",
        "email": "maria@example.com",
        "contrasena": "123456",
        "telefono": "555-0002",
        "rol": "CLIENTE",
        "is_vip": true
    }
]
```

### 1.2 Buscar Cliente por ID
```http
GET http://localhost:8080/cliente/buscar/1
```

**Con cURL:**
```powershell
curl http://localhost:8080/cliente/buscar/1
```

### 1.3 Crear un Nuevo Cliente
```http
POST http://localhost:8080/cliente/crear
Content-Type: application/json

{
    "nombre_cliente": "Carlos López",
    "email": "carlos@example.com",
    "contrasena": "pass123",
    "telefono": "555-9999",
    "rol": "CLIENTE",
    "is_vip": false
}
```

**Con cURL (PowerShell):**
```powershell
$body = @{
    nombre_cliente = "Carlos López"
    email = "carlos@example.com"
    contrasena = "pass123"
    telefono = "555-9999"
    rol = "CLIENTE"
    is_vip = $false
} | ConvertTo-Json

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/cliente/crear" -Body $body -ContentType "application/json"
```

**Respuesta esperada:**
```
Cliente creado correctamente
```

### 1.4 Buscar Cliente por Email
```http
POST http://localhost:8080/cliente/buscar/email?email=juan@example.com
```

**Con cURL:**
```powershell
curl -X POST "http://localhost:8080/cliente/buscar/email?email=juan@example.com"
```

### 1.5 Actualizar Cliente
```http
PUT http://localhost:8080/cliente/editar/1?nombre=Juan%20Pérez%20Actualizado&telefono=555-1111
```

**Con cURL:**
```powershell
curl -X PUT "http://localhost:8080/cliente/editar/1?nombre=Juan%20Pérez%20Actualizado&telefono=555-1111"
```

### 1.6 Eliminar Cliente
```http
DELETE http://localhost:8080/personas/borrar/1
```

**Con cURL:**
```powershell
curl -X DELETE http://localhost:8080/personas/borrar/1
```

---

## 2. ENDPOINTS DE PRODUCTOS

### 2.1 Obtener Todos los Productos
```http
GET http://localhost:8080/producto/traer
```

**Con cURL:**
```powershell
curl http://localhost:8080/producto/traer
```

**Respuesta esperada:**
```json
[
    {
        "id_producto": 1,
        "codigo_producto": "PROD001",
        "nombre_producto": "Laptop HP",
        "precio": 899.99,
        "categoria_producto": "TECNOLOGÍA",
        "descripcion_producto": "Laptop HP 15 pulgadas",
        "stock": 10,
        "descuento_vip": 0.10,
        "url": "http://example.com/laptop"
    }
]
```

### 2.2 Buscar Producto por ID
```http
GET http://localhost:8080/producto/buscar/1
```

### 2.3 Crear un Nuevo Producto
```http
POST http://localhost:8080/producto/crear
Content-Type: application/json

{
    "codigo_producto": "PROD100",
    "nombre_producto": "Smartphone Samsung",
    "precio": 599.99,
    "categoria_producto": "TECNOLOGÍA",
    "descripcion_producto": "Smartphone Galaxy A54",
    "stock": 25,
    "descuento_vip": 0.12,
    "url": "http://example.com/samsung"
}
```

**Con PowerShell:**
```powershell
$body = @{
    codigo_producto = "PROD100"
    nombre_producto = "Smartphone Samsung"
    precio = 599.99
    categoria_producto = "TECNOLOGÍA"
    descripcion_producto = "Smartphone Galaxy A54"
    stock = 25
    descuento_vip = 0.12
    url = "http://example.com/samsung"
} | ConvertTo-Json

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/producto/crear" -Body $body -ContentType "application/json"
```

### 2.4 Actualizar Producto
```http
PUT http://localhost:8080/producto/editar/1?precio=799.99&stock=15
```

### 2.5 Eliminar Producto
```http
DELETE http://localhost:8080/producto/borrar/1
```

---

## 3. ENDPOINTS DE VENTAS

### 3.1 Obtener Todas las Ventas
```http
GET http://localhost:8080/venta/traer
```

**Con cURL:**
```powershell
curl http://localhost:8080/venta/traer
```

### 3.2 Buscar Venta por ID
```http
GET http://localhost:8080/venta/buscar/1
```

### 3.3 Crear una Venta Simple
```http
POST http://localhost:8080/venta/crear/1
Content-Type: application/json

{
    "codigo_venta": "VTA-001",
    "total": 899.99
}
```

**Nota:** El ID al final de la URL (ej: `/1`) es el ID del cliente.

### 3.4 Crear Venta con Detalles (Completa)
```http
POST http://localhost:8080/detalle-venta/crear/1
Content-Type: application/json

{
    "venta": {
        "codigo_venta": "VTA-002"
    },
    "productos": [
        {
            "id_producto": 1
        },
        {
            "id_producto": 2
        }
    ],
    "cantidades": [2, 3]
}
```

**Explicación:**
- `venta`: Datos básicos de la venta (el total se calcula automáticamente)
- `productos`: Array de productos, cada uno con su `id_producto`
- `cantidades`: Array con las cantidades de cada producto (mismo orden)

**Ejemplo con PowerShell:**
```powershell
$body = @{
    venta = @{
        codigo_venta = "VTA-002"
    }
    productos = @(
        @{ id_producto = 1 },
        @{ id_producto = 2 }
    )
    cantidades = @(2, 3)
} | ConvertTo-Json -Depth 10

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/detalle-venta/crear/1" -Body $body -ContentType "application/json"
```

**Respuesta esperada:**
```
Venta creada correctamente
```

---

## 4. PRUEBAS COMPLETAS - FLUJO DE TRABAJO

### Flujo 1: Registro de Cliente y Primera Compra

**Paso 1:** Crear un cliente nuevo
```powershell
$cliente = @{
    nombre_cliente = "Ana Torres"
    email = "ana@example.com"
    contrasena = "ana123"
    telefono = "555-7777"
    rol = "CLIENTE"
    is_vip = $false
} | ConvertTo-Json

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/cliente/crear" -Body $cliente -ContentType "application/json"
```

**Paso 2:** Buscar el cliente creado por email
```powershell
curl -X POST "http://localhost:8080/cliente/buscar/email?email=ana@example.com"
```

**Paso 3:** Crear una venta para el cliente (usar el ID obtenido)
```powershell
$venta = @{
    venta = @{
        codigo_venta = "VTA-ANA-001"
    }
    productos = @(
        @{ id_producto = 1 },
        @{ id_producto = 3 }
    )
    cantidades = @(1, 2)
} | ConvertTo-Json -Depth 10

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/detalle-venta/crear/4" -Body $venta -ContentType "application/json"
```

**Paso 4:** Verificar la venta
```powershell
curl http://localhost:8080/venta/traer
```

### Flujo 2: Gestión de Inventario

**Paso 1:** Ver stock actual
```powershell
curl http://localhost:8080/producto/traer
```

**Paso 2:** Actualizar stock de un producto
```powershell
curl -X PUT "http://localhost:8080/producto/editar/1?stock=50"
```

**Paso 3:** Verificar actualización
```powershell
curl http://localhost:8080/producto/buscar/1
```

---

## 5. VERIFICACIÓN DE BASE DE DATOS

Puedes verificar directamente en PostgreSQL:

```sql
-- Ver todos los clientes
SELECT * FROM clientes;

-- Ver todos los productos
SELECT * FROM productos;

-- Ver todas las ventas con nombre del cliente
SELECT v.id_venta, v.codigo_venta, v.fecha, v.total, c.nombre_cliente
FROM ventas v
JOIN clientes c ON v.cliente_id = c.id_cliente;

-- Ver detalles de una venta específica
SELECT 
    dv.id_detalle_venta,
    p.nombre_producto,
    dv.cantidad,
    dv.precio_unitario,
    dv.subtotal
FROM detalleventa dv
JOIN productos p ON dv.producto_id = p.id_producto
WHERE dv.venta_id = 1;

-- Reporte de ventas por cliente
SELECT 
    c.nombre_cliente,
    COUNT(v.id_venta) as total_ventas,
    SUM(v.total) as monto_total
FROM clientes c
LEFT JOIN ventas v ON c.id_cliente = v.cliente_id
GROUP BY c.id_cliente, c.nombre_cliente;
```

---

## 6. VALIDACIONES Y CASOS DE ERROR

### Error: Cliente no existe
```powershell
curl http://localhost:8080/cliente/buscar/999
```
**Respuesta:** `null` o `404`

### Error: Email duplicado
Intenta crear un cliente con un email que ya existe:
```powershell
$cliente = @{
    nombre_cliente = "Duplicado"
    email = "juan@example.com"
    contrasena = "123"
    telefono = "555-0000"
    rol = "CLIENTE"
    is_vip = $false
} | ConvertTo-Json

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/cliente/crear" -Body $cliente -ContentType "application/json"
```
**Respuesta:** Error de PostgreSQL por violación de constraint UNIQUE

### Error: Crear venta sin productos
```powershell
$venta = @{
    venta = @{ codigo_venta = "VTA-VACIA" }
    productos = @()
    cantidades = @()
} | ConvertTo-Json -Depth 10

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/detalle-venta/crear/1" -Body $venta -ContentType "application/json"
```

---

## 7. COLECCIÓN DE POSTMAN

Puedes importar esta colección en Postman:

```json
{
    "info": {
        "name": "Chopper Backend API",
        "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
    },
    "item": [
        {
            "name": "Clientes",
            "item": [
                {
                    "name": "Obtener todos los clientes",
                    "request": {
                        "method": "GET",
                        "header": [],
                        "url": "http://localhost:8080/cliente/traer"
                    }
                },
                {
                    "name": "Crear cliente",
                    "request": {
                        "method": "POST",
                        "header": [{"key": "Content-Type", "value": "application/json"}],
                        "body": {
                            "mode": "raw",
                            "raw": "{\n    \"nombre_cliente\": \"Test\",\n    \"email\": \"test@example.com\",\n    \"contrasena\": \"123\",\n    \"telefono\": \"555-0000\",\n    \"rol\": \"CLIENTE\",\n    \"is_vip\": false\n}"
                        },
                        "url": "http://localhost:8080/cliente/crear"
                    }
                }
            ]
        }
    ]
}
```

---

## 8. RESUMEN DE CÓDIGOS DE RESPUESTA

- **200 OK** - Solicitud exitosa (GET, PUT)
- **201 Created** - Recurso creado (POST)
- **204 No Content** - Eliminación exitosa (DELETE)
- **400 Bad Request** - Datos inválidos
- **404 Not Found** - Recurso no encontrado
- **500 Internal Server Error** - Error del servidor

---

**¡Listo para probar!** 🚀

Para más información, consulta:
- `README.md` - Documentación general
- `INSTRUCCIONES.md` - Guía de instalación
- `CAMBIOS.md` - Resumen de cambios realizados

