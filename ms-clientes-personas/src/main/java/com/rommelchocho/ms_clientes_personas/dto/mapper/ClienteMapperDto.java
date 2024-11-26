package com.rommelchocho.ms_clientes_personas.dto.mapper;

import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;
import com.rommelchocho.ms_clientes_personas.entity.Cliente;
import com.rommelchocho.ms_clientes_personas.exception.BadRequestException;

public class ClienteMapperDto {

    private ClienteMapperDto() {
    }

    public static ClienteDto build(Cliente cliente) {
        if (cliente == null) {
            throw new BadRequestException("El cliente no puede ser nulo");
        }
        return new ClienteDto(cliente);
    }
}
