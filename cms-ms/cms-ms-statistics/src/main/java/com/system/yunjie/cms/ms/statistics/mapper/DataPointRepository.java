package com.system.yunjie.cms.ms.statistics.mapper;


import com.system.yunjie.cms.ms.statistics.entity.timeseries.DataPoint;

import java.util.List;

public interface DataPointRepository {

    List<DataPoint> findByIdAccount(String account);

    DataPoint save(DataPoint dataPoint);
}
