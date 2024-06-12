package com.sagar.Repository;

import com.sagar.Model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByVendorId(String vendorId);
}
