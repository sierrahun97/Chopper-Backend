package com.backend.chopper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private int cantidad;
    private double precio_unitario;
    private double subtotal;

    @ManyToOne
    @JoinColumn (name = "venta_id")
    @JsonIgnore
    private Venta venta;

    @ManyToOne
    @JoinColumn (name = "producto_id")
    private Producto producto;


    public DetalleVenta() {
    }

    public DetalleVenta(int id_detalle_venta, int cantidad, double precio_unitario, double subtotal) {
        this.id_detalle_venta = id_detalle_venta;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
    }
}