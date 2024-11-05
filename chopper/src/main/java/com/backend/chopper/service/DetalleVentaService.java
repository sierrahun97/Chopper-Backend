package com.backend.chopper.service;

import com.backend.chopper.model.DetalleVenta;
import com.backend.chopper.model.Producto;
import com.backend.chopper.model.Venta;
import com.backend.chopper.repository.IDetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService implements IDetalleVentaService {

    @Autowired
    private IDetalleVentaRepository detalleVentaRepository;

    @Override
    public DetalleVenta crearDetalleVenta(Producto producto, Integer cantidad, Venta venta) {
//        detalleVentaRepository.save(detalleVenta);
        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setProducto(producto);
        detalleVenta.setPrecio_unitario(producto.getPrecio());
        detalleVenta.setCantidad(cantidad);
        detalleVenta.setVenta(venta);
        detalleVenta.setSubtotal(producto.getPrecio()*cantidad);
        System.out.println(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);

    }

    @Override
    public DetalleVenta buscarDetalleVentaById(Integer id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElse(null);
        return detalleVenta;
    }

    @Override
    public List<DetalleVenta> buscarDetalleVentas() {
        List<DetalleVenta> listaDetalleVentas = detalleVentaRepository.findAll();
        return listaDetalleVentas;
    }

    @Override
    public void actualizarDetalleVenta(int id, int id_cliente, int id_producto, Integer nuevaCantidad, Double nuevoPrecioUnitario, Double nuevoSubtotal) {
    DetalleVenta detalleVenta = this.buscarDetalleVentaById(id);
    detalleVenta.setCantidad(nuevaCantidad);
    detalleVenta.setPrecio_unitario(nuevoPrecioUnitario);
    detalleVenta.setSubtotal(nuevoSubtotal);
    detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void eliminarDetalleVenta(Integer id) {
    detalleVentaRepository.deleteById(id);
    }
}
