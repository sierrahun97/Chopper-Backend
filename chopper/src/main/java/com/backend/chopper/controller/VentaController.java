package com.backend.chopper.controller;


import com.backend.chopper.dto.CrearVentaDto;
import com.backend.chopper.model.DetalleVenta;
import com.backend.chopper.model.Producto;
import com.backend.chopper.model.Venta;
import com.backend.chopper.service.IVentaService;
import com.backend.chopper.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping ("/venta/traer")
    public List<Venta> listaVentas(){
        return ventaService.buscarVentas();
    }

    @GetMapping ("/venta/buscar/{id_venta}")
    public Venta buscarVentaById(@PathVariable int id_venta){
        return ventaService.buscarVentaById(id_venta);
    }

    @PostMapping ("/venta/crear/{id_cliente}")
    public String crearVenta(@RequestBody Venta venta,@PathVariable Integer id_cliente){
        ventaService.crearVenta(venta, id_cliente);
        return "Venta creada correctamente";
    }

    @PostMapping ("/detalle-venta/crear/{id_cliente}")
    public String crearDetalleVenta(@RequestBody CrearVentaDto ventaDto,
                                    @PathVariable Integer id_cliente){
        ventaService.crearDetalleVenta(ventaDto, id_cliente);
        return "Venta creada correctamente";
    }
}
