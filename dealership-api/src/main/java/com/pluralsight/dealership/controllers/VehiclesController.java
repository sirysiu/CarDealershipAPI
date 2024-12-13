package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.dao.VehicleDAOMysqlImpl;
import com.pluralsight.dealership.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

    private final VehicleDAOMysqlImpl vehicleDao;

    // Inject the VehicleDAO through the constructor
    @Autowired
    public VehiclesController(VehicleDAOMysqlImpl vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    // Endpoint to retrieve vehicles by price range
    @GetMapping("/by-price")
    public List<Vehicle> findVehiclesByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        return vehicleDao.findVehicleByPriceRange(minPrice, maxPrice);
    }

    // Endpoint to retrieve vehicles by make and model
    @GetMapping("/by-make-and-model")
    public List<Vehicle> findVehiclesByMakeAndModel(
            @RequestParam String make,
            @RequestParam String model) {
        return vehicleDao.findVehicleByMakeAndModel(make, model);
    }

    // Endpoint to retrieve vehicles by year
    @GetMapping("/by-year")
    public List<Vehicle> findVehiclesByYear(@RequestParam int year) {
        return vehicleDao.findVehicleByYear(year);
    }

    // Endpoint to retrieve vehicles by color
    @GetMapping("/by-color")
    public List<Vehicle> findVehiclesByColor(@RequestParam String color) {
        return vehicleDao.findVehicleByColor(color);
    }

    // Endpoint to retrieve vehicles by mileage
    @GetMapping("/by-mileage")
    public List<Vehicle> findVehiclesByMileage(@RequestParam int maxMileage) {
        return vehicleDao.findVehicleByMileage(maxMileage);
    }

    // Endpoint to retrieve vehicles by type (e.g., SUV, Sedan)
    @GetMapping("/by-type")
    public List<Vehicle> findVehiclesByType(@RequestParam String type) {
        return vehicleDao.findVehicleByType(type);
    }

    // Endpoint to retrieve all vehicles
    @GetMapping("/")
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.findAllVehicles();
    }

    // Endpoint to add a new vehicle
    @PostMapping("/add")
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
    }

    // Endpoint to remove a vehicle by VIN
    @DeleteMapping("/remove/{vin}")
    public boolean removeVehicle(@PathVariable int vin) {
        return vehicleDao.removeVehicle(vin);
    }

}
