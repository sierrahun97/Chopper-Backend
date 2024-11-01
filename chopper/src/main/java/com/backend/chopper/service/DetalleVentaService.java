package com.backend.chopper.service;

import com.backend.chopper.model.DetalleVenta;
import com.backend.chopper.repository.IDetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService implements IDetalleVentaService {

    @Autowired
    private IDetalleVentaRepository detalleVentaRepository;

    @Override
    public void crearDetalleVenta(DetalleVenta detalleVenta) {
        detalleVentaRepository.save(detalleVenta);
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
    public void actualizarDetalleVenta(int id, int id_cliente, int id_producto, int nuevaCantidad, double nuevoPrecioUnitario, double nuevoSubtotal) {
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
