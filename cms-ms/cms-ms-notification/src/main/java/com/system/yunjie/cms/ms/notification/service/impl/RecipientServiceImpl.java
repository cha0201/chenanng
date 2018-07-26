package com.system.yunjie.cms.ms.notification.service.impl;

import com.system.yunjie.cms.ms.notification.mapper.RecipientMapper;
import com.system.yunjie.cms.ms.notification.service.RecipientService;
import com.system.yunjie.cms.ms.notification.value.NotificationType;
import com.system.yunjie.cms.ms.notification.entity.Recipient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RecipientServiceImpl implements RecipientService {

    private RecipientMapper recipientMapper;

    @Autowired
    public RecipientServiceImpl(final RecipientMapper recipientMapper) {
        this.recipientMapper = recipientMapper;
    }

    @Override
    public Recipient findByAccountName(String accountName) {
        Assert.hasLength(accountName, "empty accountName");
        return recipientMapper.findByAccountName(accountName);
    }

    @Override
    public Recipient save(String accountName, Recipient recipient) {

        recipient.setAccountName(accountName);
        recipient.getScheduledNotifications().values()
                .forEach(settings -> {
                    if (settings.getLastNotified() == null) {
                        settings.setLastNotified(new Date());
                    }
                });

        recipientMapper.save(recipient);

        log.info("recipient {} settings has been updated", recipient);

        return recipient;
    }

    @Override
    public List<Recipient> findReadyToNotify(NotificationType type) {
        switch (type) {
            case BACKUP:
                return recipientMapper.findReadyForBackup();
            case REMIND:
                return recipientMapper.findReadyForRemind();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void markNotified(NotificationType type, Recipient recipient) {
        recipient.getScheduledNotifications().get(type).setLastNotified(new Date());
        recipientMapper.save(recipient);
    }
}
