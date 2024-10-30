package com.backend.chopper.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ventas")

public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_venta;
    private int id_cliente;
    private String codigo_venta;
    private LocalDate fecha;
    private double total;

    public Venta() {
    }

    public Venta(int id_venta, int id_cliente, String codigo_venta, LocalDate fecha, double total) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.codigo_venta = codigo_venta;
        this.fecha = fecha;
        this.total = total;
    }
}

