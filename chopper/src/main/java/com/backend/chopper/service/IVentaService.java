package com.backend.chopper.service;

import com.backend.chopper.model.Cliente;
import com.backend.chopper.model.Venta;

import java.util.Date;
import java.util.List;

public interface IVentaService {


    // crud = Create Read Update Delete


    //create = guardar => INSERT INTO cliente (column 1, column 2....) VALUES (cliente.value 1, cliente.value 2...)
    void crearVenta(Venta venta, int idCliente);


    //read - single one => SELECT * FROM cliente WHERE idCliente= id
    Venta buscarVentaById(Integer id);

    //read - list => SELECT * FROM cliente
    List<Venta> buscarVentas();

    //Update
    void actualizarVenta(int id, int id_cliente, String nuevoCodigoVenta, Date nuevaFecha, double nuevoTotal);

    //Delete = DELETE FROM cliente WHERE idcliente= id
    void eliminarVenta(Integer id);
}
