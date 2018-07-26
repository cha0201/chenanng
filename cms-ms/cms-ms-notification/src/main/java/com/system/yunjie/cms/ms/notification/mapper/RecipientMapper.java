package com.system.yunjie.cms.ms.notification.mapper;



import com.system.yunjie.cms.ms.notification.entity.Recipient;

import java.util.List;

public interface RecipientMapper {

    Recipient findByAccountName(String name);

    List<Recipient> findReadyForBackup();

    List<Recipient> findReadyForRemind();

    void save(Recipient recipient);
}
