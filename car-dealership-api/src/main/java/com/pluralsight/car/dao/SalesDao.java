package com.pluralsight.car.dao;

import com.pluralsight.car.models.SalesContract;

import java.util.List;

public interface SalesDao {
    List<SalesContract> findAllSalesContracts();
}

