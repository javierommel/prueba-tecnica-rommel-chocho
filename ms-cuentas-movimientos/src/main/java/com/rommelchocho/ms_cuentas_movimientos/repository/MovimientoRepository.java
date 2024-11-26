package com.rommelchocho.ms_cuentas_movimientos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;
import com.rommelchocho.ms_cuentas_movimientos.entity.Movimiento;

public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {

    List<Movimiento> findByNumeroCuentaAndFechaBetween(Cuenta numeroCuenta, LocalDate fechaInicio, LocalDate fechaFin);

    Optional<Movimiento> findTopByNumeroCuentaOrderByFechaDesc(Cuenta numeroCuenta);

}
