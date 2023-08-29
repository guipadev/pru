package com.sysman.prueba.controllers;

import com.sysman.prueba.exceptions.InvalidDateException;
import com.sysman.prueba.exceptions.MaterialsException;
import com.sysman.prueba.models.dto.ApiResponse;
import com.sysman.prueba.models.dto.MaterialDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;


import com.sysman.prueba.services.impl.MaterialServiceImpl;
import java.time.LocalDate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/materiales")
public class MaterialController {

    private final MaterialServiceImpl materialService;

    private static final String MATERIALS_FOUND_MESSAGE = "Materiales encontrados!";

    @GetMapping
    public ResponseEntity<ApiResponse<List<MaterialDto>>> getAllMaterials() {
        List<MaterialDto> materials = materialService.getAllMaterials();
        if (materials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "No se encontraron materiales.", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "Materiales encontrados.", materials));
    }


    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<ApiResponse<List<MaterialDto>>> getMaterialsByType(@PathVariable String tipo) {
        List<MaterialDto> materials = materialService.getMaterialsByType(tipo);
        if (materials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "No se encontraron materiales con el tipo especificado.", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "Materiales encontrados.", materials));
    }


    @GetMapping("/fecha-compra/{fechaCompra}")
    public ResponseEntity<ApiResponse<List<MaterialDto>>> getMaterialsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaCompra) {
        List<MaterialDto> materials = materialService.getMaterialsByDate(fechaCompra);
        if (materials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "No se encontraron materiales con la fecha de compra especificada.", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, MATERIALS_FOUND_MESSAGE, materials));
    }


    @GetMapping("/serial/{serial}")
    public ResponseEntity<ApiResponse<List<MaterialDto>>> getMaterialsBySerial(@PathVariable String serial) {
        List<MaterialDto> materials = materialService.getMaterialsBySerial(serial);
        if (materials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "No se encontraron materiales con el serial especificado.", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, MATERIALS_FOUND_MESSAGE, materials));
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createMaterial(@RequestBody @Valid MaterialDto materialDTO) {
        try {
            materialService.createMaterial(materialDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, "Material creado exitosamente!", null));
        } catch (InvalidDateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, e.getMessage(), null));
        } catch (MaterialsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error al crear el material: " + e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error desconocido al crear el material: " + e.getMessage(), null));
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updateMaterial(@PathVariable Long id, @RequestBody @Valid MaterialDto materialDTO) {
        try {
            materialService.updateMaterial(id, materialDTO);

            return ResponseEntity.ok(new ApiResponse<>(200, "Material actualizado exitosamente!", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Material no encontrado con el ID: " + id, null));
        } catch (InvalidDateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, "Error al actualizar el material: " + e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error interno del servidor: " + e.getMessage(), null));
        }
    }





}

