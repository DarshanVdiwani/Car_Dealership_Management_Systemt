package com.dealership.model;

import java.util.Date;

public class Sale {
    private int id;
    private int carId;
    private int customerId;
    private Date saleDate;
    private double amount;

    public Sale(int id, int carId, int customerId, Date saleDate, double amount) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.amount = amount;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", carId=" + carId +
                ", customerId=" + customerId +
                ", saleDate=" + saleDate +
                ", amount=" + amount +
                '}';
    }
}
