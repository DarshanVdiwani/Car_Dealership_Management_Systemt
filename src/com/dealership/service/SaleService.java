package com.dealership.service;

import com.dealership.dao.SaleDAO;
import com.dealership.model.Sale;
import com.dealership.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleService {
    private SaleDAO saleDAO;

    public SaleService(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }

    public void addSale(Sale sale) {
        saleDAO.addSale(sale);
    }

    public ArrayList<Sale> getSalesByCarId(int carId) {
        return saleDAO.getSalesByCarId(carId);
    }

    public ArrayList<Sale> getSalesByCustomerId(int customerId) {
        return saleDAO.getSalesByCustomerId(customerId);
    }

    public ArrayList<Sale> getAllSales() {
        return saleDAO.getAllSales();
    }

    public ArrayList<Sale> getSalesByDateRange(Date startDate, Date endDate) {
        return saleDAO.getSalesByDateRange(startDate, endDate);
    }

    // Additional method to search sales by ID (assuming it's implemented in SaleDAO)
//    public Sale getSaleById(int id) {
//        return saleDAO.getSaleById(id);
//    }
}
