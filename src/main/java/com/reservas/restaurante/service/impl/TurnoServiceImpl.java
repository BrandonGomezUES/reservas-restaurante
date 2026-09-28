package com.reservas.restaurante.service.impl;

import com.reservas.restaurante.exception.ResourceNotFoundException;
import com.reservas.restaurante.model.dto.TurnoRequestDTO;
import com.reservas.restaurante.model.dto.TurnoResponseDTO;
import com.reservas.restaurante.model.entity.Turno;
import com.reservas.restaurante.model.mapper.TurnoMapper;
import com.reservas.restaurante.repository.TurnoRepository;
import com.reservas.restaurante.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;
    private final TurnoMapper turnoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TurnoResponseDTO> obtenerTodos() {
        return turnoMapper.toDTOList(turnoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public TurnoResponseDTO obtenerPorId(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID: " + id));
        return turnoMapper.toDTO(turno);
    }

    @Override
    @Transactional
    public TurnoResponseDTO crear(TurnoRequestDTO dto) {
        validarHorarioTurno(dto);

        Turno turno = turnoMapper.toEntity(dto);
        Turno guardado = turnoRepository.save(turno);
        return turnoMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public TurnoResponseDTO actualizar(Long id, TurnoRequestDTO dto) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID: " + id));

        validarHorarioTurno(dto);

        turnoMapper.updateEntityFromDTO(dto, turno);
        Turno actualizado = turnoRepository.save(turno);
        return turnoMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID: " + id));

        if (turno.getReservas() != null && !turno.getReservas().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el turno porque tiene reservas registradas.");
        }

        turnoRepository.delete(turno);
    }

    private void validarHorarioTurno(TurnoRequestDTO dto) {
        if (dto.getHoraInicio().isAfter(dto.getHoraFin()) || dto.getHoraInicio().equals(dto.getHoraFin())) {
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de fin.");
        }
    }
}