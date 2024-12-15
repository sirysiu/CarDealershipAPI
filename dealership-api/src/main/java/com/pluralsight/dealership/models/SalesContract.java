package com.pluralsight.dealership.models;

public class SalesContract {

    private int vin;
    private String contractDate;
    private String customerName;
    private String customerEmail;
    private double totalPrice;
    private double monthlyPayment;
    private double salesTaxes;
    private double recordingFees;
    private boolean isFinancing;

    // Constructor
    public SalesContract(int vin, String contractDate, String customerName, String customerEmail,
                         double totalPrice, double monthlyPayment, double salesTaxes, double recordingFees,
                         boolean isFinancing) {
        this.vin = vin;
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
        this.salesTaxes = salesTaxes;
        this.recordingFees = recordingFees;
        this.isFinancing = isFinancing;
    }

    // Getters and Setters
    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getSalesTaxes() {
        return salesTaxes;
    }

    public void setSalesTaxes(double salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public double getRecordingFees() {
        return recordingFees;
    }

    public void setRecordingFees(double recordingFees) {
        this.recordingFees = recordingFees;
    }

    public boolean isFinancing() {
        return isFinancing;
    }

    public void setFinancing(boolean financing) {
        isFinancing = financing;
    }

    // toString method for better logging and debugging
    @Override
    public String toString() {
        return "SalesContract{" +
                "vin=" + vin +
                ", contractDate='" + contractDate + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", totalPrice=" + totalPrice +
                ", monthlyPayment=" + monthlyPayment +
                ", salesTaxes=" + salesTaxes +
                ", recordingFees=" + recordingFees +
                ", isFinancing=" + isFinancing +
                '}';
    }
}
