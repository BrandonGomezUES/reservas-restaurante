package com.reservas.restaurante.model.mapper;

import com.reservas.restaurante.model.dto.TurnoRequestDTO;
import com.reservas.restaurante.model.dto.TurnoResponseDTO;
import com.reservas.restaurante.model.entity.Turno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TurnoMapper {

    TurnoResponseDTO toDTO(Turno turno);

    List<TurnoResponseDTO> toDTOList(List<Turno> turnos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Turno toEntity(TurnoRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    void updateEntityFromDTO(TurnoRequestDTO dto, @MappingTarget Turno turno);
}