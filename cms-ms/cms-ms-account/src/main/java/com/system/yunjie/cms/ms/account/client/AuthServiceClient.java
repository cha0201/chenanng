package com.system.yunjie.cms.ms.account.client;

import com.system.yunjie.cms.ms.account.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cms-ms-auth")
public interface AuthServiceClient {

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void createUser(User user);

}
