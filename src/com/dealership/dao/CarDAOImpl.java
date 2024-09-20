package com.dealership.dao;

import com.dealership.model.Car;
import com.dealership.jdbc.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

public class CarDAOImpl implements CarDAO {

    @Override
    public void addCar(Car car) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO cars (make, model, year, price,quantity) VALUES (?, ?, ?, ?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setDouble(4, car.getPrice());
            statement.setInt(5, car.getQuantity());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCar(Car car) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE cars SET make = ?, model = ?, year = ?, price = ?, quantity = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setDouble(4, car.getPrice());
            statement.setInt(5, car.getQuantity());
            statement.setInt(6, car.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCar(int id) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM cars WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car getCarById(int id) {
        Car car = null;
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM cars WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getDouble("price"), 
                        resultSet.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM cars";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Car car = new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public ArrayList<Car> searchCars(String make, String model, int minYear, int maxYear, double minPrice, double maxPrice) {
        ArrayList<Car> cars = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            StringBuilder sql = new StringBuilder("SELECT * FROM cars WHERE 1=1");
            if (make != null && !make.isEmpty()) {
                sql.append(" AND make = ?");
            }
            if (model != null && !model.isEmpty()) {
                sql.append(" AND model = ?");
            }
            if (minYear > 0) {
                sql.append(" AND year >= ?");
            }
            if (maxYear > 0) {
                sql.append(" AND year <= ?");
            }
            if (minPrice > 0) {
                sql.append(" AND price >= ?");
            }
            if (maxPrice > 0) {
                sql.append(" AND price <= ?");
            }

            PreparedStatement statement = connection.prepareStatement(sql.toString());

            int parameterIndex = 1;
            if (make != null && !make.isEmpty()) {
                statement.setString(parameterIndex++, make);
            }
            if (model != null && !model.isEmpty()) {
                statement.setString(parameterIndex++, model);
            }
            if (minYear > 0) {
                statement.setInt(parameterIndex++, minYear);
            }
            if (maxYear > 0) {
                statement.setInt(parameterIndex++, maxYear);
            }
            if (minPrice > 0) {
                statement.setDouble(parameterIndex++, minPrice);
            }
            if (maxPrice > 0) {
                statement.setDouble(parameterIndex++, maxPrice);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
    
    @Override
    public double getCarPriceById(int carId) {
        double price = 0;
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT price FROM cars WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
}
