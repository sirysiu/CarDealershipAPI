package com.pluralsight.dealership.controllers;


import com.pluralsight.dealership.dao.LeaseContractDaoMysql;
import com.pluralsight.dealership.models.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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

    // GET method to retrieve a lease contract by VIN
    @GetMapping("/by-vin/{vin}")
    public List<LeaseContract> getLeaseContractByVin(@PathVariable int vin) {
        List<LeaseContract>  leaseContract = leaseContractDao.findLeaseContractByVin(vin);
        if (leaseContract == null) {
            throw new RuntimeException("Lease contract not found with VIN: " + vin);
        }
        return leaseContract;
    }

    // POST method to add a new lease contract
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP 201 (Created)
    public void addLeaseContract(@RequestBody LeaseContract leaseContract) {
        boolean isAdded = leaseContractDao.addLeaseContract(leaseContract);
        if (!isAdded) {
            throw new RuntimeException("Failed to add lease contract");
        }
    }
}
