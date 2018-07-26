package com.system.yunjie.cms.ms.statistics.client;

import com.system.yunjie.cms.ms.statistics.client.fallback.ExchangeRatesClientFallback;
import com.system.yunjie.cms.ms.statistics.value.Currency;
import com.system.yunjie.cms.ms.statistics.entity.ExchangeRatesContainer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collection;

@FeignClient(url = "${rates.url}", name = "rates-client", fallback = ExchangeRatesClientFallback.class)
public interface ExchangeRatesClient {

    default ExchangeRatesContainer getRates(Currency base) {
        return getRates(base, Arrays.asList(Currency.values()));
    }

    @GetMapping("/latest")
    ExchangeRatesContainer getRates(@RequestParam("base") Currency base,
                                    @RequestParam("symbols") Collection<Currency> currencies);

}
