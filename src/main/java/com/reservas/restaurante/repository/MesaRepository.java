package com.reservas.restaurante.repository;

import com.reservas.restaurante.model.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    Optional<Mesa> findByNumeroMesa(Integer numeroMesa);

    boolean existsByNumeroMesa(Integer numeroMesa);

    List<Mesa> findByUbicacion(String ubicacion);
}