package com.system.yunjie.cms.ms.statistics.entity.timeseries;


import com.system.yunjie.cms.ms.statistics.value.TimePeriod;
import com.system.yunjie.cms.ms.statistics.entity.Item;
import com.system.yunjie.cms.ms.statistics.value.Currency;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Represents normalized {@link Item} object
 * with {@link Currency#getBase()} ()} currency and {@link TimePeriod#getBase()} ()} time period
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ItemMetric {

    private String title;
    private BigDecimal amount;

}
