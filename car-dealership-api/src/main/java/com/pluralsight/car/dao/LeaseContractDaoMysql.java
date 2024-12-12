package com.pluralsight.car.dao;

import com.pluralsight.car.models.LeaseContract;

import java.util.List;

public class LeaseContractDaoMysql implements LeaseDao{
    @Override
    public List<LeaseContract> findAllLeaseContracts() {
        return List.of();
    }
}
