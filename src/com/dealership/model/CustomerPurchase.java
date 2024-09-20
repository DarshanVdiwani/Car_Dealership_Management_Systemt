package com.dealership.model;

import java.util.Date;

public class CustomerPurchase {
    private int id;
    private int customerId;
    private int carId;
    private Date purchaseDate;
    private double purchaseAmount;

    public CustomerPurchase(int id, int customerId, int carId, Date purchaseDate, double purchaseAmount) {
        this.id = id;
        this.customerId = customerId;
        this.carId = carId;
        this.purchaseDate = purchaseDate;
        this.purchaseAmount = purchaseAmount;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    @Override
    public String toString() {
        return "CustomerPurchase{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", carId=" + carId +
                ", purchaseDate=" + purchaseDate +
                ", purchaseAmount=" + purchaseAmount +
                '}';
    }
}
