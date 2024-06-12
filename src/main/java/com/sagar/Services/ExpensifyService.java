package com.sagar.Services;

import com.sagar.Model.Transaction;
import com.sagar.Model.Vendor;
import com.sagar.Repository.TransactionRepository;
import com.sagar.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ExpensifyService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String API_URL = "https://integrations.expensify.com/#create";

    private final String API_URL_GET = "https://integrations.expensify.com/#read-get";


    public void fetchAndStoreVendors() {
        String url = API_URL + "/your-vendor-endpoint";
        Vendor[] vendors = restTemplate.getForObject(url, Vendor[].class);

        if (vendors != null) {
            for (Vendor vendor : vendors) {
                vendorRepository.save(vendor);
                fetchAndStoreTransactions(vendor.getId());
            }
        }
    }

    public void fetchAndStoreTransactions(String vendorId) {
        String url = API_URL_GET + "/your-transaction-endpoint?vendorId=" + vendorId;
        Transaction[] transactions = restTemplate.getForObject(url, Transaction[].class);

        if (transactions != null) {
            for (Transaction transaction : transactions) {
                transaction.setVendorId(vendorId);
                transactionRepository.save(transaction);
            }
        }
    }
}

