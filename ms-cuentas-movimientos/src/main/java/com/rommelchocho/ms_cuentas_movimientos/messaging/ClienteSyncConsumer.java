package com.rommelchocho.ms_cuentas_movimientos.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rommelchocho.ms_cuentas_movimientos.dto.ClienteSyncMessage;
import com.rommelchocho.ms_cuentas_movimientos.entity.ClienteReplica;
import com.rommelchocho.ms_cuentas_movimientos.repository.ClienteReplicaRepository;

@Component
public class ClienteSyncConsumer {

    private final ClienteReplicaRepository clienteReplicaRepository;

    public ClienteSyncConsumer(ClienteReplicaRepository clienteReplicaRepository) {
        this.clienteReplicaRepository = clienteReplicaRepository;
    }

    @RabbitListener(queues = "clientes.sync.queue")
    public void procesarActualizacionCliente(ClienteSyncMessage mensaje) {
        String clienteId = mensaje.getClienteId();
        String nombre = mensaje.getNombre();

        ClienteReplica cliente = clienteReplicaRepository.findByClienteId(clienteId)
                .orElse(new ClienteReplica());

        cliente.setClienteId(clienteId);
        cliente.setNombre(nombre);
        clienteReplicaRepository.save(cliente);

        System.out.println("Cliente sincronizado: " + clienteId);
    }

    @RabbitListener(queues = "clientes.delete.queue")
    public void procesarEliminacionCliente(ClienteSyncMessage mensaje) {
        String clienteId = mensaje.getClienteId();

        clienteReplicaRepository.deleteById(clienteId);
        System.out.println("Cliente eliminado de réplica: " + clienteId);
    }
}