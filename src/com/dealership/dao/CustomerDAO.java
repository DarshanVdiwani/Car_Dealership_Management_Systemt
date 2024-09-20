package com.dealership.dao;

import com.dealership.model.Customer;

import java.util.ArrayList;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
    Customer getCustomerById(int id);
    ArrayList<Customer> getAllCustomers();
    ArrayList<Customer> searchCustomers(String name, String contact);
}
