package com.pluralsight.car.models;

public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;
    private int mileage;
    private double price;
    private boolean sold;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Vehicle(int vin, int year, String make, String model, String type, String color, int mileage, double price, boolean sold) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
        this.sold = sold;

    }

    public int getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }
    public int getMileage() {
        return mileage;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("VIN: %d, Year: %d, Make: %s, Model: %s, Type: %s, Color: %s, Mileage: %d, Price: %.2f",
                vin, year, make, model, type, color, mileage, price);
    }


}
