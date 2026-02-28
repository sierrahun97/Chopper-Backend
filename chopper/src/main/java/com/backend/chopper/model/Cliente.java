package com.backend.chopper.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clientes")

public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id_cliente;
    private String nombre_cliente;
    @Column (nullable = false, unique = true)
    private String email;
    private String contrasena;
    private String telefono;
    @Column(length = 100)
    private String rol;
    private Boolean is_vip;

    @OneToMany (mappedBy = "cliente")
    private List<Venta> ventas;

    public Cliente() {
    }

    public Cliente(int id_cliente, String nombre_cliente, String email, String contrasena, String telefono, String rol, Boolean is_vip) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.rol = rol;
        this.is_vip = is_vip;
    }
}
