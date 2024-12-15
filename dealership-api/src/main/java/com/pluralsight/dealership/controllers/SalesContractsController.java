package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.dao.SalesContractDaoMysql;
import com.pluralsight.dealership.dao.SalesDao;
import com.pluralsight.dealership.models.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sales-contracts")
public class SalesContractsController {
    private final SalesDao salesContractDao;

    @Autowired
    public SalesContractsController(SalesContractDaoMysql salesContractDao) {
        this.salesContractDao = salesContractDao;
    }

    @GetMapping("/")
    public List<SalesContract> getAllSalesContracts() {
        return salesContractDao.findAllSalesContracts();
    }

    // Get a sales contract by VIN (or any other identifier, e.g., contract ID)
    @GetMapping("/by-vin/{vin}")
    public List<SalesContract>  getSalesContractByVin(@PathVariable int vin) {
        List<SalesContract>  salesContract = salesContractDao.findSalesContractByVin(vin);

        if (salesContract == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales contract not found for VIN: " + vin);
        }

        return salesContract;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSalesContract(@RequestBody SalesContract salesContract) {
        salesContractDao.insertSalesContract(salesContract);
    }
}
