package com.dealership.dao;

import com.dealership.model.Car;

import java.util.ArrayList;

public interface CarDAO {
    void addCar(Car car);
    void updateCar(Car car);
    void deleteCar(int id);
    Car getCarById(int id);
    double getCarPriceById(int carId);
    ArrayList<Car> getAllCars();
    ArrayList<Car> searchCars(String make, String model, int minYear, int maxYear, double minPrice, double maxPrice);
}
