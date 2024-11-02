package com.backend.chopper.service;

import com.backend.chopper.model.Cliente;
import com.backend.chopper.model.DetalleVenta;
import com.backend.chopper.model.Producto;
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
            venta.setFecha(new Date());
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
    public void actualizarVenta(int id, int id_cliente, String nuevoCodigoVenta, Date nuevaFecha, double nuevoTotal) {
        Venta venta = this.buscarVentaById(id);
        venta.setCodigo_venta(nuevoCodigoVenta);
        venta.setFecha(nuevaFecha);
        venta.setTotal(nuevoTotal);
    }

    @Override
    public void eliminarVenta(Integer id) {
        ventaRepository.deleteById(id);
    }

    public void crearDetalleVenta(Venta venta, int idCliente, List<Producto> productos, Integer[] cantidades) {
        Cliente clienteEncontrado = clienteService.buscarClienteById(idCliente);
        if (clienteEncontrado != null) {
            venta.setFecha(new Date());
            venta.setCliente(clienteEncontrado);
            Venta v = ventaRepository.save(venta);
            int i = 0;
            for (Producto producto : productos) {
                Producto p = productoService.buscarProductoById(producto.getId_producto());
                Integer cantidad = cantidades[i];
                DetalleVenta detalleVenta = detalleVentaService.crearDetalleVenta(p, cantidad, v);
                i++;
            }
        } else {
            System.out.println("No se encontro el cliente");
        }
    }

}
