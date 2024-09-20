package com.dealership.dao;

import com.dealership.model.Customer;
import com.dealership.jdbc.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void addCustomer(Customer customer) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO customers (name, address, contact) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getContact());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE customers SET name = ?, address = ?, contact = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getContact());
            statement.setInt(4, customer.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int id) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM customers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM customers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("contact")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
    	ArrayList<Customer> customers = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM customers";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("contact")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public ArrayList<Customer> searchCustomers(String name, String contact) {
    	ArrayList<Customer> customers = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            StringBuilder sql = new StringBuilder("SELECT * FROM customers WHERE 1=1");
            if (name != null && !name.isEmpty()) {
                sql.append(" AND name = ?");
            }
            if (contact != null && !contact.isEmpty()) {
                sql.append(" AND contact = ?");
            }

            PreparedStatement statement = connection.prepareStatement(sql.toString());

            int parameterIndex = 1;
            if (name != null && !name.isEmpty()) {
                statement.setString(parameterIndex++, name);
            }
            if (contact != null && !contact.isEmpty()) {
                statement.setString(parameterIndex++, contact);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("contact")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
