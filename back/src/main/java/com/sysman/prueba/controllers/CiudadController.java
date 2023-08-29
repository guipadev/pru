package com.sysman.prueba.controllers;

import com.sysman.prueba.exceptions.CiudadException;
import com.sysman.prueba.models.dto.CiudadDto;
import com.sysman.prueba.services.impl.CiudadServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/ciudades")
public class CiudadController {

    private final CiudadServiceImpl ciudadService;

    @PostMapping
    public ResponseEntity<CiudadDto> createCiudad(@RequestBody CiudadDto ciudadDto) {
        try {
            CiudadDto createdCiudad = ciudadService.createCiudad(ciudadDto);
            return ResponseEntity.ok(createdCiudad);
        } catch (CiudadException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CiudadDto());
        }
    }
}