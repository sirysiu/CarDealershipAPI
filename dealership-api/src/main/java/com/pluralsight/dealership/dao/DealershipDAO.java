package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.Dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface DealershipDAO {

    List<Dealership> findAllDealerships();
//    private DataSource dataSource;
//
//    public DealershipDAO(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public boolean removeVehicle(int vin) {
//        String query = "DELETE FROM vehicles WHERE vin = ?";
//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setInt(1, vin);
//            int rowsAffected = ps.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException("Error removing vehicle with VIN: " + vin, e);
//        }
//    }
}

