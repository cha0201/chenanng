package com.system.yunjie.cms.ms.notification.service;



import com.system.yunjie.cms.ms.notification.value.NotificationType;
import com.system.yunjie.cms.ms.notification.entity.Recipient;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException, IOException;

}
