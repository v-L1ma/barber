package com.barber.barber.application.services.rabbitMQService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final MessageConverter jsonConverter = new Jackson2JsonMessageConverter();

    public void enviaMensagem(String nomeFila, Object messagem){
        try {
            this.rabbitTemplate.setMessageConverter(jsonConverter);
            String messagemJson = this.objectMapper.writeValueAsString(messagem);
            this.rabbitTemplate.convertAndSend(nomeFila, messagem);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
