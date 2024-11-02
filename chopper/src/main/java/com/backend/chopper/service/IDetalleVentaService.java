package com.backend.chopper.service;

import com.backend.chopper.model.DetalleVenta;
import com.backend.chopper.model.Producto;
import com.backend.chopper.model.Venta;

import java.util.List;

public interface IDetalleVentaService {


    // crud = Create Read Update Delete

    //create = guardar => INSERT INTO cliente (column 1, column 2....) VALUES (cliente.value 1, cliente.value 2...)
    DetalleVenta crearDetalleVenta(Producto producto, Integer cantidad, Venta venta);

    //read - single one => SELECT * FROM cliente WHERE idCliente= id
    DetalleVenta buscarDetalleVentaById(Integer id);

    //read - list => SELECT * FROM cliente
    List<DetalleVenta> buscarDetalleVentas();

    //Update => UPDATE cliente SET column 1 = "value1", column 2 = "value2..." WHERE idCliente= id
    void actualizarDetalleVenta(int id, int id_cliente, int id_producto, Integer nuevaCantidad, Double nuevoPrecioUnitario, Double nuevoSubtotal);

    //Delete = DELETE FROM cliente WHERE idcliente= id
    void eliminarDetalleVenta(Integer id);
}
