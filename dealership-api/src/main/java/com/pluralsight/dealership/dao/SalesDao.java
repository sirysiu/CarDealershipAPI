package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.SalesContract;

import java.util.List;

public interface SalesDao {
    List<SalesContract> findAllSalesContracts();

    List<SalesContract>  findSalesContractByVin(int vin);

    // Insert a new sales contract
    void insertSalesContract(SalesContract salesContract);
}

