package com.dealership.service;

import com.dealership.dao.CarDAO;
import com.dealership.model.Car;
import com.dealership.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class InventoryService {
    private CarDAO carDAO;

    public InventoryService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public void addCar(Car car) {
        carDAO.addCar(car);
    }

    public void updateCar(Car car) {
        carDAO.updateCar(car);
    }

    public void deleteCar(int id) {
        carDAO.deleteCar(id);
    }

    public Car getCarById(int id) {
        return carDAO.getCarById(id);
    }

    public ArrayList<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public ArrayList<Car> searchCars(String make, String model, int minYear, int maxYear, double minPrice, double maxPrice) {
        return carDAO.searchCars(make, model, minYear, maxYear, minPrice, maxPrice);
    }

    // Additional method to search cars by ID (assuming it's implemented in CarDAO)
    public Car searchCarById(int id) {
        return carDAO.getCarById(id);
    }
    public void decreaseCarQuantity(int carId, int quantity) {
        Car car = carDAO.getCarById(carId);
        if (car != null) {
            int currentQuantity = car.getQuantity();
            if (currentQuantity >= quantity) {
                car.setQuantity(currentQuantity - quantity);
                carDAO.updateCar(car);
            } else {
                System.out.println("Not enough cars in inventory.");
            }
        } else {
            System.out.println("Car not found in inventory.");
        }
    }
}
