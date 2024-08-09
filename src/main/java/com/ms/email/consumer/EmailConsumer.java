package com.ms.email.consumer;

import com.ms.email.dto.EmailDto;
import com.ms.email.model.Email;
import com.ms.email.service.EmailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailConsumer {
    private final EmailService service;

    public EmailConsumer(EmailService service) {
        this.service = service;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload @Valid EmailDto dto) {
        var model = new Email();
        BeanUtils.copyProperties(dto, model);
        service.sendEmail(model);
        log.info("Email Status: " + model.getStatusEmail().toString());
    }
}
