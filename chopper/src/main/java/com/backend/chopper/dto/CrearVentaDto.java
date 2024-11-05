package com.backend.chopper.dto;

import com.backend.chopper.model.Producto;
import com.backend.chopper.model.Venta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearVentaDto {
    private Venta venta;
    private List<Producto> productos;
    private Integer [] cantidades;

    public Integer getCantidades(int j) {
        return cantidades[j];
    }
}
