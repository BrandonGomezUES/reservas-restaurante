package com.reservas.restaurante.service.impl;

import com.reservas.restaurante.exception.ResourceNotFoundException;
import com.reservas.restaurante.model.dto.ClienteRequestDTO;
import com.reservas.restaurante.model.dto.ClienteResponseDTO;
import com.reservas.restaurante.model.entity.Cliente;
import com.reservas.restaurante.model.mapper.ClienteMapper;
import com.reservas.restaurante.repository.ClienteRepository;
import com.reservas.restaurante.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> obtenerTodos() {
        return clienteMapper.toDTOList(clienteRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        return clienteMapper.toDTO(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO crear(ClienteRequestDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Ya existe un cliente registrado con el email: " + dto.getEmail());
        }

        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(guardado);
    }

    @Override
    @Transactional
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        if (!cliente.getEmail().equalsIgnoreCase(dto.getEmail()) && clienteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email " + dto.getEmail() + " ya está en uso por otro cliente.");
        }

        clienteMapper.updateEntityFromDTO(dto, cliente);
        Cliente actualizado = clienteRepository.save(cliente);
        return clienteMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        if (cliente.getReservas() != null && !cliente.getReservas().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el cliente porque tiene reservas asociadas.");
        }

        clienteRepository.delete(cliente);
    }
}