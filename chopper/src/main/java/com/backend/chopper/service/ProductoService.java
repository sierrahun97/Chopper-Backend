package com.backend.chopper.service;

import com.backend.chopper.model.Cliente;
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
    public void actualizarProducto(int id, String nuevoCodigoProducto, String nuevoNombreProducto, double nuevoPrecio, String nuevaCategoria, String nuevaDescripcion, int nuevoStock, double nuevoDescuentoVip, String nuevaUrl) {

    Producto producto = this.buscarProductoById(id);
    producto.setCodigo_producto(nuevoCodigoProducto);
    producto.setNombre_producto(nuevoNombreProducto);
    producto.setPrecio(nuevoPrecio);
    producto.setCategoria_pruducto(nuevaCategoria);
    producto.setDescripcion_producto(nuevaDescripcion);
    producto.setStock(nuevoStock);
    producto.setDescuento_vip(nuevoDescuentoVip);
    producto.setUrl(nuevaUrl);

    productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Integer id) {
    productoRepository.deleteById(id);
    }
}
