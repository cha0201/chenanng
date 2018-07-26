package com.system.yunjie.cms.ms.account.entity;

import com.system.yunjie.cms.ms.account.value.Currency;
import com.system.yunjie.cms.ms.account.value.TimePeriod;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class Item {

    private Long id;

    @NotNull
    @Length(min = 1, max = 20)
    private String title;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;

    @NotNull
    private TimePeriod period;

    @NotNull
    private String icon;

}
