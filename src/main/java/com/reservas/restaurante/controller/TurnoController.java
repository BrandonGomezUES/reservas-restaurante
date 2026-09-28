package com.reservas.restaurante.controller;

import com.reservas.restaurante.model.dto.TurnoRequestDTO;
import com.reservas.restaurante.model.dto.TurnoResponseDTO;
import com.reservas.restaurante.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
@RequiredArgsConstructor
@Tag(name = "Turnos", description = "Gestión de los horarios y turnos de atención")
public class TurnoController {

    private final TurnoService turnoService;

    @GetMapping
    @Operation(summary = "Listar todos los turnos disponibles")
    public ResponseEntity<List<TurnoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(turnoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un turno por su ID")
    public ResponseEntity<TurnoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo turno horario")
    public ResponseEntity<TurnoResponseDTO> crear(@Valid @RequestBody TurnoRequestDTO dto) {
        TurnoResponseDTO creado = turnoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un turno existente")
    public ResponseEntity<TurnoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TurnoRequestDTO dto) {
        return ResponseEntity.ok(turnoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un turno por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}