package com.sysman.prueba.services.impl;

import com.sysman.prueba.exceptions.MaterialsDataAccessException;
import com.sysman.prueba.exceptions.MaterialsException;
import com.sysman.prueba.exceptions.InvalidDateException;

import com.sysman.prueba.models.Material;
import com.sysman.prueba.models.dto.MaterialDto;
import com.sysman.prueba.repositorys.MaterialRepository;
import com.sysman.prueba.services.MaterialService;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    private final ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(MaterialServiceImpl.class);


    @Override
    public List<MaterialDto> getAllMaterials() {
        try {
            List<Material> materials = materialRepository.findAll();

            logger.info("Materiales encontrados: {}", materials.size());

            return materials.stream()
                    .map(material -> modelMapper.map(material, MaterialDto.class))
                    .toList();
        } catch (Exception e) {
            logger.error("Error al obtener materiales", e);
            throw new MaterialsException("Error: No se encuentran materiales", e);
        }
    }

    @Override
    public List<MaterialDto> getMaterialsByType(String tipo) {
        try {
            logger.info("Obteniendo materiales por tipo: {}", tipo);

            List<Material> materials = materialRepository.findByTipo(tipo);

            logger.info("Materiales por tipo '{}' encontrados: {}", tipo, materials.size());

            return materials.stream()
                    .map(material -> modelMapper.map(material, MaterialDto.class))
                    .toList();
        } catch (DataAccessException e) {
            logger.error("Error al acceder a datos al obtener materiales por tipo", e);

            throw new MaterialsDataAccessException("Error acceso a datos al obtener materiales por tipo", e);
        } catch (Exception e) {
            logger.error("Error desconocido al obtener materiales por tipo", e);

            throw new MaterialsException("Error desconocido al obtener materiales por tipo", e);
        }
    }

    @Override
    public List<MaterialDto> getMaterialsByDate(LocalDate fechaCompra) {
        try {
            logger.info("Obteniendo materiales por fecha de compra: {}", fechaCompra);

            List<Material> materials = materialRepository.findByFechaCompra(fechaCompra);

            logger.info("Materiales por fecha de compra '{}' encontrados: {}", fechaCompra, materials.size());

            return materials.stream()
                    .map(material -> modelMapper.map(material, MaterialDto.class))
                    .toList();
        } catch (DataAccessException e) {
            logger.error("Error al acceder a datos al obtener materiales por fecha de compra", e);

            throw new MaterialsDataAccessException("Error acceso a datos al obtener materiales por fecha de compra", e);
        } catch (Exception e) {
            logger.error("Error desconocido al obtener materiales por fecha de compra", e);

            throw new MaterialsException("Error desconocido al obtener materiales por fecha de compra", e);
        }
    }


    @Override
    public List<MaterialDto> getMaterialsBySerial(String serial) {
        try {
            logger.info("Obteniendo materiales por número de serie: {}", serial);

            List<Material> materials = materialRepository.findBySerial(serial);

            logger.info("Materiales por número de serie '{}' encontrados: {}", serial, materials.size());

            return materials.stream()
                    .map(material -> modelMapper.map(material, MaterialDto.class))
                    .toList();
        } catch (DataAccessException e) {
            logger.error("Error al acceder a datos al obtener materiales por número de serie", e);

            throw new MaterialsDataAccessException("Error acceso a datos al obtener materiales por número de serie", e);
        } catch (Exception e) {
            logger.error("Error desconocido al obtener materiales por número de serie", e);

            throw new MaterialsException("Error desconocido al obtener materiales por número de serie", e);
        }
    }



    @Override
    public MaterialDto createMaterial(MaterialDto materialDTO) {
        try {
            if (materialDTO == null) {
                throw new IllegalArgumentException("El DTO de material no puede ser nulo");
            }

            validatePurchaseDateVsSaleDate(materialDTO.getFechaCompra(), materialDTO.getFechaVenta());

            Material material = modelMapper.map(materialDTO, Material.class);
            Material savedMaterial = materialRepository.save(material);
            return modelMapper.map(savedMaterial, MaterialDto.class);
        } catch (DataAccessException e) {
            throw new MaterialsDataAccessException("Error acceso a datos al crear un nuevo material", e);
        } catch (Exception e) {
            throw new MaterialsException("La fecha de compra no puede ser posterior a la fecha de venta!", e);
        }
    }


    @Override
    public MaterialDto updateMaterial(Long id, MaterialDto materialDTO) {
        validatePurchaseDateVsSaleDate(materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el material con el ID: " + id))
                .getFechaCompra(), materialDTO.getFechaVenta());

        Material material = modelMapper.map(materialDTO, Material.class);
        material.setId(id);
        Material updatedMaterial = materialRepository.save(material);
        return modelMapper.map(updatedMaterial, MaterialDto.class);
    }



    /**
     * REGLA DE NEGOCIO
     * Se deben validar las fechas
     * (nunca una fecha de compra debe ser superior a una fecha de venta)
     * @param fechaCompra fecha de compra
     * @param fechaVenta fecha de venta
     */
    private void validatePurchaseDateVsSaleDate(LocalDate fechaCompra, LocalDate fechaVenta) {
        if (fechaCompra != null && fechaVenta != null && fechaCompra.isAfter(fechaVenta)) {
            throw new InvalidDateException("La fecha de compra no puede ser posterior a la fecha de venta!");
        }
    }


}
