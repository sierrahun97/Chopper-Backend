package com.backend.chopper.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detalleventa")

public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private int id_detalle_venta;
    private int id_cliente;
    private int id_producto;
    private int cantidad;
    private double precio_unitario;
    private double subtotal;

    @ManyToOne
    @JoinColumn (name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn (name = "producto_id")
    private Producto producto;


    public DetalleVenta() {
    }

    public DetalleVenta(int id_detalle_venta, int id_cliente, int id_producto, int cantidad, double precio_unitario, double subtotal) {
        this.id_detalle_venta = id_detalle_venta;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
    }
}