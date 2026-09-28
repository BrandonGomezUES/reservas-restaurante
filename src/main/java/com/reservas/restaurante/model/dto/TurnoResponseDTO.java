package com.reservas.restaurante.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponseDTO {
    private Long id;
    private String nombre;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}