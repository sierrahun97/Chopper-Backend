package com.backend.chopper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ventas")

public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_venta;
    @Column(unique = true)
    private String codigo_venta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private double total;

    @ManyToOne
    @JoinColumn (name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    @OneToMany (mappedBy = "venta")
    private List<DetalleVenta> detallesVenta;



    public Venta() {
    }

    public Venta(int id_venta, String codigo_venta, Date fecha, double total) {
        this.id_venta = id_venta;
        this.codigo_venta = codigo_venta;
        this.fecha = fecha;
        this.total = total;
    }
}

