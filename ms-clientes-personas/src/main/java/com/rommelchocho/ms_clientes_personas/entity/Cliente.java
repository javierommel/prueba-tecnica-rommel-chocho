package com.rommelchocho.ms_clientes_personas.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class Cliente extends Persona{
    
    @Column(unique = true, nullable = false)
    private String clienteId;

    private String contrasena;
    
    private Boolean estado;

    @PrePersist
    public void prePersist() {
        if (this.clienteId == null) {
            this.clienteId = UUID.randomUUID().toString().substring(0,6);
        }
    }
    @PostPersist
    public void postPersist() {
        this.clienteId = "CLI-" + getId();
        
    }
}
