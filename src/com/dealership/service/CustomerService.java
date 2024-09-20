package com.dealership.service;

import com.dealership.dao.CustomerDAO;
import com.dealership.model.Customer;
import com.dealership.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    public ArrayList<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public ArrayList<Customer> searchCustomers(String name, String contact) {
        return customerDAO.searchCustomers(name, contact);
    }

    // Additional method to search customers by ID (assuming it's implemented in CustomerDAO)
    public Customer searchCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }
}
