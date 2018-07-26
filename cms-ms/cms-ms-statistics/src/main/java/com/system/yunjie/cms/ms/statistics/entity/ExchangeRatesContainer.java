package com.system.yunjie.cms.ms.statistics.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.system.yunjie.cms.ms.statistics.value.Currency;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"date"})
@Getter
@Setter
@ToString
public class ExchangeRatesContainer {

    private LocalDate date = LocalDate.now();
    private Currency base;
    private Map<String, BigDecimal> rates;

}
