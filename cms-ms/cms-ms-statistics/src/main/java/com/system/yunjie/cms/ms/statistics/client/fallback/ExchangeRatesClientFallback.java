package com.system.yunjie.cms.ms.statistics.client.fallback;

import com.yunjie.system.cms.ms.client.ExchangeRatesClient;
import com.yunjie.system.cms.ms.entity.Currency;
import com.yunjie.system.cms.ms.entity.ExchangeRatesContainer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class ExchangeRatesClientFallback implements ExchangeRatesClient {

    @Override
    public ExchangeRatesContainer getRates(Currency base, Collection<Currency> currencies) {
        ExchangeRatesContainer container = new ExchangeRatesContainer();
        container.setBase(Currency.getBase());
        container.setRates(Collections.emptyMap());
        return container;
    }
}
