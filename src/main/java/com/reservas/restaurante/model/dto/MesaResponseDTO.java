package com.reservas.restaurante.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MesaResponseDTO {
    private Long id;
    private Integer numeroMesa;
    private Integer capacidad;
    private String ubicacion;
}