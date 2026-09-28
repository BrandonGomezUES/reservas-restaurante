package com.reservas.restaurante.model.mapper;

import com.reservas.restaurante.model.dto.ReservaRequestDTO;
import com.reservas.restaurante.model.dto.ReservaResponseDTO;
import com.reservas.restaurante.model.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "nombreClienteCompleto", expression = "java(reserva.getCliente().getNombre() + ' ' + reserva.getCliente().getApellido())")
    @Mapping(target = "mesaId", source = "mesa.id")
    @Mapping(target = "numeroMesa", source = "mesa.numeroMesa")
    @Mapping(target = "ubicacionMesa", source = "mesa.ubicacion")
    @Mapping(target = "turnoId", source = "turno.id")
    @Mapping(target = "nombreTurno", source = "turno.nombre")
    @Mapping(target = "horaInicioTurno", source = "turno.horaInicio")
    @Mapping(target = "horaFinTurno", source = "turno.horaFin")
    ReservaResponseDTO toDTO(Reserva reserva);

    List<ReservaResponseDTO> toDTOList(List<Reserva> reservas);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "mesa", ignore = true)
    @Mapping(target = "turno", ignore = true)
    Reserva toEntity(ReservaRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "mesa", ignore = true)
    @Mapping(target = "turno", ignore = true)
    void updateEntityFromDTO(ReservaRequestDTO dto, @MappingTarget Reserva reserva);
}