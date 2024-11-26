package com.rommelchocho.ms_cuentas_movimientos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Boolean estado;
    
    @Column(nullable = false)
    private String clienteId;
}
