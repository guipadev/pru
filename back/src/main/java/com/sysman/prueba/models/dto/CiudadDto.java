package com.sysman.prueba.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CiudadDto implements Serializable {
    private Long id;
    private String nombre;
}
