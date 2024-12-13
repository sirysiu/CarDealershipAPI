package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class VehicleDAOMysqlImpl implements VehicleDao {
    private final DataSource dataSource;

    public VehicleDAOMysqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> findVehicleByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        String make, model, color, type;
        int year, mileage, vin;
        boolean sold;
        double price;

        try (Connection connection = dataSource.getConnection()) {
            // Prepare the SQL query to find vehicles within the given price range
            String query = """
                    SELECT make, model, year, color, mileage, price, vin, type, sold
                    FROM vehicles
                    WHERE price BETWEEN ? AND ?
                    ORDER BY price;
                    """;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, minPrice);  // Set the minimum price parameter
            statement.setDouble(2, maxPrice);  // Set the maximum price parameter

            // Execute the query
            ResultSet rs = statement.executeQuery();

            // Process the result set and create Vehicle objects
            while (rs.next()) {
                make = rs.getString("make");
                model = rs.getString("model");
                year = rs.getInt("year");
                color = rs.getString("color");
                mileage = rs.getInt("mileage");
                price = rs.getDouble("price");
                vin = rs.getInt("vin");
                type = rs.getString("type");
               // sold = rs.getBoolean("sold");


                // Create a new Vehicle object and add it to the list
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            // Handle the SQLException (e.g., database connection issues, query issues)
            throw new RuntimeException("Error retrieving vehicles by price range", e);
        }

        // Return the list of vehicles within the price range
        return vehicles;
    }


    @Override
    public List<Vehicle> findVehicleByMakeAndModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String color, type;
        int year, mileage, vin;
      //  boolean sold;
        double price;

        try (Connection connection = dataSource.getConnection()) {
            // SQL query to find vehicles by make and model
            PreparedStatement findVehiclesByMakeModel = connection.prepareStatement("""
                    SELECT make, model, year, color, mileage, price, vin, type
                    FROM vehicles
                    WHERE make = ? AND model = ?
                    ORDER BY price;
                    """);

            // Set the parameters for the query
            findVehiclesByMakeModel.setString(1, make);
            findVehiclesByMakeModel.setString(2, model);

            ResultSet rs = findVehiclesByMakeModel.executeQuery();

            // Process the results and add to the vehicles list
            while (rs.next()) {
                make = rs.getString("make");
                model = rs.getString("model");
                year = rs.getInt("year");
                type = rs.getString("type");
                color = rs.getString("color");
                mileage = rs.getInt("mileage");
                price = rs.getDouble("price");
                vin = rs.getInt("vin");
               // sold = rs.getBoolean("sold");
                // Create a new Vehicle object and add it to the list
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }


    @Override
    public List<Vehicle> findVehicleByYear(int year) {
        List<Vehicle> vehicles = new ArrayList<>();
        String make, model, color, type;
        int mileage, vin;
        double price;
       // boolean sold;

        try (Connection connection = dataSource.getConnection()) {
            // SQL query to get vehicles based on the year
            String query = """
                    SELECT make, model, year, color, mileage, price, vin, type
                    FROM vehicles
                    WHERE year = ?
                    ORDER BY price;
                    """;

            // Create a PreparedStatement and set the year parameter
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, year);  // Set the year parameter

            // Execute the query and get the result set
            ResultSet rs = statement.executeQuery();

            // Process the result set and add vehicles to the list
            while (rs.next()) {
                make = rs.getString("make");
                model = rs.getString("model");
                year = rs.getInt("year");
                color = rs.getString("color");
                mileage = rs.getInt("mileage");
                price = rs.getDouble("price");
                vin = rs.getInt("vin");
                type = rs.getString("type");
                //sold = rs.getBoolean("sold");

                // Create a new Vehicle object and add it to the list
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            // Handle any database or query errors
            throw new RuntimeException("Error retrieving vehicles by year", e);
        }

        return vehicles;  // Return the list of vehicles for the given year
    }

    @Override
    public List<Vehicle> findVehicleByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE color = ?";  // SQL query to find vehicles by color

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, color);  // Set the color parameter in the query

            ResultSet rs = preparedStatement.executeQuery();

            // Iterate through the result set and create Vehicle objects
            while (rs.next()) {
                int vin = rs.getInt("vin");
                int year = rs.getInt("year");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int mileage = rs.getInt("mileage");
                double price = rs.getDouble("price");
                String type = rs.getString("type");
                Boolean sold = rs.getBoolean("sold");


                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                vehicles.add(vehicle);  // Add the vehicle to the list
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vehicles by color", e);
        }

        return vehicles;  // Return the list of vehicles found
    }

    @Override
    public List<Vehicle> findVehicleByMileage(int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE mileage <= ?";  // SQL query to filter by mileage

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, maxMileage);  // Set the maximum mileage parameter

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int vin = rs.getInt("vin");
                int year = rs.getInt("year");
                String make = rs.getString("make");
                String model = rs.getString("model");
                String color = rs.getString("color");
                int mileage = rs.getInt("mileage");
                double price = rs.getDouble("price");
                String type = rs.getString("type");
                boolean sold = rs.getBoolean("sold");

                // Create a new Vehicle object and add it to the list
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            // Improved error logging with the actual SQL error message
            System.err.println("Error executing query: " + query);
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("ErrorCode: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace for more context
            throw new RuntimeException("Error retrieving vehicles by mileage", e);
        }

        return vehicles;  // Return the list of vehicles with mileage <= maxMileage
    }


    @Override
    public List<Vehicle> findVehicleByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE type = ?";  // SQL query to filter by type

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, type);  // Set the type parameter in the query

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int vin = rs.getInt("vin");
                int year = rs.getInt("year");
                String make = rs.getString("make");
                String model = rs.getString("model");
                String color = rs.getString("color");
                int mileage = rs.getInt("mileage");
                double price = rs.getDouble("price");
                boolean sold = rs.getBoolean("sold");

                // Create a new Vehicle object and add it to the list
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vehicles by type", e);
        }

        return vehicles;  // Return the list of vehicles found
    }


    @Override
    public List<Vehicle> findAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String make, model, color, type;
        int year, mileage, vin;
        double price;
        boolean sold;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement findAllVehicles = connection.prepareStatement("""
                    SELECT make, model, year, color, mileage, price, vin, type, sold
                    FROM vehicles
                    ORDER BY price;
                    """);

            ResultSet rs = findAllVehicles.executeQuery();

            while (rs.next()) {
                make = rs.getString("make");
                model = rs.getString("model");
                year = rs.getInt("year");
                color = rs.getString("color");
                mileage = rs.getInt("mileage");
                price = rs.getDouble("price");
                vin = rs.getInt("vin");
                type = rs.getString("type");
                sold = rs.getBoolean("sold");

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                vehicles.add(vehicle);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;

    }

    @Override
    public List<Vehicle> findVehicleByVin(int vin) {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT vin, make, model, year, color, mileage, price, type FROM vehicles WHERE vin = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, vin);  // Set the VIN parameter in the query.

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                // Retrieve the values from the ResultSet.
                int year = rs.getInt("year");
                String make = rs.getString("make");
                String model = rs.getString("model");
                String color = rs.getString("color");
                int mileage = rs.getInt("mileage");
                double price = rs.getDouble("price");
                String type = rs.getString("type");
                //boolean sold = rs.getBoolean("sold");

                // Create the Vehicle object using the data retrieved from the ResultSet.
             Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                vehicles.add(vehicle);

            }

        } catch (SQLException e) {
            // Log the exception and rethrow it as a RuntimeException for now
            throw new RuntimeException("Error retrieving vehicle by VIN: " + vin, e);
        }

        return vehicles;  // Return the vehicle object, or null if not found.
    }


    @Override
    public boolean removeVehicle(int vin) {
        String query = "DELETE FROM vehicles WHERE vin = ?";  // SQL query to delete the vehicle

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, vin);  // Set the VIN parameter in the query

            int rowsAffected = preparedStatement.executeUpdate();

            // If rowsAffected is greater than 0, the deletion was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error removing vehicle with VIN: " + vin, e);
        }
    }



    @Override
    public void addVehicle(Vehicle vehicle) {
        String query = """
            INSERT INTO vehicles (vin, year, make, model, type, color, mileage, price, sold)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, vehicle.getVin());
            ps.setInt(2, vehicle.getYear());
            ps.setString(3, vehicle.getMake());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getType()); // Set type first
            ps.setString(6, vehicle.getColor());
            ps.setInt(7, vehicle.getMileage());
            ps.setDouble(8, vehicle.getPrice());
            ps.setBoolean(9, vehicle.isSold());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {

        String query = """
        UPDATE vehicles
        SET make = ?, model = ?, year = ?, color = ?, mileage = ?, price = ?, type = ?
        WHERE vin = ?
    """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, vehicle.getMake());
            ps.setString(2, vehicle.getModel());
            ps.setInt(3, vehicle.getYear());
            ps.setString(4, vehicle.getColor());
            ps.setInt(5, vehicle.getMileage());
            ps.setDouble(6, vehicle.getPrice());
            ps.setString(7, vehicle.getType());
          //  ps.setBoolean(8, vehicle.isSold());
            ps.setInt(8, vehicle.getVin());

            ps.executeUpdate();  // Execute the update statement
        } catch (SQLException e) {
            throw new RuntimeException("Error updating vehicle with VIN: " + vehicle.getVin(), e);
        }
    }


}