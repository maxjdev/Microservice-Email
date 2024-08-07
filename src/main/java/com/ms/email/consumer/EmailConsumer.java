package com.ms.email.consumer;

import com.ms.email.dto.EmailDto;
import com.ms.email.model.Email;
import com.ms.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    private final EmailService service;

    public EmailConsumer(EmailService service) {
        this.service = service;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto dto) {
        Email email = new Email();
        BeanUtils.copyProperties(dto, email);
        service.sendEmail(email);
        System.out.println("Email Status: " + email.getStatusEmail().toString());
    }
}
