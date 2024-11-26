package com.rommelchocho.ms_cuentas_movimientos.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
    
    @Bean
    public DirectExchange clientesExchange() {
        return new DirectExchange("clientes.exchange");
    }

    @Bean
    public Queue clientesSyncQueue() {
        return new Queue("clientes.sync.queue");
    }

    @Bean
    public Queue clientesDeleteQueue() {
        return new Queue("clientes.delete.queue");
    }

    @Bean
    public Binding bindingClientesSync(Queue clientesSyncQueue, DirectExchange clientesExchange) {
        return BindingBuilder.bind(clientesSyncQueue).to(clientesExchange).with("clientes.sync");
    }

    @Bean
    public Binding bindingClientesDelete(Queue clientesDeleteQueue, DirectExchange clientesExchange) {
        return BindingBuilder.bind(clientesDeleteQueue).to(clientesExchange).with("clientes.delete");
    }
}
