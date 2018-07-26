package com.system.yunjie.cms.ms.statistics.entity;

import com.system.yunjie.cms.ms.statistics.value.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class Saving {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    @NotNull
    private BigDecimal interest;

    @NotNull
    private Boolean deposit;

    @NotNull
    private Boolean capitalization;

}
