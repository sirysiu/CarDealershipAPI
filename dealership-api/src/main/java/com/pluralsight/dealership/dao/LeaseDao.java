package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.LeaseContract;

import java.util.List;

public interface LeaseDao {
    List<LeaseContract> findAllLeaseContracts();

}
