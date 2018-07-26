package com.system.yunjie.cms.ms.statistics.entity;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Account {

    @Valid
    @NotNull
    private List<Item> incomes;

    @Valid
    @NotNull
    private List<Item> expenses;

    @Valid
    @NotNull
    private Saving saving;

}
