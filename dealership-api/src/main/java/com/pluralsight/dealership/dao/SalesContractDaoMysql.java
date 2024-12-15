package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SalesContractDaoMysql implements SalesDao{

    private final BasicDataSource dataSource;

    public SalesContractDaoMysql(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Find all sales contracts
    @Override
    public List<SalesContract> findAllSalesContracts() {
        List<SalesContract> salesContracts = new ArrayList<>();

        String sql = "SELECT * FROM sales_contracts"; // Query to get all sales contracts

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            // Iterate through the result set and create a SalesContract object for each row
            while (rs.next()) {
                int vin = rs.getInt("vin");
                String contractDate = rs.getString("contract_date");
                String customerName = rs.getString("customer_name");
                String customerEmail = rs.getString("customer_email");
                double totalPrice = rs.getDouble("total_price");
                double monthlyPayment = rs.getDouble("monthly_payment");
                double salesTaxes = rs.getDouble("sales_taxes");
                double recordingFees = rs.getDouble("recording_fees");
                boolean isFinancing = rs.getBoolean("isFinancing");

                // Create SalesContract object and add it to the list
                SalesContract salesContract = new SalesContract(vin, contractDate, customerName, customerEmail, totalPrice, monthlyPayment, salesTaxes, recordingFees, isFinancing);
                salesContracts.add(salesContract);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving sales contracts from the database", e);
        }

        return salesContracts;
    }



    @Override
    public List<SalesContract> findSalesContractByVin(int vin) {
        List<SalesContract> salesContracts = new ArrayList<>();

        String sql = "SELECT * FROM sales_contracts WHERE vin = ?"; // Query to get sales contract by VIN

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vin);  // Set the VIN parameter in the SQL query
            ResultSet rs = statement.executeQuery();

            // If a result is found, create a SalesContract object and add it to the list
            if (rs.next()) {
                String contractDate = rs.getString("contract_date");
                String customerName = rs.getString("customer_name");
                String customerEmail = rs.getString("customer_email");
                double totalPrice = rs.getDouble("total_price");
                double monthlyPayment = rs.getDouble("monthly_payment");
                double salesTaxes = rs.getDouble("sales_taxes");
                double recordingFees = rs.getDouble("recording_fees");
                boolean isFinancing = rs.getBoolean("isFinancing");

                SalesContract salesContract = new SalesContract(vin, contractDate, customerName, customerEmail, totalPrice, monthlyPayment, salesTaxes, recordingFees, isFinancing);
                salesContracts.add(salesContract); // Add to the list
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving sales contract by VIN from the database", e);
        }

        return salesContracts;
    }


    // Insert a new sales contract
    @Override
    public void insertSalesContract(SalesContract salesContract) {
        String sql = """
            INSERT INTO sales_contracts (
                vin,
                contract_date,
                customer_name,
                customer_email,
                total_price,
                monthly_payment,
                sales_taxes,
                recording_fees,
                isFinancing
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set the parameters for the PreparedStatement
            statement.setInt(1, salesContract.getVin());  // VIN
            statement.setString(2, salesContract.getContractDate());  // Contract Date
            statement.setString(3, salesContract.getCustomerName());  // Customer Name
            statement.setString(4, salesContract.getCustomerEmail());  // Customer Email
            statement.setDouble(5, salesContract.getTotalPrice());  // Total Price
            statement.setDouble(6, salesContract.getMonthlyPayment());  // Monthly Payment
            statement.setDouble(7, salesContract.getSalesTaxes());  // Sales Taxes
            statement.setDouble(8, salesContract.getRecordingFees());  // Recording Fees
            statement.setBoolean(9, salesContract.isFinancing());  // Is Financing

            // Execute the update (inserting the data)
            int rowsAffected = statement.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected == 0) {
                throw new SQLException("Inserting sales contract failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting sales contract into the database", e);
        }
    }


}
