package com.dealership.dao;

import com.dealership.jdbc.JDBCUtil;
import com.dealership.model.CustomerPurchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerPurchaseDAOImpl implements CustomerPurchaseDAO {

    @Override
    public void addPurchase(CustomerPurchase customerPurchase) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO customer_purchases (customer_id, car_id, purchase_date,purchase_amount) VALUES (?, ?, ?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerPurchase.getCustomerId());
            statement.setInt(2, customerPurchase.getCarId());
            statement.setDate(3,new java.sql.Date(customerPurchase.getPurchaseDate().getTime()));
            statement.setDouble(4, customerPurchase.getPurchaseAmount());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> getPurchasedCarsByCustomer(int customerId) {
        List<Integer> purchasedCars = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT car_id FROM customer_purchases WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                purchasedCars.add(resultSet.getInt("car_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchasedCars;
    }

    @Override
    public List<Integer> getCustomersByPurchasedCar(int carId) {
        List<Integer> customers = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT customer_id FROM customer_purchases WHERE car_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customers.add(resultSet.getInt("customer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<CustomerPurchase> getAllCustomerPurchases() {
        List<CustomerPurchase> customerPurchases = new ArrayList<>();
        String sql = "SELECT * FROM customer_purchase";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                CustomerPurchase customerPurchase = extractCustomerPurchaseFromResultSet(resultSet);
                customerPurchases.add(customerPurchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerPurchases;
    }
    private CustomerPurchase extractCustomerPurchaseFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int customerId = resultSet.getInt("customer_id");
        int carId = resultSet.getInt("car_id");
        Date date = resultSet.getDate("purchase_date");
        double purchaseAmount = resultSet.getDouble("purchase_amount");
        return new CustomerPurchase(id, customerId, carId, date, purchaseAmount);
    }


}
