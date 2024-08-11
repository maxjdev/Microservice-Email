package com.ms.email.service;

import com.ms.email.enums.StatusEmail;
import com.ms.email.model.Email;
import com.ms.email.repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EmailService {
    private final EmailRepository repository;
    private final JavaMailSender mailSender;

    public EmailService(EmailRepository repository, JavaMailSender mailSender) {
        this.repository = repository;
        this.mailSender = mailSender;
    }

    @Transactional
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        var message = createSimpleMailMessage(email);
        try {
            mailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
            log.info("Email sent successfully to {}", email.getEmailTo());
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
            log.error("Failed to send email to {}", email.getEmailTo(), e);
        }
        return repository.save(email);
    }

    public Page<Email> findAll(int page, int items) {
        return repository.findAll(PageRequest.of(page, items));
    }

    private SimpleMailMessage createSimpleMailMessage(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        return message;
    }
}
