package com.sysman.prueba.controllers;

import com.sysman.prueba.models.dto.MaterialDto;
import com.sysman.prueba.services.impl.MaterialServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MaterialController.class)
class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialServiceImpl materialService;



    @Test
    public void getAllMaterials() throws Exception {

        List<MaterialDto> mockMaterials = new ArrayList<>();
        mockMaterials.add(new MaterialDto());

        when(materialService.getAllMaterials()).thenReturn(mockMaterials);


        mockMvc.perform(get("/api/v1/materiales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Materiales encontrados."))
                .andExpect(jsonPath("$.data").isArray());
    }


    @Test
    public void getMaterialsByType() throws Exception {
        String mockTipo = "Vidrio";
        List<MaterialDto> mockMaterials = new ArrayList<>();
        mockMaterials.add(new MaterialDto());

        when(materialService.getMaterialsByType(mockTipo)).thenReturn(mockMaterials);

        mockMvc.perform(get("/api/v1/materiales/tipo/{tipo}", mockTipo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Materiales encontrados."))
                .andExpect(jsonPath("$.data").isArray());
    }


}