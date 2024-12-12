package com.pluralsight.car.models;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public boolean isFinancing() {
        return isFinancing;
    }

    private boolean isFinancing;  // Add a field to track financing status


    public double getLeaseFee() {
        return leaseFee;
    }

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = vehicleSold.getPrice() / 2;
        this.leaseFee = vehicleSold.getPrice() * 0.07;
    }

    @Override
    public String toString() {
        return "LeaseContract{" +
                "expectedEndingValue=" + expectedEndingValue +
                ", leaseFee=" + leaseFee +
                ", date='" + date + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", vehicleSold=" + vehicleSold +
                ", totalPrice=" + totalPrice +
                ", monthlyPayment=" + monthlyPayment +
                '}';
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }
    @Override
    public double getTotalPrice() {
        return expectedEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        return expectedEndingValue * Math.pow((1 + (0.04 / 12)), 36) - expectedEndingValue;
    }

}
