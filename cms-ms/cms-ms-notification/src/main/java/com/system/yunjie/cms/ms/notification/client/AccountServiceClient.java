package com.system.yunjie.cms.ms.notification.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @GetMapping(value = "/accounts/{accountName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String getAccount(@PathVariable("accountName") String accountName);

}
