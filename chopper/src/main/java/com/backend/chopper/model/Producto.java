package com.backend.chopper.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_producto;
    private String codigo_producto;
    private String nombre_producto;
    private double precio;
    private String categoria_pruducto;
    private String descripcion_producto;
    private int stock;
    private double descuento_vip;
    private String url;

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
