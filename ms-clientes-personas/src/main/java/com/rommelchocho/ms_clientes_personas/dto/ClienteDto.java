package com.rommelchocho.ms_clientes_personas.dto;

import com.rommelchocho.ms_clientes_personas.entity.Cliente;

import lombok.Data;

@Data
public class ClienteDto {

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.clienteId = cliente.getClienteId();
        this.nombre = cliente.getNombre();
        this.identificacion = cliente.getIdentificacion();
        this.genero = cliente.getGenero();
        this.telefono = cliente.getTelefono();
        this.direccion = cliente.getDireccion();
        this.edad = cliente.getEdad();
        this.estado = cliente.getEstado();
    }

    private Long id;
    private String clienteId;
    private Boolean estado;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
