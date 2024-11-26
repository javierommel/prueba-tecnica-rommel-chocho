package com.rommelchocho.ms_clientes_personas.service;

import java.util.List;
import java.util.Optional;

import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;
import com.rommelchocho.ms_clientes_personas.entity.Cliente;

public interface ClienteService {
    
    ClienteDto createCliente(Cliente cliente);

    List<ClienteDto> getAllClientes();

    Optional<ClienteDto> getClienteById(Long id);

    Optional<ClienteDto> updateCliente(Long id, Cliente cliente);

    void deleteCliente(Long id);

    Boolean existeCliente(String clienteId);
}
