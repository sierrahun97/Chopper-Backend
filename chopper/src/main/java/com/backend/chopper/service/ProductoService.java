package com.backend.chopper.service;

import com.backend.chopper.model.Producto;
import com.backend.chopper.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public void crearProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public Producto buscarProductoById(Integer id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        return producto;
    }

    @Override
    public List<Producto> buscarProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos;
    }

    @Override
    public void actualizarProducto(int id, String nuevoCodigoProducto, String nuevoNombreProducto, Double nuevoPrecio, String nuevaCategoria, String nuevaDescripcion, Integer nuevoStock, Double nuevoDescuentoVip, String nuevaUrl) {

        Producto producto = this.buscarProductoById(id);
        if (producto == null) {
            System.out.println("No se encontró el producto");
            return;
        }

        if (nuevoCodigoProducto != null) {
            producto.setCodigo_producto(nuevoCodigoProducto);
        }
        if (nuevoNombreProducto != null) {
            producto.setNombre_producto(nuevoNombreProducto);
        }
        if (nuevoPrecio != 0) {
            producto.setPrecio(nuevoPrecio);
        }
        if (nuevaCategoria != null) {
            producto.setCategoria_producto(nuevaCategoria);
        }
        if (nuevaDescripcion != null) {
            producto.setDescripcion_producto(nuevaDescripcion);
        }
        if (nuevoStock != null) {
            producto.setStock(nuevoStock);
        }
        if (nuevoDescuentoVip != null) {
            producto.setDescuento_vip(nuevoDescuentoVip);
        }
        if (nuevaUrl != null) {
            producto.setUrl(nuevaUrl);
        }

        productoRepository.save(producto);
        System.out.println("Producto actualizado");
    }


    @Override
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}
