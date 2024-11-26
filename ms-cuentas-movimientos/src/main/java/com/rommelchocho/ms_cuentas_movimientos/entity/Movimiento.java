package com.rommelchocho.ms_cuentas_movimientos.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldoDisponible;
    @ManyToOne
    @JoinColumn(name = "numero_cuenta", referencedColumnName = "numeroCuenta", nullable = false)
    private Cuenta numeroCuenta;
}
