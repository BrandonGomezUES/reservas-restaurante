package com.reservas.restaurante.model.mapper;

import com.reservas.restaurante.model.dto.MesaRequestDTO;
import com.reservas.restaurante.model.dto.MesaResponseDTO;
import com.reservas.restaurante.model.entity.Mesa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    MesaResponseDTO toDTO(Mesa mesa);

    List<MesaResponseDTO> toDTOList(List<Mesa> mesas);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Mesa toEntity(MesaRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    void updateEntityFromDTO(MesaRequestDTO dto, @MappingTarget Mesa mesa);
}