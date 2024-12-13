package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.dao.SalesContractDaoMysql;
import com.pluralsight.dealership.models.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales-contracts")
public class SalesContractsController {
    private final SalesContractDaoMysql salesContractDao;

    @Autowired
    public SalesContractsController(SalesContractDaoMysql salesContractDao) {
        this.salesContractDao = salesContractDao;
    }

    @GetMapping("/")
    public List<SalesContract> getAllSalesContracts() {
        return salesContractDao.findAllSalesContracts();
    }

}
