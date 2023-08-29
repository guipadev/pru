package com.sysman.prueba.repositorys;

import com.sysman.prueba.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByTipo(String tipo);
    List<Material> findByFechaCompra(LocalDate fechaCompra);
    List<Material> findBySerial(String serial);
}