package com.ms.email.controller;

import com.ms.email.dto.EmailDto;
import com.ms.email.model.Email;
import com.ms.email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/sendind-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDto dto) {
        Email email = new Email();
        BeanUtils.copyProperties(dto, email);
        service.sendEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }
}
