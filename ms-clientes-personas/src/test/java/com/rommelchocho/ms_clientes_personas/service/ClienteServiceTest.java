package com.rommelchocho.ms_clientes_personas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import com.rommelchocho.ms_clientes_personas.dto.ClienteDto;
import com.rommelchocho.ms_clientes_personas.entity.Cliente;
import com.rommelchocho.ms_clientes_personas.messaging.ClienteSyncProducer;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;

    @Mock 
    private ClienteSyncProducer clienteSyncProducer;

    @TestConfiguration
    static class MockProducerConfig {
        @Bean
        public ClienteSyncProducer clienteSyncProducer() {
            return Mockito.mock(ClienteSyncProducer.class); 
        }
    }

    @Test
    public void testCrearCliente() {

        Mockito.doNothing().when(clienteSyncProducer).enviarActualizacionCliente(Mockito.anyString(),
                Mockito.anyString());

        Cliente cliente = new Cliente();
        cliente.setClienteId("CLI-1");
        cliente.setNombre("Maria Lopez");
        cliente.setGenero("Femenino");
        cliente.setEdad(28);
        cliente.setDireccion("Avenida Siempre Viva");
        cliente.setTelefono("0984785621");
        cliente.setContrasena("12345");
        cliente.setEstado(true);
        cliente.setIdentificacion("0203232234");

        ClienteDto clienteGuardado = clienteService.createCliente(cliente);

        assertNotNull(clienteGuardado.getId());
        assertEquals("CLI-1", clienteGuardado.getClienteId());
        assertEquals("Maria Lopez", clienteGuardado.getNombre());
    }
}
