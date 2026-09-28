package com.reservas.restaurante.service;

import com.reservas.restaurante.model.dto.TurnoRequestDTO;
import com.reservas.restaurante.model.dto.TurnoResponseDTO;

import java.util.List;

public interface TurnoService {
    List<TurnoResponseDTO> obtenerTodos();
    TurnoResponseDTO obtenerPorId(Long id);
    TurnoResponseDTO crear(TurnoRequestDTO dto);
    TurnoResponseDTO actualizar(Long id, TurnoRequestDTO dto);
    void eliminar(Long id);
}