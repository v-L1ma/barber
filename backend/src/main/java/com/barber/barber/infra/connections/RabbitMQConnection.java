package com.barber.barber.infra.connections;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {

    private static final String NOME_EXCHANGE = "amq.direct";
    private final AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relactionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona(){
        Queue filaWhatsapp = this.fila("FILA_WHATSAPP");
        Queue filaEmail = this.fila("FILA_EMAIL");

        DirectExchange troca = this.trocaDireta();

        Binding ligacao = this.relactionamento(filaWhatsapp, troca);
        Binding ligacaoEmail = this.relactionamento(filaEmail, troca);

        this.amqpAdmin.declareQueue(filaWhatsapp);
        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareBinding(ligacao);

        this.amqpAdmin.declareQueue(filaEmail);
        this.amqpAdmin.declareBinding(ligacaoEmail);

    }
}
