package com.rommelchocho.ms_clientes_personas.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.rommelchocho.ms_clientes_personas.dto.ClienteSyncMessage;

@Component
public class ClienteSyncProducer {

    private final RabbitTemplate rabbitTemplate;

    public ClienteSyncProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarActualizacionCliente(String clienteId, String nombre) {
        ClienteSyncMessage mensaje = new ClienteSyncMessage(clienteId, nombre);
        rabbitTemplate.convertAndSend("clientes.exchange", "clientes.sync", mensaje);
        System.out.println("Mensaje enviado para crear/actualizar cliente: " + clienteId);
    }

    public void enviarEliminacionCliente(String clienteId) {
        ClienteSyncMessage mensaje = new ClienteSyncMessage(clienteId, null);
        rabbitTemplate.convertAndSend("clientes.exchange", "clientes.delete", mensaje);
        System.out.println("Mensaje enviado para eliminar cliente: " + clienteId);
    }
}
