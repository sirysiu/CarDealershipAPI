package com.pluralsight.car.dao;

import com.pluralsight.car.models.LeaseContract;

import java.util.List;

public interface LeaseDao {
    List<LeaseContract> findAllLeaseContracts();

}
