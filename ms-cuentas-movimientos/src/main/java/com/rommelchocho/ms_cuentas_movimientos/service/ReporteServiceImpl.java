package com.rommelchocho.ms_cuentas_movimientos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rommelchocho.ms_cuentas_movimientos.dto.ReporteMovimientoDto;
import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;
import com.rommelchocho.ms_cuentas_movimientos.entity.Movimiento;
import com.rommelchocho.ms_cuentas_movimientos.repository.ClienteReplicaRepository;
import com.rommelchocho.ms_cuentas_movimientos.repository.CuentaRepository;
import com.rommelchocho.ms_cuentas_movimientos.repository.MovimientoRepository;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final ClienteReplicaRepository clienteReplicaRepository;

    public ReporteServiceImpl(CuentaRepository cuentaRepository, MovimientoRepository movimientoRepository, ClienteReplicaRepository clienteReplicaRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
        this.clienteReplicaRepository = clienteReplicaRepository;
    }

    public List<ReporteMovimientoDto> generarReporte(String clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        // Obtengo las cuentas del cliente
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        // Genero el reporte de movimientos
        return cuentas.stream()
                .flatMap(cuenta -> {
                    
                    List<Movimiento> movimientos = movimientoRepository.findByNumeroCuentaAndFechaBetween(
                            cuenta, fechaInicio, fechaFin);

                    return movimientos.stream().map(mov -> new ReporteMovimientoDto(
                            mov.getFecha().toString(),
                            clienteReplicaRepository.findByClienteId(clienteId).orElseThrow().getNombre(), 
                            cuenta.getNumeroCuenta(),
                            cuenta.getTipo(),
                            cuenta.getSaldoInicial(),
                            cuenta.getEstado(),
                            mov.getValor(),
                            mov.getSaldoDisponible() 
                    ));
                })
                .collect(Collectors.toList());
    }

}
