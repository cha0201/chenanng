package com.system.yunjie.cms.ms.account.client;

import com.system.yunjie.cms.ms.account.entity.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "cms-ms-statistics")
public interface StatisticsServiceClient {

    @PutMapping(value = "/{accountName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void updateStatistics(@PathVariable("accountName") String accountName, Account account);

}
