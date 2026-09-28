package com.reservas.restaurante.service;

import com.reservas.restaurante.model.dto.ReservaRequestDTO;
import com.reservas.restaurante.model.dto.ReservaResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {
    List<ReservaResponseDTO> obtenerTodas();
    ReservaResponseDTO obtenerPorId(Long id);
    List<ReservaResponseDTO> obtenerPorFecha(LocalDate fecha);
    ReservaResponseDTO crear(ReservaRequestDTO dto);
    ReservaResponseDTO actualizar(Long id, ReservaRequestDTO dto);
    void eliminar(Long id);
}