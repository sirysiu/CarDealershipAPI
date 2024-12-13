package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.dao.VehicleDAOMysqlImpl;
import com.pluralsight.dealership.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @ResponseStatus(code= HttpStatus.CREATED)
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
        return vehicle;
    }

    // Endpoint to remove a vehicle by VIN
    @DeleteMapping("/remove/{vin}")
    public boolean removeVehicle(@PathVariable int vin) {
        return vehicleDao.removeVehicle(vin);
    }

        // PUT endpoint to update an existing vehicle
        @PutMapping("/update/{vin}")
        @ResponseStatus(code = HttpStatus.OK)
        public Vehicle updateVehicle(@PathVariable int vin, @RequestBody Vehicle updatedVehicle) {
            // Attempt to find the vehicle by VIN and update it
            List<Vehicle> existingVehicles = vehicleDao.findVehicleByVin(vin);

            if (existingVehicles == null) {
                // If vehicle is not found, return a 404 (Not Found) response
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with VIN: " + vin);
            }
            Vehicle existingVehicle = existingVehicles.get(0);  // This gets the first (and likely only) vehicle


            // Update the existing vehicle with the new data
            existingVehicle.setMake(updatedVehicle.getMake());
            existingVehicle.setModel(updatedVehicle.getModel());
            existingVehicle.setYear(updatedVehicle.getYear());
            existingVehicle.setColor(updatedVehicle.getColor());
            existingVehicle.setMileage(updatedVehicle.getMileage());
            existingVehicle.setPrice(updatedVehicle.getPrice());
            existingVehicle.setType(updatedVehicle.getType());
            //existingVehicle.setSold(updatedVehicle.isSold());

            // Call the DAO to save the updated vehicle
            vehicleDao.updateVehicle(existingVehicle);

            // Return the updated vehicle
            return existingVehicle;
        }


    }



