package com.sysman.prueba.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
@Data
public class MaterialDto implements Serializable {
    private Long id;
    @NotNull
    private String nombre;

    @NotBlank
    private String descripcion;
    private String tipo;
    private String serial;
    private String numeroInterno;
    @NotNull
    private Double precio;
    @NotNull
    private LocalDate fechaCompra;
    @NotNull
    private LocalDate fechaVenta;
    private EstadoMaterial estado;
}
