package com.system.yunjie.cms.ms.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Account {

    private Long id;

    private String name;

    private Date lastSeen;

    @Valid
    private List<Item> incomes;

    @Valid
    private List<Item> expenses;

    @Valid
    @NotNull
    private Saving saving;

    @Length(max = 20_000)
    private String note;
}
