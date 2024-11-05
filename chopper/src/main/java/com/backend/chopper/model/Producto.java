package com.backend.chopper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Double precio;
    private String categoria_producto;
    @Column(length = 100)
    private String descripcion_producto;
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer stock;
    @Column(columnDefinition = "double DEFAULT 0")
    private Double descuento_vip;
    private String url;

    @OneToMany (mappedBy = "producto")
    @JsonIgnore
    private List<DetalleVenta> detalleVentas;

    public Producto() {
    }

    public Producto(int id_producto, String codigo_producto, String nombre_producto, Double precio, String categoria_producto, String descripcion_producto, Integer stock, Double descuento_vip, String url) {
        this.id_producto = id_producto;
        this.codigo_producto = codigo_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.categoria_producto = categoria_producto;
        this.descripcion_producto = descripcion_producto;
        this.stock = stock;
        this.descuento_vip = descuento_vip;
        this.url = url;
    }
}
