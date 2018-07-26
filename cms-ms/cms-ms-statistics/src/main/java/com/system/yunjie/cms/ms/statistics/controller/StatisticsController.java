package com.system.yunjie.cms.ms.statistics.controller;

import com.system.yunjie.cms.ms.statistics.entity.Account;
import com.system.yunjie.cms.ms.statistics.entity.timeseries.DataPoint;
import com.system.yunjie.cms.ms.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/current")
    public List<DataPoint> getCurrentAccountStatistics(Principal principal) {
        return statisticsService.findByAccountName(principal.getName());
    }

    @PreAuthorize("#oauth2.hasScope('server') or #accountName.equals('demo')")
    @GetMapping("/{accountName}")
    public List<DataPoint> getStatisticsByAccountName(@PathVariable String accountName) {
        return statisticsService.findByAccountName(accountName);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @PutMapping("/{accountName}")
    public void saveAccountStatistics(@PathVariable String accountName, @Valid @RequestBody Account account) {
        statisticsService.save(accountName, account);
    }
}
