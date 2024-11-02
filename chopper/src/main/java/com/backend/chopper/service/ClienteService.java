package com.backend.chopper.service;

import com.backend.chopper.model.Cliente;
import com.backend.chopper.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public void crearCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarClienteById(Integer id) {
       return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();

    }

    @Override
    public void actualizarCliente(int id, String nuevoNombre, String nuevoEmail, String nuevaContrasena, String nuevoTelefono, String nuevoRol, boolean nuevoVip) {
        Cliente clienteEncontrado = clienteRepository.findById(id).orElse(null);
        if (clienteEncontrado == null) {
            System.out.println("No se encontró el cliente");
        } else {
            if (nuevoNombre != null) {
                clienteEncontrado.setNombre_cliente(nuevoNombre);
            }
            if (nuevoEmail != null) {
                clienteEncontrado.setEmail(nuevoEmail);
            }
            if (nuevaContrasena != null) {
                clienteEncontrado.setContrasena(nuevaContrasena);
            }
            if (nuevoTelefono != null) {
                clienteEncontrado.setTelefono(nuevoTelefono);
            }
            if (nuevoRol != null) {
                clienteEncontrado.setRol(nuevoRol);
            }
            clienteEncontrado.setIs_vip(nuevoVip);

            clienteRepository.save(clienteEncontrado);
            System.out.println("Cliente actualizado");
        }
    }


    @Override
    public void eliminarCliente(Integer id) {
        Cliente clienteEncontrado = clienteRepository.findById(id).orElse(null);
        if (clienteEncontrado != null) {
            clienteRepository.delete(clienteEncontrado);
            System.out.println("Usuario eliminado con exito");
        } else {
            System.out.println("Usuario no encontrado");
        }
    }
}
