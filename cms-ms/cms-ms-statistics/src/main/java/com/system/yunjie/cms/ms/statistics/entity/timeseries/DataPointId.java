package com.system.yunjie.cms.ms.statistics.entity.timeseries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Getter
@AllArgsConstructor
public class DataPointId implements Serializable {

    private static final long serialVersionUID = 1L;
    private String account;
    private Date date;

}
