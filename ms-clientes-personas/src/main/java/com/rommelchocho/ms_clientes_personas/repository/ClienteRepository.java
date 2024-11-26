package com.rommelchocho.ms_clientes_personas.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rommelchocho.ms_clientes_personas.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Optional<Cliente> findByClienteId(String clienteId);
}
