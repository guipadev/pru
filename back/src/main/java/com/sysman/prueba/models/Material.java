package com.sysman.prueba.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String tipo;
    private String serial;
    private String numeroInterno;
    private Double precio;
    private LocalDate fechaCompra;
    private LocalDate fechaVenta;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;
}
