package com.pluralsight.dealership.controllers;


import com.pluralsight.dealership.dao.LeaseContractDaoMysql;
import com.pluralsight.dealership.models.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lease-contracts")
public class LeaseContractsController {

    private final LeaseContractDaoMysql leaseContractDao;

    // Inject LeaseContractDaoMysql through the constructor
    @Autowired
    public LeaseContractsController(LeaseContractDaoMysql leaseContractDao) {
        this.leaseContractDao = leaseContractDao;
    }
    // Endpoint to retrieve all lease contracts
    @GetMapping("/")
    public List<LeaseContract> getAllLeaseContracts() {
        // Retrieve the list of all lease contracts from the DAO
        return leaseContractDao.findAllLeaseContracts();
    }
}
