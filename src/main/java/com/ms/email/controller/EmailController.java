package com.ms.email.controller;

import com.ms.email.dto.EmailDto;
import com.ms.email.model.Email;
import com.ms.email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/post/send-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDto dto) {
        var model = new Email();
        BeanUtils.copyProperties(dto, model);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.sendEmail(model));
    }

    @GetMapping("/get/emails")
    public ResponseEntity<Page<Email>> findAll(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "2") int items) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(page, items));
    }
}
