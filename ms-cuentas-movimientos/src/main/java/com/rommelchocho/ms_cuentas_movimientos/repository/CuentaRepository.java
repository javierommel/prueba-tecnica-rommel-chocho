package com.rommelchocho.ms_cuentas_movimientos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;

public interface CuentaRepository extends CrudRepository<Cuenta, Long>{
    
    List<Cuenta> findByClienteId(String clienteId);
    
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
