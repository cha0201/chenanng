package com.system.yunjie.cms.ms.statistics.entity;

import com.system.yunjie.cms.ms.statistics.value.Currency;
import com.system.yunjie.cms.ms.statistics.value.TimePeriod;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class Item {

    @NotNull
    @Length(min = 1, max = 20)
    private String title;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    @NotNull
    private TimePeriod period;
}
