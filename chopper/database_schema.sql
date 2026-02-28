-- Script DDL para la base de datos chopper_backend en PostgreSQL

-- Crear la base de datos (ejecutar como superusuario)
-- CREATE DATABASE chopper_backend;

-- Conectarse a la base de datos
-- \c chopper_backend;

-- Tabla de clientes
CREATE TABLE IF NOT EXISTS clientes (
    id_cliente SERIAL PRIMARY KEY,
    nombre_cliente VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255),
    telefono VARCHAR(255),
    rol VARCHAR(100),
    is_vip BOOLEAN DEFAULT FALSE
);

-- Tabla de productos
CREATE TABLE IF NOT EXISTS productos (
    id_producto SERIAL PRIMARY KEY,
    codigo_producto VARCHAR(255) UNIQUE,
    nombre_producto VARCHAR(255) UNIQUE,
    precio DOUBLE PRECISION,
    categoria_producto VARCHAR(255),
    descripcion_producto VARCHAR(100),
    stock INTEGER,
    descuento_vip DOUBLE PRECISION,
    url VARCHAR(255)
);

-- Tabla de ventas
CREATE TABLE IF NOT EXISTS ventas (
    id_venta SERIAL PRIMARY KEY,
    codigo_venta VARCHAR(255) UNIQUE,
    fecha TIMESTAMP,
    total DOUBLE PRECISION,
    cliente_id INTEGER,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id_cliente) ON DELETE CASCADE
);

-- Tabla de detalles de venta
CREATE TABLE IF NOT EXISTS detalleventa (
    id_detalle_venta SERIAL PRIMARY KEY,
    cantidad INTEGER,
    precio_unitario DOUBLE PRECISION,
    subtotal DOUBLE PRECISION,
    venta_id INTEGER,
    producto_id INTEGER,
    CONSTRAINT fk_venta FOREIGN KEY (venta_id) REFERENCES ventas(id_venta) ON DELETE CASCADE,
    CONSTRAINT fk_producto FOREIGN KEY (producto_id) REFERENCES productos(id_producto) ON DELETE CASCADE
);

-- Crear índices para mejorar el rendimiento
CREATE INDEX IF NOT EXISTS idx_clientes_email ON clientes(email);
CREATE INDEX IF NOT EXISTS idx_productos_codigo ON productos(codigo_producto);
CREATE INDEX IF NOT EXISTS idx_ventas_cliente ON ventas(cliente_id);
CREATE INDEX IF NOT EXISTS idx_detalleventa_venta ON detalleventa(venta_id);
CREATE INDEX IF NOT EXISTS idx_detalleventa_producto ON detalleventa(producto_id);

-- Datos de prueba (opcional)

-- Insertar clientes de prueba
INSERT INTO clientes (nombre_cliente, email, contrasena, telefono, rol, is_vip)
VALUES
    ('Juan Pérez', 'juan@example.com', '123456', '555-0001', 'CLIENTE', FALSE),
    ('María García', 'maria@example.com', '123456', '555-0002', 'CLIENTE', TRUE),
    ('Admin User', 'admin@example.com', 'admin123', '555-0003', 'ADMIN', FALSE)
ON CONFLICT (email) DO NOTHING;

-- Insertar productos de prueba
INSERT INTO productos (codigo_producto, nombre_producto, precio, categoria_producto, descripcion_producto, stock, descuento_vip, url)
VALUES
    ('PROD001', 'Laptop HP', 899.99, 'TECNOLOGÍA', 'Laptop HP 15 pulgadas', 10, 0.10, 'http://example.com/laptop'),
    ('PROD002', 'Mouse Logitech', 25.99, 'TECNOLOGÍA', 'Mouse inalámbrico', 50, 0.05, 'http://example.com/mouse'),
    ('PROD003', 'Teclado Mecánico', 79.99, 'TECNOLOGÍA', 'Teclado mecánico RGB', 30, 0.08, 'http://example.com/teclado'),
    ('PROD004', 'Monitor LG 24"', 199.99, 'TECNOLOGÍA', 'Monitor Full HD 24 pulgadas', 15, 0.12, 'http://example.com/monitor'),
    ('PROD005', 'Auriculares Sony', 149.99, 'AUDIO', 'Auriculares con cancelación de ruido', 25, 0.15, 'http://example.com/auriculares')
ON CONFLICT (codigo_producto) DO NOTHING;

-- Comentarios sobre las tablas
COMMENT ON TABLE clientes IS 'Tabla que almacena la información de los clientes';
COMMENT ON TABLE productos IS 'Tabla que almacena el catálogo de productos';
COMMENT ON TABLE ventas IS 'Tabla que almacena las ventas realizadas';
COMMENT ON TABLE detalleventa IS 'Tabla que almacena los detalles de cada venta';

-- Verificar las tablas creadas
-- SELECT table_name FROM information_schema.tables WHERE table_schema = 'public' ORDER BY table_name;

