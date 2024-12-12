package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.SalesContract;

import java.util.List;

public interface SalesDao {
    List<SalesContract> findAllSalesContracts();
}

