package com.system.yunjie.cms.ms.notification.service.impl;

import com.system.yunjie.cms.ms.notification.service.EmailService;
import com.system.yunjie.cms.ms.notification.value.NotificationType;
import com.system.yunjie.cms.ms.notification.entity.Recipient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;

@Service
@RefreshScope
@Slf4j
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;
    private Environment env;

    @Autowired
    public EmailServiceImpl(final JavaMailSender mailSender, final Environment env) {
        this.mailSender = mailSender;
        this.env = env;
    }

    @Override
    public void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException {

        final String subject = env.getProperty(type.getSubject());
        final String text = MessageFormat.format(env.getProperty(type.getText()), recipient.getAccountName());

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipient.getEmail());
        helper.setSubject(subject);
        helper.setText(text);

        if (StringUtils.hasLength(attachment)) {
            helper.addAttachment(env.getProperty(type.getAttachment()), new ByteArrayResource(attachment.getBytes()));
        }

        mailSender.send(message);

        log.info("{} email notification has been send to {}", type, recipient.getEmail());
    }
}
