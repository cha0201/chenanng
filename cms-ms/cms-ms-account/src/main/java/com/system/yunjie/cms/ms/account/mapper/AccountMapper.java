package com.system.yunjie.cms.ms.account.mapper;

import com.system.yunjie.cms.ms.account.entity.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    Account selectByAccountName(@Param("accountName") String accountName);

    void insert(Account account);
}
