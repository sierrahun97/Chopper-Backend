package com.backend.chopper.service;

import com.backend.chopper.model.Venta;

import java.util.List;

public interface IVentaService {

    void crearVenta(Venta venta, int idCliente);

    Venta buscarVentaById(Integer id);

    List<Venta> buscarVentas();

    void actualizarVenta(int id, int id_cliente, String nuevoCodigoVenta, double nuevoTotal);

    void eliminarVenta(Integer id);
}
