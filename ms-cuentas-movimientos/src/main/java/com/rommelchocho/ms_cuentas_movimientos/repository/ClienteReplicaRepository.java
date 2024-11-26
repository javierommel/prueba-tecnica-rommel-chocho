package com.rommelchocho.ms_cuentas_movimientos.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rommelchocho.ms_cuentas_movimientos.entity.ClienteReplica;

public interface ClienteReplicaRepository extends CrudRepository<ClienteReplica, String>{
    
    Optional<ClienteReplica> findByClienteId(String clienteId);
}
