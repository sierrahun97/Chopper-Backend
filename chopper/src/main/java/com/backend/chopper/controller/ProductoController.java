package com.backend.chopper.controller;

import com.backend.chopper.model.Producto;
import com.backend.chopper.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:3000"})
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/producto/traer")
    public List<Producto> buscarProductos() {
        return productoService.buscarProductos();
    }

    @GetMapping("/producto/buscar/{id_producto}")
    public ResponseEntity<?> buscarProductoById(@PathVariable int id_producto) {
        try {
            Producto producto = productoService.buscarProductoById(id_producto);
            return ResponseEntity.ok(producto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/producto/crear")
    public String crearProducto(@RequestBody Producto producto) {
        productoService.crearProducto(producto);
        return "Producto creado correctamente";
    }

    @PutMapping("/producto/editar/{id_producto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int id_producto,
                                       @RequestParam(required = false, name = "codigo_producto") String codigo_producto,
                                       @RequestParam(required = false, name = "nombre_producto") String nombre_producto,
                                       @RequestParam(required = false, name = "precio") Double precio,
                                       @RequestParam(required = false, name = "categoria_producto") String categoria_producto,
                                       @RequestParam(required = false, name = "descripcion_producto") String descripcion_producto,
                                       @RequestParam(required = false, name = "stock") Integer stock,
                                       @RequestParam(required = false, name = "descuento_vip") Double descuento_vip,
                                       @RequestParam(required = false, name = "url") String url) {
        try {
            productoService.actualizarProducto(id_producto, codigo_producto, nombre_producto, precio, categoria_producto, descripcion_producto, stock, descuento_vip, url);
            Producto producto = productoService.buscarProductoById(id_producto);
            return ResponseEntity.ok(producto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/producto/borrar/{id_producto}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id_producto) {
        try {
            productoService.eliminarProducto(id_producto);
            return ResponseEntity.ok("Producto borrado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
