package com.reservas.restaurante.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResponseDTO {
    private Long id;
    private LocalDate fechaReserva;
    private Integer cantidadPersonas;
    private String estado;
    
    // Información aplanada para no exponer entidades completas
    private Long clienteId;
    private String nombreClienteCompleto;
    
    private Long mesaId;
    private Integer numeroMesa;
    private String ubicacionMesa;
    
    private Long turnoId;
    private String nombreTurno;
    private LocalTime horaInicioTurno;
    private LocalTime horaFinTurno;
}