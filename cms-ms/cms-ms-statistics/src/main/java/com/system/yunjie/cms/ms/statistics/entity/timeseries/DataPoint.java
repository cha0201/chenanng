package com.system.yunjie.cms.ms.statistics.entity.timeseries;


import com.system.yunjie.cms.ms.statistics.value.StatisticMetric;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Represents daily time series data point containing
 * current account state
 */
@Getter
@Setter
public class DataPoint {

    private DataPointId id;
    private Set<ItemMetric> incomes;
    private Set<ItemMetric> expenses;
    private Map<StatisticMetric, BigDecimal> statistics;

}
