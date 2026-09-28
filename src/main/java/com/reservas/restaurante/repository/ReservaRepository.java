package com.reservas.restaurante.repository;


import com.reservas.restaurante.model.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    boolean existsByMesaIdAndFechaReservaAndTurnoIdAndEstadoNot(
            Long mesaId, 
            LocalDate fechaReserva, 
            Long turnoId, 
            String estado
    );

    boolean existsByMesaIdAndFechaReservaAndTurnoId(
            Long mesaId, 
            LocalDate fechaReserva, 
            Long turnoId
    );

    @Query("SELECT COUNT(r) > 0 FROM Reserva r " +
           "WHERE r.mesa.id = :mesaId " +
           "AND r.fechaReserva = :fecha " +
           "AND r.turno.id = :turnoId " +
           "AND r.id <> :reservaId " +
           "AND r.estado <> 'CANCELADA'")
    boolean existeReservaParaOtraInstancia(
            @Param("mesaId") Long mesaId,
            @Param("fecha") LocalDate fecha,
            @Param("turnoId") Long turnoId,
            @Param("reservaId") Long reservaId
    );

    List<Reserva> findByClienteId(Long clienteId);

    List<Reserva> findByFechaReserva(LocalDate fechaReserva);

    List<Reserva> findByMesaIdAndFechaReserva(Long mesaId, LocalDate fechaReserva);
}