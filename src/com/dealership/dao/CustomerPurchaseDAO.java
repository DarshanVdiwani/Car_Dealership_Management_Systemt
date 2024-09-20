package com.dealership.dao;

import java.util.List;

import com.dealership.model.CustomerPurchase;

public interface CustomerPurchaseDAO {
	void addPurchase(CustomerPurchase customerPurchase);
    List<Integer> getPurchasedCarsByCustomer(int customerId);
    List<Integer> getCustomersByPurchasedCar(int carId);
    List<CustomerPurchase> getAllCustomerPurchases();
}
