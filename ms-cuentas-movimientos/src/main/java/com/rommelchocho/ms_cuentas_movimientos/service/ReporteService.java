package com.rommelchocho.ms_cuentas_movimientos.service;

import java.time.LocalDate;
import java.util.List;

import com.rommelchocho.ms_cuentas_movimientos.dto.ReporteMovimientoDto;

public interface ReporteService {

    public List<ReporteMovimientoDto> generarReporte(String clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}
