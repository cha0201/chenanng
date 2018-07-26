package com.system.yunjie.cms.ms.notification.service.impl;

import com.system.yunjie.cms.ms.notification.service.EmailService;
import com.system.yunjie.cms.ms.notification.service.NotificationService;
import com.system.yunjie.cms.ms.notification.service.RecipientService;
import com.system.yunjie.cms.ms.notification.client.AccountServiceClient;
import com.system.yunjie.cms.ms.notification.value.NotificationType;
import com.system.yunjie.cms.ms.notification.entity.Recipient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private AccountServiceClient client;
    private RecipientService recipientService;
    private EmailService emailService;

    @Autowired
    public NotificationServiceImpl(final AccountServiceClient client, final RecipientService recipientService,
                                   final EmailService emailService) {
        this.client = client;
        this.recipientService = recipientService;
        this.emailService = emailService;
    }

    @Override
    @Scheduled(cron = "${backup.cron}")
    public void sendBackupNotifications() {

        final NotificationType type = NotificationType.BACKUP;

        List<Recipient> recipients = recipientService.findReadyToNotify(type);
        log.info("found {} recipients for backup notification", recipients.size());

        recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
            try {
                String attachment = client.getAccount(recipient.getAccountName());
                emailService.send(type, recipient, attachment);
                recipientService.markNotified(type, recipient);
            } catch (Throwable t) {
                log.error("an error during backup notification for {}", recipient, t);
            }
        }));
    }

    @Override
    @Scheduled(cron = "${remind.cron}")
    public void sendRemindNotifications() {

        final NotificationType type = NotificationType.REMIND;

        List<Recipient> recipients = recipientService.findReadyToNotify(type);
        log.info("found {} recipients for remind notification", recipients.size());

        recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
            try {
                emailService.send(type, recipient, null);
                recipientService.markNotified(type, recipient);
            } catch (Throwable t) {
                log.error("an error during remind notification for {}", recipient, t);
            }
        }));
    }
}
