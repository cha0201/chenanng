package com.system.yunjie.cms.ms.account.service.impl;

import com.system.yunjie.cms.ms.account.client.AuthServiceClient;
import com.system.yunjie.cms.ms.account.client.StatisticsServiceClient;
import com.system.yunjie.cms.ms.account.entity.Account;
import com.system.yunjie.cms.ms.account.entity.Saving;
import com.system.yunjie.cms.ms.account.entity.User;
import com.system.yunjie.cms.ms.account.mapper.AccountMapper;
import com.system.yunjie.cms.ms.account.service.AccountService;
import com.system.yunjie.cms.ms.account.value.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private StatisticsServiceClient statisticsClient;
    private AuthServiceClient authClient;
    private AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(final StatisticsServiceClient statisticsClient, final AuthServiceClient authClient,
                              final AccountMapper accountMapper) {
        this.statisticsClient = statisticsClient;
        this.authClient = authClient;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account findByName(String accountName) {
        Assert.hasLength(accountName, "empty accountName");
        return accountMapper.selectByAccountName(accountName);
    }

    @Override
    public Account create(User user) {

        Account existing = accountMapper.selectByAccountName(user.getUsername());
        Assert.isNull(existing, "account already exists: " + user.getUsername());

        authClient.createUser(user);

        Saving saving = new Saving();
        saving.setAmount(new BigDecimal(0));
        saving.setCurrency(Currency.getDefault());
        saving.setInterest(new BigDecimal(0));
        saving.setDeposit(false);
        saving.setCapitalization(false);

        Account account = new Account();
        account.setName(user.getUsername());
        account.setLastSeen(new Date());
        account.setSaving(saving);

        accountMapper.insert(account);

        log.info("new account has been created: " + account.getName());

        return account;
    }

    @Override
    public void saveChanges(String name, Account update) {
        Account account = accountMapper.selectByAccountName(name);
        Assert.notNull(account, "can't find account with name " + name);
        account.setIncomes(update.getIncomes());
        account.setExpenses(update.getExpenses());
        account.setSaving(update.getSaving());
        account.setNote(update.getNote());
        account.setLastSeen(new Date());
        accountMapper.insert(account);
        log.debug("account {} changes has been saved", name);
        statisticsClient.updateStatistics(name, account);
    }
}
