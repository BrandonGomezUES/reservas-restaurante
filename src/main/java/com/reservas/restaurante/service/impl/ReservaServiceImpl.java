package com.reservas.restaurante.service.impl;

import com.reservas.restaurante.exception.ResourceNotFoundException;
import com.reservas.restaurante.model.dto.ReservaRequestDTO;
import com.reservas.restaurante.model.dto.ReservaResponseDTO;
import com.reservas.restaurante.model.entity.Cliente;
import com.reservas.restaurante.model.entity.Mesa;
import com.reservas.restaurante.model.entity.Reserva;
import com.reservas.restaurante.model.entity.Turno;
import com.reservas.restaurante.model.mapper.ReservaMapper;
import com.reservas.restaurante.repository.ClienteRepository;
import com.reservas.restaurante.repository.MesaRepository;
import com.reservas.restaurante.repository.ReservaRepository;
import com.reservas.restaurante.repository.TurnoRepository;
import com.reservas.restaurante.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final MesaRepository mesaRepository;
    private final TurnoRepository turnoRepository;
    private final ReservaMapper reservaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> obtenerTodas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservaMapper.toDTOList(reservas);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaResponseDTO obtenerPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));
        return reservaMapper.toDTO(reserva);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> obtenerPorFecha(LocalDate fecha) {
        List<Reserva> reservas = reservaRepository.findByFechaReserva(fecha);
        return reservaMapper.toDTOList(reservas);
    }

    @Override
    @Transactional
    public ReservaResponseDTO crear(ReservaRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + dto.getClienteId()));

        Mesa mesa = mesaRepository.findById(dto.getMesaId())
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada con ID: " + dto.getMesaId()));

        Turno turno = turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID: " + dto.getTurnoId()));

        if (dto.getCantidadPersonas() > mesa.getCapacidad()) {
            throw new IllegalArgumentException(
                    "La cantidad de personas (" + dto.getCantidadPersonas() + 
                    ") excede la capacidad máxima de la mesa (" + mesa.getCapacidad() + ")"
            );
        }

        boolean yaReservada = reservaRepository.existsByMesaIdAndFechaReservaAndTurnoIdAndEstadoNot(
                dto.getMesaId(), dto.getFechaReserva(), dto.getTurnoId(), "CANCELADA"
        );

        if (yaReservada) {
            throw new IllegalStateException("La mesa #" + mesa.getNumeroMesa() + " ya está reservada para esa fecha y turno.");
        }

        Reserva reserva = reservaMapper.toEntity(dto);
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reserva.setTurno(turno);
        reserva.setEstado("CONFIRMADA");

        Reserva guardada = reservaRepository.save(reserva);
        return reservaMapper.toDTO(guardada);
    }

    @Override
    @Transactional
    public ReservaResponseDTO actualizar(Long id, ReservaRequestDTO dto) {

        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + dto.getClienteId()));

        Mesa mesa = mesaRepository.findById(dto.getMesaId())
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada con ID: " + dto.getMesaId()));

        Turno turno = turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID: " + dto.getTurnoId()));

        if (dto.getCantidadPersonas() > mesa.getCapacidad()) {
            throw new IllegalArgumentException("La cantidad de personas excede la capacidad de la mesa (" + mesa.getCapacidad() + ")");
        }

        boolean existeConflicto = reservaRepository.existeReservaParaOtraInstancia(
                dto.getMesaId(), dto.getFechaReserva(), dto.getTurnoId(), id
        );

        if (existeConflicto) {
            throw new IllegalStateException("La mesa #" + mesa.getNumeroMesa() + " no está disponible en la fecha y turno indicados.");
        }

        reservaMapper.updateEntityFromDTO(dto, reservaExistente);
        reservaExistente.setCliente(cliente);
        reservaExistente.setMesa(mesa);
        reservaExistente.setTurno(turno);

        Reserva actualizada = reservaRepository.save(reservaExistente);
        return reservaMapper.toDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Reserva no encontrada con ID: " + id);
        }
        reservaRepository.deleteById(id);
    }
}