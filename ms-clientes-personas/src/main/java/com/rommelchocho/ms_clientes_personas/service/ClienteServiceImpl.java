package com.rommelchocho.ms_clientes_personas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;
import com.rommelchocho.ms_clientes_personas.dto.mapper.ClienteMapperDto;
import com.rommelchocho.ms_clientes_personas.entity.Cliente;
import com.rommelchocho.ms_clientes_personas.messaging.ClienteSyncProducer;
import com.rommelchocho.ms_clientes_personas.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteSyncProducer clienteSyncProducer;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteSyncProducer clienteSyncProducer) {
        this.clienteRepository = clienteRepository;
        this.clienteSyncProducer = clienteSyncProducer;
    }

    @Override
    public ClienteDto createCliente(Cliente cliente) {
        Cliente clienteOptional = clienteRepository.save(cliente);
        clienteSyncProducer.enviarActualizacionCliente(clienteOptional.getClienteId(), clienteOptional.getNombre());
        return ClienteMapperDto.build(clienteOptional);
    }

    @Override
    public void deleteCliente(Long id) {
        Optional<Cliente> clienteAsync = clienteRepository.findById(id);
        if (clienteAsync.isPresent()) {
            clienteSyncProducer.enviarEliminacionCliente(clienteAsync.orElseThrow().getClienteId());
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public List<ClienteDto> getAllClientes() {
        List<Cliente> cliente = (List<Cliente>) clienteRepository.findAll();
        return cliente.stream().map(c -> ClienteMapperDto.build(c)).collect(Collectors.toList());
    }

    @Override
    public Optional<ClienteDto> getClienteById(Long id) {
        Optional<Cliente> o = clienteRepository.findById(id);
        if (o.isPresent()) {
            return Optional.of(
                    ClienteMapperDto.build(o.orElseThrow()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClienteDto> updateCliente(Long id, Cliente clienteNuevo) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        Cliente clienteOptional = null;
        if (cliente.isPresent()) {
            Cliente clienteDb = cliente.orElseThrow();
            clienteDb.setContrasena(clienteNuevo.getContrasena());
            clienteDb.setEstado(clienteNuevo.getEstado());
            clienteOptional = clienteRepository.save(cliente.orElseThrow());
            clienteSyncProducer.enviarActualizacionCliente(clienteOptional.getClienteId(), clienteOptional.getNombre());
        }
        return Optional.ofNullable(ClienteMapperDto.build(clienteOptional));
    }

    @Override
    public Boolean existeCliente(String clienteId) {
        Optional<Cliente> cliente = clienteRepository.findByClienteId(clienteId);
        if (cliente.isPresent())
            return true;
        return false;
    }

}
