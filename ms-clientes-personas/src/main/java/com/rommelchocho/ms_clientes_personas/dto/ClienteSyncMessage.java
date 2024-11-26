package com.rommelchocho.ms_clientes_personas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteSyncMessage {
    private String clienteId;
    private String nombre;
}
