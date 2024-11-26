package com.rommelchocho.ms_cuentas_movimientos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ClienteReplica {
    
    @Id
    private String clienteId;

    private String nombre;
}
