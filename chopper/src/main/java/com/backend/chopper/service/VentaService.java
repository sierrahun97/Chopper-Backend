package com.backend.chopper.service;

import com.backend.chopper.model.Cliente;
import com.backend.chopper.model.Venta;
import com.backend.chopper.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Override
    public void crearVenta(Venta venta) {
    ventaRepository.save(venta);
    }

    @Override
    public Venta buscarVentaById(Integer id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        return venta;
    }

    @Override
    public List<Venta> buscarVentas() {
        List<Venta> listaVentas = ventaRepository.findAll();
        return listaVentas;
    }

    @Override
    public void actualizarVenta(int id, int id_cliente, String nuevoCodigoVenta, Date nuevaFecha, double nuevoTotal) {
    Venta venta = this.buscarVentaById(id);
    venta.setCodigo_venta(nuevoCodigoVenta);
    venta.setFecha(nuevaFecha);
    venta.setTotal(nuevoTotal);
    }

    @Override
    public void eliminarVenta(Integer id) {
    ventaRepository.deleteById(id);
    }
}
