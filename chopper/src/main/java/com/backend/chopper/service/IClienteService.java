package com.backend.chopper.service;

import com.backend.chopper.model.Cliente;

import java.util.List;

public interface IClienteService {
    // crud = Create Read Update Delete

    //create = guardar => INSERT INTO cliente (column 1, column 2....) VALUES (cliente.value 1, cliente.value 2...)
    void crearCliente(Cliente cliente);

    //read - single one => SELECT * FROM cliente WHERE idCliente= id
    Cliente buscarClienteById(Integer id);

    //read - list => SELECT * FROM cliente
    List<Cliente> buscarClientes();

    //Update
    void actualizarCliente(int id, String nuevoNombre, String nuevoEmail, String nuevaContrasena, String nuevoTelefono, String nuevoRol, boolean nuevoVip);

    //Delete = DELETE FROM cliente WHERE idcliente= id
    void eliminarCliente(Integer id);

    Cliente buscarClienteByEmail(String email);

}
