package com.sysman.prueba.services;

import com.sysman.prueba.models.dto.MaterialDto;

import java.time.LocalDate;
import java.util.List;

public interface MaterialService {
    List<MaterialDto> getAllMaterials();
    List<MaterialDto> getMaterialsByType(String tipo);
    List<MaterialDto> getMaterialsByDate(LocalDate fechaCompra);
    List<MaterialDto> getMaterialsBySerial(String serial);
    MaterialDto createMaterial(MaterialDto materialDTO);
    MaterialDto updateMaterial(Long id, MaterialDto materialDTO);
}
