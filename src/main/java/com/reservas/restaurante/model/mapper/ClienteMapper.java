package com.reservas.restaurante.model.mapper;

import com.reservas.restaurante.model.dto.ClienteRequestDTO;
import com.reservas.restaurante.model.dto.ClienteResponseDTO;
import com.reservas.restaurante.model.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO toDTO(Cliente cliente);

    List<ClienteResponseDTO> toDTOList(List<Cliente> clientes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Cliente toEntity(ClienteRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    void updateEntityFromDTO(ClienteRequestDTO dto, @MappingTarget Cliente cliente);
}