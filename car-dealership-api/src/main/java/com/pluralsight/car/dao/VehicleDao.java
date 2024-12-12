package com.pluralsight.car.dao;

import com.pluralsight.car.models.Vehicle;

import java.util.List;

public interface VehicleDao {

    List<Vehicle> findVehicleByPriceRange(double minPrice, double maxPrice);
    List<Vehicle> findVehicleByMakeAndModel(String make, String model);
    List<Vehicle> findVehicleByYear(int year);
    List<Vehicle> findVehicleByColor(String color);
    List<Vehicle> findVehicleByMileage(int mileage);
    List<Vehicle> findVehicleByType(String type);
    List<Vehicle> findAllVehicles();

    boolean removeVehicle(int vin);  // Changed return type to boolean



    void addVehicle(Vehicle vehicle);
}
