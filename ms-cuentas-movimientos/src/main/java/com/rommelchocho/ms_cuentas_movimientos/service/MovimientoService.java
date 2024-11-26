package com.rommelchocho.ms_cuentas_movimientos.service;

import java.util.List;
import java.util.Optional;

import com.rommelchocho.ms_cuentas_movimientos.entity.Movimiento;

public interface MovimientoService {

    Movimiento createMovimiento(String numeroCuenta, String tipoMovimiento, Double valor);

    List<Movimiento> getAllMovimientos();

    Optional<Movimiento> getMovimientoById(Long id);

    Optional<Movimiento> updateMovimiento(Long id, Movimiento cuenta);

    void deleteMovimiento(Long id);
}
