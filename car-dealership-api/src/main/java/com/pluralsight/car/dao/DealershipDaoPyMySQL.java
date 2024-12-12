package com.pluralsight.car.dao;

import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.pluralsight.car.models.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDaoPyMySQL implements DealershipDAO{
    private final BasicDataSource dataSource;

    public DealershipDaoPyMySQL(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Dealership> findAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT dealership_id, name, address, phone FROM dealerships";

        // Open a connection and perform the query
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            // Loop through the result set and create Dealership objects
            while (rs.next()) {
                int dealershipId = rs.getInt("dealership_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                // Create a new Dealership object and add it to the list
                Dealership dealership = new Dealership();
                dealerships.add(dealership);
            }

        } catch (SQLException e) {
            // Handle the exception, could log it or throw a custom exception
            throw new RuntimeException("Error fetching dealerships from the database", e);
        }

        return dealerships; // Return the list of dealerships
    }

}
