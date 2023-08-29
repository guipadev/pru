package com.sysman.prueba.services.impl;

import com.sysman.prueba.exceptions.CiudadException;
import com.sysman.prueba.models.Ciudad;
import com.sysman.prueba.models.dto.CiudadDto;
import com.sysman.prueba.repositorys.CiudadRepository;
import com.sysman.prueba.services.CiudadService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CiudadServiceImpl implements CiudadService {

    private final CiudadRepository ciudadRepository;

    private final ModelMapper modelMapper;

    @Override
    public CiudadDto createCiudad(CiudadDto ciudadDto) throws CiudadException {
        try {
            Ciudad ciudad = modelMapper.map(ciudadDto, Ciudad.class);
            Ciudad savedCiudad = ciudadRepository.save(ciudad);
            return modelMapper.map(savedCiudad, CiudadDto.class);
        } catch (Exception e) {
            throw new CiudadException("Error al crear la ciudad", e);
        }
    }
}
