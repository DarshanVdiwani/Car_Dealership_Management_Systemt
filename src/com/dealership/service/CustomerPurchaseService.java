package com.dealership.service;

import com.dealership.dao.CustomerPurchaseDAO;
import com.dealership.jdbc.JDBCUtil;
import com.dealership.model.Customer;
import com.dealership.model.CustomerPurchase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CustomerPurchaseService {
    private CustomerPurchaseDAO customerPurchaseDAO;

    public CustomerPurchaseService(CustomerPurchaseDAO customerPurchaseDAO) {
        this.customerPurchaseDAO = customerPurchaseDAO;
    }
    
    public void addPurchase(CustomerPurchase customerPurchase) {
        customerPurchaseDAO.addPurchase(customerPurchase);
    }

    
    public ArrayList<CustomerPurchase> getAllCustomerPurchases() {
    	ArrayList<CustomerPurchase> customerspurchase = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM customer_purchases";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                CustomerPurchase customerPurchase = new CustomerPurchase(
                        resultSet.getInt("id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("car_id"),
                        resultSet.getDate("purchase_date"),
                        resultSet.getDouble("purchase_amount")
                );
                customerspurchase.add(customerPurchase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerspurchase;
    }
}
