package com.reservas.restaurante.service;

import com.reservas.restaurante.model.dto.ClienteRequestDTO;
import com.reservas.restaurante.model.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteResponseDTO> obtenerTodos();
    ClienteResponseDTO obtenerPorId(Long id);
    ClienteResponseDTO crear(ClienteRequestDTO dto);
    ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto);
    void eliminar(Long id);
}