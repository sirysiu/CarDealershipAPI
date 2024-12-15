package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.LeaseContract;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeaseContractDaoMysql implements LeaseDao {

    private final BasicDataSource dataSource;

    // Constructor for the DAO
    public LeaseContractDaoMysql(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<LeaseContract> findAllLeaseContracts() {
        List<LeaseContract> leaseContracts = new ArrayList<>();

        // SQL query to retrieve all lease contracts
        String sql = "SELECT * FROM lease_contract";  // Adjust table name based on your schema

        // Establish a connection and execute the query
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            // Iterate through the result set and map each row to a LeaseContract object
            while (rs.next()) {
             //   int leaseId = rs.getInt("lease_id");  // Assuming 'lease_id' is the primary key
                String contractDate = rs.getString("start_date");
                String customerName = rs.getString("customer_name");
                String customerEmail = rs.getString("customer_email");
              //  double monthlyPayment = rs.getDouble("monthly_payment");
               // int leaseTerm = rs.getInt("lease_term");  // Assuming 'lease_term' is in months
               // boolean isFinancing = rs.getBoolean("isFinancing");

                // Create a LeaseContract object and add it to the list
                LeaseContract leaseContract = new LeaseContract(contractDate,customerName,customerEmail
                );

                leaseContracts.add(leaseContract);
            }
        } catch (SQLException e) {
            // Handle exceptions and print the stack trace for debugging
            e.printStackTrace();
            throw new RuntimeException("Error retrieving lease contracts from the database", e);
        }

        // Return the list of all lease contracts
        return leaseContracts;
    }

    // New Method: Get Lease Contract by VIN
    public List<LeaseContract> findLeaseContractByVin(int vin) {
        List<LeaseContract>  lc = new ArrayList<>();

        String sql = "SELECT * FROM lease_contract WHERE vin = ?";  // Adjust table and column names based on your schema

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vin);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String contractDate = rs.getString("start_date");
                String customerName = rs.getString("customer_name");
                String customerEmail = rs.getString("customer_email");

                // Create a LeaseContract object and map it to the result set

                LeaseContract leaseContract = new LeaseContract(contractDate, customerName, customerEmail);
                lc.add(leaseContract);
            }
        } catch (SQLException e) {
            // Handle SQL errors
            e.printStackTrace();
            throw new RuntimeException("Error retrieving lease contract by VIN from the database", e);
        }

        return lc;
    }

    // New Method: Add a Lease Contract to the database
    public boolean addLeaseContract(LeaseContract leaseContract) {
        String sql = "INSERT INTO lease_contract (vin, start_date, customer_name, customer_email) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set the values for the prepared statement
            statement.setInt(1, leaseContract.getVin()); // Assuming LeaseContract has a VIN field
            statement.setString(2, leaseContract.getContractDate());
            statement.setString(3, leaseContract.getCustomerName());
            statement.setString(4, leaseContract.getCustomerEmail());

            // Execute the insert statement
            int rowsAffected = statement.executeUpdate();

            // Return true if the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle SQL errors
            e.printStackTrace();
            throw new RuntimeException("Error inserting lease contract into the database", e);
        }
    }
}

