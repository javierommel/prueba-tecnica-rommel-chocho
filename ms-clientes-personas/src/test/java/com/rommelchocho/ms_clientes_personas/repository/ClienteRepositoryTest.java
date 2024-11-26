package com.rommelchocho.ms_clientes_personas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.rommelchocho.ms_clientes_personas.entity.Cliente;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {
@Autowired
    private ClienteRepository clienteRepository;
    @Test
    public void testCrearYBuscarCliente() {
        
        Cliente cliente = new Cliente();
        cliente.setClienteId("CLI-1");
        cliente.setNombre("Juan Perez");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("0996875412");
        cliente.setIdentificacion("0104223430");
        cliente.setContrasena("1234");
        cliente.setEstado(true);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        assertNotNull(clienteGuardado.getId());
        assertEquals("CLI-1", clienteGuardado.getClienteId());

        Optional<Cliente> clienteBuscado = clienteRepository.findById(clienteGuardado.getId());
        assertTrue(clienteBuscado.isPresent());
        assertEquals("Juan Perez", clienteBuscado.get().getNombre());

        Optional<Cliente> clientePorClienteId = clienteRepository.findByClienteId("CLI-1");
        assertNotNull(clientePorClienteId.isPresent());
        assertEquals("Juan Perez", clientePorClienteId.orElseThrow().getNombre());
    }
}
