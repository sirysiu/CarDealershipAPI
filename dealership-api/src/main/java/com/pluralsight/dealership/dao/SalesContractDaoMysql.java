package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.SalesContract;
import com.pluralsight.dealership.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDaoMysql implements SalesDao{

    private final BasicDataSource dataSource;

    public SalesContractDaoMysql(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<SalesContract> findAllSalesContracts() {
        List<SalesContract> sc = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement findAllSalesContracts = connection.prepareStatement("""
                   SELECT * 
                   FROM sales_contracts;
                   """);

            ResultSet rs = findAllSalesContracts.executeQuery();

            while (rs.next()) {
                String date = rs.getNString("contract_date");
                String customerName = rs.getNString("customer_name");
                String customerEmail = rs.getNString("customer_email");
                boolean isFinancing = rs.getBoolean("isFinancing");


                SalesContract salesContract = new SalesContract(date, customerName,customerEmail,isFinancing);
                sc.add(salesContract);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sc;
    }

    @Override
    public List<SalesContract> findSalesContractById() {
        return List.of();
    }
}
