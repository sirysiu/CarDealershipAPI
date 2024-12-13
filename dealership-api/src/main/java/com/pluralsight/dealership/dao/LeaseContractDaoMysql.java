package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.LeaseContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class LeaseContractDaoMysql implements LeaseDao{
    private final BasicDataSource dataSource;

    public LeaseContractDaoMysql(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<LeaseContract> findAllLeaseContracts() {
        return List.of();
    }
}
