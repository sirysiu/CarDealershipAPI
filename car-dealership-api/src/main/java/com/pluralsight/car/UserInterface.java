package com.pluralsight.car;

import com.pluralsight.car.dao.VehicleDAOMysqlImpl;
import com.pluralsight.car.models.Contract;
import com.pluralsight.car.models.Dealership;
import com.pluralsight.car.models.LeaseContract;
import com.pluralsight.car.models.Vehicle;
import com.pluralsight.car.styles.ColorCodes;

import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

  private Dealership dealership;
  Scanner scanner = new Scanner(System.in);
  private VehicleDAOMysqlImpl vehicleDAOMysql;  // Declare the VehicleDAOMysqlImpl object
  private BasicDataSource dataSource;

  public UserInterface(BasicDataSource dataSource) {
    this.dataSource = dataSource;
    this.vehicleDAOMysql = new VehicleDAOMysqlImpl(dataSource);
    this.dealership = new Dealership();

  }


  public void display() {

    boolean isRunning = true;
    while (isRunning) {
      menus();
      String input = scanner.nextLine().trim();
      switch (input) {
        case "1":
          processGetByPriceRequest();
          break;
        case "2":
          processGetByMakeModelRequest();
          break;
        case "3":
          processGetByYearRequest();
          break;
        case "4":
          processGetByColor();
          break;
        case "5":
          processGetByMileageRequest();
          break;
        case "6":
          processGetByType();
          break;
        case "7":
         processAllVehiclesRequest();

          break;
        case "8":
          processAddVehicleRequest();
          break;
        case "9":
          processRemoveVehicleRequest();
          break;
        case "0":
          processSellOrLeaseVehicleRequest();
          break;
        case "x", "X":
          isRunning = false;
      }

    }
  }

  private static void menus() {
    System.out.println(ColorCodes.BLUE + "┌───────────────────────────────────────┐");
    System.out.println(ColorCodes.BLUE + "│                Menu                   │");
    System.out.println(ColorCodes.BLUE + "│         Please Enter an Option:       │");
    System.out.println(ColorCodes.BLUE + "├───────────────────────────────────────┤");
    System.out.println(ColorCodes.GREEN + "│ [1] Vehicle By Price                  │");
    System.out.println(ColorCodes.GREEN + "│ [2] Vehicle By Make and Model         │");
    System.out.println(ColorCodes.GREEN + "│ [3] Vehicle By Year                   │");
    System.out.println(ColorCodes.GREEN + "│ [4] Vehicle By Color                  │");
    System.out.println(ColorCodes.GREEN + "│ [5] Vehicle By Mileage                │");
    System.out.println(ColorCodes.GREEN + "│ [6] Vehicle By Type                   │");
    System.out.println(ColorCodes.GREEN + "│ [7] Show All                          │");
    System.out.println(ColorCodes.GREEN + "│ [8] Add Vehicle                       │");
    System.out.println(ColorCodes.GREEN + "│ [9] Remove Vehicle                    │");
    System.out.println(ColorCodes.GREEN + "│ [0] Sell/ Lease Vehicle               │");
    System.out.println(ColorCodes.RED + "│ [x] Exit                              │");
    System.out.println(ColorCodes.BLUE + "└───────────────────────────────────────┘" + ColorCodes.RESET);
  }


  private void displayVehicles(ArrayList<Vehicle> vehicles) {
    // Check if the vehicle list is empty
    if (vehicles.isEmpty()) {
      System.out.println(ColorCodes.YELLOW + "No vehicles found." + ColorCodes.RESET);
      return;
    }

    // Print the header with color
    System.out.printf(ColorCodes.BLUE + "%-10s %-5s %-15s %-15s %-10s %-10s %-10s %-10s%n" + ColorCodes.RESET,
            "VIN", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price");
    System.out.println(ColorCodes.BLUE + "---------------------------------------------------------------------------------------" + ColorCodes.RESET);

    // Print each vehicle's details
    for (Vehicle vehicle : vehicles) {
      System.out.printf("%-10d %-5d %-15s %-15s %-10s %-10s %-10d $%-9.2f%n",
              vehicle.getVin(), vehicle.getYear(), vehicle.getMake(),
              vehicle.getModel(), vehicle.getType(), vehicle.getColor(),
              vehicle.getMileage(), vehicle.getPrice());
    }
  }

  private void processGetByPriceRequest() {
    // Get minimum price from the user
    System.out.print("Enter the minimum price: ");
    double minPrice;
    while (true) {
      try {
        minPrice = Double.parseDouble(scanner.nextLine().trim()); // Get user input for minimum price
        break;  // Exit loop if parsing is successful
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number for minimum price.");
        System.out.print("Enter the minimum price: ");
      }
    }

    // Get maximum price from the user
    System.out.print("Enter the maximum price: ");
    double maxPrice;
    while (true) {
      try {
        maxPrice = Double.parseDouble(scanner.nextLine().trim()); // Get user input for maximum price
        break;  // Exit loop if parsing is successful
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number for maximum price.");
        System.out.print("Enter the maximum price: ");
      }
    }

    // Retrieve vehicles within the specified price range
    List<Vehicle> vehiclesByPrice = vehicleDAOMysql.findVehicleByPriceRange(minPrice, maxPrice);

    // If no vehicles were found, display a message
    if (vehiclesByPrice.isEmpty()) {
      System.out.println("No vehicles found in the specified price range.");
    } else {
      // Convert List to ArrayList before passing to displayVehicles if needed
      displayVehicles(new ArrayList<>(vehiclesByPrice)); // Use the display method to print vehicles
    }
  }




  public void processAllVehiclesRequest() {
    // Configure the BasicDataSource with the correct DB connection info

    // Create an instance of VehicleDAOMysqlImpl with the configured data source
    VehicleDAOMysqlImpl vehicleDAOMysql = new VehicleDAOMysqlImpl(dataSource);

    // Retrieve all vehicles from the database
    List<Vehicle> vehicles = vehicleDAOMysql.findAllVehicles();

    // Convert List to ArrayList before passing to displayVehicles
    displayVehicles(new ArrayList<>(vehicles));

    // Print the make and model of each vehicle
    vehicles.forEach(vehicle -> System.out.println(vehicle.getMake() + " " + vehicle.getModel()));
  }
  private void processGetByMakeModelRequest() {
    // Prompt user for make and model
    System.out.print("Enter the make of the vehicle: ");
    String make = scanner.nextLine().trim();  // Get user input for make

    System.out.print("Enter the model of the vehicle: ");
    String model = scanner.nextLine().trim(); // Get user input for model

    // Create an instance of VehicleDAOMysqlImpl with the configured data source
    VehicleDAOMysqlImpl vehicleDAOMysql = new VehicleDAOMysqlImpl(dataSource);

    // Retrieve vehicles based on make and model from the database
    List<Vehicle> vehicles = vehicleDAOMysql.findVehicleByMakeAndModel(make, model);

    // Convert List to ArrayList before passing to displayVehicles
    displayVehicles(new ArrayList<>(vehicles));

    // Print the make and model of each vehicle (this line is optional if you're displaying the vehicles already)
    vehicles.forEach(vehicle -> System.out.println(vehicle.getMake() + " " + vehicle.getModel()));
  }


  private void processGetByYearRequest() {
    System.out.print("Enter the year of the vehicle: ");
    int year = scanner.nextInt();  // Get user input for the year
    scanner.nextLine();  // Consume the newline character

    // Call the DAO method to get vehicles by year
    List<Vehicle> vehiclesByYear = vehicleDAOMysql.findVehicleByYear(year);

    // Display the retrieved vehicles
    displayVehicles(new ArrayList<>(vehiclesByYear));  // Convert List to ArrayList if needed
  }
  private void processGetByColor() {
    System.out.print("Enter the color of the vehicle: ");
    String color = scanner.nextLine().trim();  // Get user input for color

    // Call the DAO method to get vehicles by color
    List<Vehicle> vehiclesByColor = vehicleDAOMysql.findVehicleByColor(color);

    // If no vehicles were found, display a message
    if (vehiclesByColor.isEmpty()) {
      System.out.println("No vehicles found for color: " + color);
    } else {
      // Convert List to ArrayList before passing to displayVehicles if needed
      displayVehicles(new ArrayList<>(vehiclesByColor));
    }
  }


  private void processGetByMileageRequest() {
    // Prompt the user for the maximum mileage
    System.out.print("Enter the maximum mileage of the vehicle: ");
    int maxMileage;
    while (true) {
      try {
        maxMileage = Integer.parseInt(scanner.nextLine().trim()); // Get user input for maximum mileage
        break;  // Exit loop if parsing is successful
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid number for maximum mileage.");
        System.out.print("Enter the maximum mileage of the vehicle: ");
      }
    }

    // Retrieve vehicles with mileage less than or equal to the specified maxMileage
    List<Vehicle> vehiclesByMileage = vehicleDAOMysql.findVehicleByMileage(maxMileage);

    // If no vehicles were found, display a message
    if (vehiclesByMileage.isEmpty()) {
      System.out.println("No vehicles found with the specified mileage.");
    } else {
      // Convert List to ArrayList before passing to displayVehicles if needed
      displayVehicles(new ArrayList<>(vehiclesByMileage));  // Use the display method to print vehicles
    }
  }

  private void processGetByType() {
    System.out.print("Enter the type of the vehicle: ");
    String type = scanner.nextLine().trim();  // Get user input for vehicle type

    try {
      // Assuming dealership.getVehiclesByType(type) calls the corresponding DAO method
      List<Vehicle> vehiclesByType = vehicleDAOMysql.findVehicleByType(type);
      displayVehicles((ArrayList<Vehicle>) vehiclesByType);  // Use the display method to print vehicles
    } catch (Exception e) {
      System.out.println("An error occurred while retrieving vehicles by type: " + e.getMessage());
    }
  }


  private void processAddVehicleRequest() {
    // Prompt the user for vehicle details
    System.out.print("Enter VIN: ");
    int vin = Integer.parseInt(scanner.nextLine().trim());  // VIN should be an integer

    System.out.print("Enter Year: ");
    int year = Integer.parseInt(scanner.nextLine().trim());

    System.out.print("Enter Make: ");
    String make = scanner.nextLine().trim();

    System.out.print("Enter Model: ");
    String model = scanner.nextLine().trim();

    System.out.print("Enter Type (e.g., Sedan, SUV, Coupe): ");
    String type = scanner.nextLine().trim();

    System.out.print("Enter Color: ");
    String color = scanner.nextLine().trim();

    System.out.print("Enter Mileage: ");
    int mileage = Integer.parseInt(scanner.nextLine().trim());

    System.out.print("Enter Price: ");
    double price = Double.parseDouble(scanner.nextLine().trim());

    System.out.print("Is the vehicle sold (true/false)? ");
    boolean sold = Boolean.parseBoolean(scanner.nextLine().trim());

    // Create a new Vehicle object using the input values
    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, sold);

    // Add the vehicle to the database
    vehicleDAOMysql.addVehicle(vehicle);

    // Provide feedback to the user
    System.out.println("Vehicle added successfully!");
  }


  private void processRemoveVehicleRequest() {
    // Prompt the user to enter the VIN of the vehicle to remove
    System.out.print("Enter the VIN of the vehicle to remove: ");
    String vinInput = scanner.nextLine();  // Read the VIN as a string

    try {
      // Convert the VIN string to an integer
      int vin = Integer.parseInt(vinInput);

      // Call the removeVehicle method from the Vehicle DAO or Dealership class
      boolean removed = vehicleDAOMysql.removeVehicle(vin);  // Replace vehicleDAOMysql with your DAO

      // Provide feedback to the user based on whether the removal was successful
      if (removed) {
        System.out.println("Vehicle with VIN " + vin + " removed successfully!");
      } else {
        System.out.println("Vehicle with VIN " + vin + " not found or could not be removed.");
      }

    } catch (NumberFormatException e) {
      // Handle invalid input (e.g., if the user doesn't enter a valid number for VIN)
      System.out.println("Invalid VIN format. VIN must be a numeric value.");
    } catch (Exception e) {
      // Handle any other exceptions (e.g., database connection issues)
      System.out.println("An error occurred while removing the vehicle: " + e.getMessage());
    }
  }




  public void processSellOrLeaseVehicleRequest() {

  }


}


