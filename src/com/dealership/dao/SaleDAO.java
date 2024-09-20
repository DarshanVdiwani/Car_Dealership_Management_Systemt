package com.dealership.dao;

import com.dealership.model.Sale;


import java.util.ArrayList;
import java.util.Date;

public interface SaleDAO {
    void addSale(Sale sale);
    ArrayList<Sale> getSalesByCarId(int carId);
    ArrayList<Sale> getSalesByCustomerId(int customerId);
    ArrayList<Sale> getAllSales();
    ArrayList<Sale> getSalesByDateRange(Date startDate, Date endDate);
}
