package com.backend.chopper.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "productos")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_producto;
    @Column(unique = true)
    private String codigo_producto;
    @Column(unique = true)
    private String nombre_producto;
    private double precio;
    private String categoria_pruducto;
    @Column(length = 100)
    private String descripcion_producto;
    @Column(columnDefinition = "INT DEFAULT 0")
    private int stock;
    @Column(columnDefinition = "double DEFAULT 0")
    private double descuento_vip;
    private String url;

    @OneToMany (mappedBy = "producto")
    private List<DetalleVenta> detalleVentas;

    public Producto() {
    }

    public Producto(int id_producto, String codigo_producto, String nombre_producto, double precio, String categoria_pruducto, String descripcion_producto, int stock, double descuento_vip, String url) {
        this.id_producto = id_producto;
        this.codigo_producto = codigo_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.categoria_pruducto = categoria_pruducto;
        this.descripcion_producto = descripcion_producto;
        this.stock = stock;
        this.descuento_vip = descuento_vip;
        this.url = url;
    }
}
