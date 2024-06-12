package com.sagar.Configuration;

import com.sagar.Model.Transaction;
import com.sagar.Model.Vendor;
import com.sagar.Repository.VendorRepository;
import com.sagar.Services.ExpensifyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTask {

    @Autowired
    private ExpensifyService expensifyService;

    @Scheduled(fixedRate = 3600000) // Every hour
    public void fetchVendorsAndTransactions() {
        expensifyService.fetchAndStoreVendors();
    }
}

