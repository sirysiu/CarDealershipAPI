package com.pluralsight.dealership.models;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Dealership {
    private  String name = "";
    private  String address;
    private  String phone;
    private final ArrayList<Vehicle> inventory;

    public Dealership() {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }


    public String getPhone() {
        return phone;
    }


    public String getAddress() {
        return address;
    }


    public String getName() {
        return name;
    }


    public ArrayList<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice) {
        ArrayList<Vehicle> vehiclesByPrice = inventory.stream()
                .filter(vehicle -> vehicle.getPrice() >= minPrice && vehicle.getPrice() <= maxPrice) // Filter by price range
                .collect(Collectors.toCollection(ArrayList::new)); // Collect to ArrayList

        return vehiclesByPrice;
    }
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> vehiclesByMakeModel = inventory.stream()
                .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) // Filter by make and model
                .collect(Collectors.toCollection(ArrayList::new)); // Collect to ArrayList

        return vehiclesByMakeModel;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int year) {
        ArrayList<Vehicle> vehiclesByYear = inventory.stream()
                .filter(vehicle -> vehicle.getYear() == year) // Filter by year
                .collect(Collectors.toCollection(ArrayList::new)); // Collect to ArrayList

        return vehiclesByYear;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String specificColor) {
        ArrayList<Vehicle> vehiclesByColor = inventory.stream()
                .filter(vehicle -> vehicle.getColor().equalsIgnoreCase(specificColor)) // Filter by specific color
                .collect(Collectors.toCollection(ArrayList::new)); // Collect to ArrayList

        return vehiclesByColor;
    }
    public ArrayList<Vehicle> getVehiclesByMileage(int specificMileage) {
        ArrayList<Vehicle> vehiclesByMileage = inventory.stream()
                .filter(vehicle -> vehicle.getMileage() == specificMileage) // Filter by specific mileage
                .collect(Collectors.toCollection(ArrayList::new)); // Collect to ArrayList

        return vehiclesByMileage;
    }

    public ArrayList<Vehicle> getVehiclesByType(String specificType) {
        ArrayList<Vehicle> vehiclesByType = inventory.stream()
                .filter(vehicle -> vehicle.getType().equalsIgnoreCase(specificType)) // Filter by specific type
                .collect(Collectors.toCollection(ArrayList::new)); // Collect to ArrayList

        return vehiclesByType;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }
    public boolean removeVehicle(int vin) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vin) {
                inventory.remove(vehicle);
                return true; // Vehicle removed successfully
            }
        }
        return false; // Vehicle not found
    }


}

