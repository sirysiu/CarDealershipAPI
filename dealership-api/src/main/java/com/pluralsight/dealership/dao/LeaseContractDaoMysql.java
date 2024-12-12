package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.LeaseContract;

import java.util.List;

public class LeaseContractDaoMysql implements LeaseDao{
    @Override
    public List<LeaseContract> findAllLeaseContracts() {
        return List.of();
    }
}
