package com.reservas.restaurante.service.impl;

import com.reservas.restaurante.exception.ResourceNotFoundException;
import com.reservas.restaurante.model.dto.MesaRequestDTO;
import com.reservas.restaurante.model.dto.MesaResponseDTO;
import com.reservas.restaurante.model.entity.Mesa;
import com.reservas.restaurante.model.mapper.MesaMapper;
import com.reservas.restaurante.repository.MesaRepository;
import com.reservas.restaurante.service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaServiceImpl implements MesaService {

    private final MesaRepository mesaRepository;
    private final MesaMapper mesaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MesaResponseDTO> obtenerTodas() {
        return mesaMapper.toDTOList(mesaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public MesaResponseDTO obtenerPorId(Long id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada con ID: " + id));
        return mesaMapper.toDTO(mesa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MesaResponseDTO> obtenerPorUbicacion(String ubicacion) {
        return mesaMapper.toDTOList(mesaRepository.findByUbicacion(ubicacion));
    }

    @Override
    @Transactional
    public MesaResponseDTO crear(MesaRequestDTO dto) {
        if (mesaRepository.existsByNumeroMesa(dto.getNumeroMesa())) {
            throw new IllegalArgumentException("Ya existe una mesa registrada con el número: " + dto.getNumeroMesa());
        }

        Mesa mesa = mesaMapper.toEntity(dto);
        Mesa guardada = mesaRepository.save(mesa);
        return mesaMapper.toDTO(guardada);
    }

    @Override
    @Transactional
    public MesaResponseDTO actualizar(Long id, MesaRequestDTO dto) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada con ID: " + id));

        if (!mesa.getNumeroMesa().equals(dto.getNumeroMesa()) && mesaRepository.existsByNumeroMesa(dto.getNumeroMesa())) {
            throw new IllegalArgumentException("El número de mesa " + dto.getNumeroMesa() + " ya pertenece a otra mesa.");
        }

        mesaMapper.updateEntityFromDTO(dto, mesa);
        Mesa actualizada = mesaRepository.save(mesa);
        return mesaMapper.toDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada con ID: " + id));

        if (mesa.getReservas() != null && !mesa.getReservas().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la mesa #" + mesa.getNumeroMesa() + " porque tiene reservas registradas.");
        }

        mesaRepository.delete(mesa);
    }
}