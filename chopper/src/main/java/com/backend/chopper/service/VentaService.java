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

    }

    @Override
    public Cliente buscarVentaById(Integer id) {
        return null;
    }

    @Override
    public List<Venta> buscarVentas() {
        return List.of();
    }

    @Override
    public void actualizarVenta(int id, int id_cliente, String nuevoCodigoVenta, Date nuevaFecha, double nuevoTotal) {

    }

    @Override
    public void eliminarVenta(Integer id) {

    }
}
