# Car Dealership Management System

This Spring Boot application provides a RESTful API to manage a car dealership's vehicles, sales contracts, and lease contracts. It uses MySQL as the database and supports operations like creating, retrieving, and listing vehicles and contracts.

## Features
- **Vehicle Management**: Add, retrieve, and list vehicles.
- **Sales Contract Management**: Manage sales contracts associated with vehicles.
- **Lease Contract Management**: Manage lease contracts for customers.

## API Endpoints

### Vehicle Management
- `GET /vehicles`: Retrieves all vehicles.
- `GET /vehicles/{vin}`: Retrieves a vehicle by its VIN.
- `POST /vehicles/add`: Adds a new vehicle.

### Sales Contract Management
- `GET /sales-contracts`: Retrieves all sales contracts.
- `GET /sales-contracts/by-vin/{vin}`: Retrieves a sales contract by VIN.
- `POST /sales-contracts/add`: Adds a new sales contract.

### Lease Contract Management
- `GET /lease-contracts`: Retrieves all lease contracts.
- `GET /lease-contracts/by-vin/{vin}`: Retrieves a lease contract by VIN.
- `POST /lease-contracts/add`: Adds a new lease contract.

## Conclusion

The Car Dealership Management System provides a robust solution for managing vehicles, sales contracts, and lease contracts within a car dealership. By leveraging Spring Boot, MySQL, and RESTful APIs, this application allows for efficient handling of all major dealership operations.

With the ability to add, retrieve, and list vehicles and contracts, it supports both sales and lease management, making it a powerful tool for any dealership looking to manage their inventory and customer contracts.

Feel free to clone the repository, set up your environment, and extend or customize the system as needed to fit your specific requirements. We hope this system helps you streamline dealership operations and provide excellent service to your customers.

For further questions, feature requests, or contributions, feel free to open an issue or pull request on GitHub.

Thank you for using the Car Dealership Management System!
