package com.backend.chopper.service;

import com.backend.chopper.model.Producto;

import java.util.List;

public interface IProductoService {

    // crud = Create Read Update Delete

    //create = guardar => INSERT INTO cliente (column 1, column 2....) VALUES (cliente.value 1, cliente.value 2...)
    void crearProducto(Producto producto);

    //read - single one => SELECT * FROM cliente WHERE idCliente= id
    Producto buscarProductoById(Integer id);

    //read - list => SELECT * FROM cliente
    List<Producto> buscarProductos();

    //Update
    void actualizarProducto(int id,String nuevoCodigoProducto, String nuevoNombreProducto, Double nuevoPrecio, String nuevaCategoria, String nuevaDescripcion, Integer nuevoStock, Double nuevoDescuentoVip, String nuevaUrl);

    //Delete = DELETE FROM cliente WHERE idcliente= id
    void eliminarProducto(Integer id);
}
