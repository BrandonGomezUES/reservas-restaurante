package com.reservas.restaurante.controller;

import com.reservas.restaurante.model.dto.MesaRequestDTO;
import com.reservas.restaurante.model.dto.MesaResponseDTO;
import com.reservas.restaurante.service.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mesas")
@RequiredArgsConstructor
@Tag(name = "Mesas", description = "Gestión del catálogo de mesas del restaurante")
public class MesaController {

    private final MesaService mesaService;

    @GetMapping
    @Operation(summary = "Listar todas las mesas o filtrar por ubicación")
    public ResponseEntity<List<MesaResponseDTO>> obtenerTodas(
            @RequestParam(required = false) String ubicacion) {
        if (ubicacion != null && !ubicacion.isBlank()) {
            return ResponseEntity.ok(mesaService.obtenerPorUbicacion(ubicacion));
        }
        return ResponseEntity.ok(mesaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una mesa por su ID")
    public ResponseEntity<MesaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mesaService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva mesa")
    public ResponseEntity<MesaResponseDTO> crear(@Valid @RequestBody MesaRequestDTO dto) {
        MesaResponseDTO creada = mesaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos de una mesa")
    public ResponseEntity<MesaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody MesaRequestDTO dto) {
        return ResponseEntity.ok(mesaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una mesa por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mesaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}