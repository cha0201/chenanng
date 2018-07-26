package com.system.yunjie.cms.ms.notification.controller;

import com.system.yunjie.cms.ms.notification.service.RecipientService;
import com.system.yunjie.cms.ms.notification.entity.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/recipients")
public class RecipientController {

    private RecipientService recipientService;

    @Autowired
    public RecipientController(final RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @GetMapping("/current")
    public Object getCurrentNotificationsSettings(Principal principal) {
        return recipientService.findByAccountName(principal.getName());
    }

    @PutMapping("/current")
    public Object saveCurrentNotificationsSettings(Principal principal, @Valid @RequestBody Recipient recipient) {
        return recipientService.save(principal.getName(), recipient);
    }
}
