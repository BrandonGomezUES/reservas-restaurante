package com.reservas.restaurante.service;

import com.reservas.restaurante.model.dto.MesaRequestDTO;
import com.reservas.restaurante.model.dto.MesaResponseDTO;

import java.util.List;

public interface MesaService {
    List<MesaResponseDTO> obtenerTodas();
    MesaResponseDTO obtenerPorId(Long id);
    List<MesaResponseDTO> obtenerPorUbicacion(String ubicacion);
    MesaResponseDTO crear(MesaRequestDTO dto);
    MesaResponseDTO actualizar(Long id, MesaRequestDTO dto);
    void eliminar(Long id);
}