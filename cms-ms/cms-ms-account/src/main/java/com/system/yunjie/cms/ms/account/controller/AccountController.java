package com.system.yunjie.cms.ms.account.controller;

import com.system.yunjie.cms.ms.account.entity.Account;
import com.system.yunjie.cms.ms.account.entity.User;
import com.system.yunjie.cms.ms.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
    @GetMapping("/{name}")
    public Account getAccountByName(@PathVariable String name) {
        return accountService.findByName(name);
    }

    @GetMapping("/current")
    public Account getCurrentAccount(Principal principal) {
        return accountService.findByName(principal.getName());
    }

    @PutMapping("/current")
    public void saveCurrentAccount(Principal principal, @Valid @RequestBody Account account) {
        accountService.saveChanges(principal.getName(), account);
    }

    @PostMapping("/")
    public Account createNewAccount(@Valid @RequestBody User user) {
        return accountService.create(user);
    }
}
