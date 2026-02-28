package com.backend.chopper.service;

import com.backend.chopper.dto.CrearVentaDto;
import com.backend.chopper.model.Cliente;
import com.backend.chopper.model.DetalleVenta;
import com.backend.chopper.model.Producto;
import com.backend.chopper.model.Venta;
import com.backend.chopper.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Override
    public void crearVenta(Venta venta, int idCliente) {
        Cliente clienteEncontrado = clienteService.buscarClienteById(idCliente);
        if (clienteEncontrado != null) {
            venta.setFecha(LocalDateTime.now());
            venta.setCliente(clienteEncontrado);
            ventaRepository.save(venta);
        } else {
            System.out.println("No se encontro el cliente");
        }
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
    public void actualizarVenta(int id, int id_cliente, String nuevoCodigoVenta, double nuevoTotal) {
        Venta venta = this.buscarVentaById(id);
        if (venta != null) {
            venta.setCodigo_venta(nuevoCodigoVenta);
            venta.setTotal(nuevoTotal);
            ventaRepository.save(venta);
        }
    }

    @Override
    public void eliminarVenta(Integer id) {
        ventaRepository.deleteById(id);
    }

    public void crearDetalleVenta(CrearVentaDto ventaDto, Integer idCliente) {
        Cliente clienteEncontrado = clienteService.buscarClienteById(idCliente);
        if (clienteEncontrado != null) {
            double subtotal = 0;
            Venta venta = ventaDto.getVenta();
            venta.setFecha(LocalDateTime.now());
            venta.setCliente(clienteEncontrado);
            Venta v = ventaRepository.save(venta);
            for (int j = 0; j < ventaDto.getProductos().size(); j++) {
                Producto p = productoService.buscarProductoById(ventaDto.getProductos().get(j).getId_producto());
                Integer cantidad = ventaDto.getCantidades(j);
                DetalleVenta detalleVenta = detalleVentaService.crearDetalleVenta(p, cantidad, v);
                subtotal += detalleVenta.getSubtotal();
            }
            v.setTotal(subtotal);
            ventaRepository.save(v);
        } else {
            System.out.println("No se encontro el cliente");
        }
    }

}
