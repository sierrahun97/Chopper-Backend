package com.backend.chopper.controller;


import com.backend.chopper.model.Cliente;
import com.backend.chopper.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    //localhost:8080/api/cliente/traer **Con request mapping
    //localhost:8080/cliente/traer
    @GetMapping ("/cliente/traer")
    public List<Cliente> getClientes(){
        return clienteService.buscarClientes();
    }

    @GetMapping("/cliente/buscar/{id_cliente}")
    public Cliente buscarClienteById(@PathVariable int id_cliente){
        return clienteService.buscarClienteById(id_cliente);
    }

    @PostMapping("/cliente/crear")
    public String crearCliente(@RequestBody Cliente cliente){
        clienteService.crearCliente(cliente);
        return "Cliente creado correctamente";
    }

    @PutMapping("/cliente/editar/{id_cliente}")
    public Cliente actualizarCliente(@PathVariable int id_cliente,
                                     @RequestParam (required = false, name = "nombre") String nuevoNombre,
                                     @RequestParam (required = false, name = "email") String nuevoEmail,
                                     @RequestParam (required = false, name = "contrasena") String nuevaContrasena,
                                     @RequestParam (required = false, name = "telefono") String nuevoTelefono,
                                     @RequestParam (required = false, name = "rol") String nuevoRol,
                                     @RequestParam (required = false, name = "isvip") boolean nuevoVip){
        clienteService.actualizarCliente(id_cliente, nuevoNombre, nuevoEmail, nuevaContrasena, nuevoTelefono, nuevoRol, nuevoVip);
        Cliente cliente = clienteService.buscarClienteById(id_cliente);
        return cliente;
    }

    @DeleteMapping ("/personas/borrar/{id_cliente}")
    public String borrarCliente(@PathVariable int id_cliente){
        clienteService.eliminarCliente(id_cliente);
        return "Cliente eliminado correctamente";
    }
}
