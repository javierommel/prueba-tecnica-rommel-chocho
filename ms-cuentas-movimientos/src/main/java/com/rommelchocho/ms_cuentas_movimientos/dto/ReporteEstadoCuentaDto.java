package com.rommelchocho.ms_cuentas_movimientos.dto;

import java.util.List;

import com.rommelchocho.ms_cuentas_movimientos.entity.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteEstadoCuentaDto {

    private String clienteId;
    private List<Cuenta> cuentas;
    private List<ReporteMovimientoDto> movimientos;
}
