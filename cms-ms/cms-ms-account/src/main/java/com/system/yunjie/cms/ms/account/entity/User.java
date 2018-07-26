package com.system.yunjie.cms.ms.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class User {

    private Long id;

    @NotNull
    @Length(min = 3, max = 20)
    private String username;

    @NotNull
    @Length(min = 6, max = 40)
    private String password;

}
