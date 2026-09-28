package com.reservas.restaurante.controller;

import com.reservas.restaurante.model.dto.ReservaRequestDTO;
import com.reservas.restaurante.model.dto.ReservaResponseDTO;
import com.reservas.restaurante.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
@Tag(name = "Reservas", description = "Gestión de reservas de mesas del restaurante")
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    @Operation(summary = "Obtener todas las reservas o filtrar por fecha")
    public ResponseEntity<List<ReservaResponseDTO>> obtenerTodas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        if (fecha != null) {
            return ResponseEntity.ok(reservaService.obtenerPorFecha(fecha));
        }
        return ResponseEntity.ok(reservaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una reserva por su ID")
    public ResponseEntity<ReservaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva reserva (valida disponibilidad de mesa y turno)")
    public ResponseEntity<ReservaResponseDTO> crear(@Valid @RequestBody ReservaRequestDTO dto) {
        ReservaResponseDTO creada = reservaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una reserva existente por ID")
    public ResponseEntity<ReservaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ReservaRequestDTO dto) {
        return ResponseEntity.ok(reservaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una reserva por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}