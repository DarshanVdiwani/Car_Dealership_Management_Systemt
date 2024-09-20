package com.dealership.dao;

import com.dealership.model.Sale;
import com.dealership.jdbc.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class SaleDAOImpl implements SaleDAO {

    @Override
    public void addSale(Sale sale) {
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO sales (car_id, customer_id, sale_date, amount) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sale.getCarId());
            statement.setInt(2, sale.getCustomerId());
            statement.setDate(3, new java.sql.Date(sale.getSaleDate().getTime()));
            statement.setDouble(4, sale.getAmount());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    sale.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Sale> getSalesByCarId(int carId) {
    	ArrayList<Sale> sales = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM sales WHERE car_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, carId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sale sale = new Sale(
                        resultSet.getInt("id"),
                        resultSet.getInt("car_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getDate("sale_date"),
                        resultSet.getDouble("amount")
                );
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public ArrayList<Sale> getSalesByCustomerId(int customerId) {
    	ArrayList<Sale> sales = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM sales WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sale sale = new Sale(
                        resultSet.getInt("id"),
                        resultSet.getInt("car_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getDate("sale_date"),
                        resultSet.getDouble("amount")
                );
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public ArrayList<Sale> getAllSales() {
    	ArrayList<Sale> sales = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM sales";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Sale sale = new Sale(
                        resultSet.getInt("id"),
                        resultSet.getInt("car_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getDate("sale_date"),
                        resultSet.getDouble("amount")
                );
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public ArrayList<Sale> getSalesByDateRange(Date startDate, Date endDate) {
    	ArrayList<Sale> sales = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM sales WHERE sale_date BETWEEN ? AND ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sale sale = new Sale(
                        resultSet.getInt("id"),
                        resultSet.getInt("car_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getDate("sale_date"),
                        resultSet.getDouble("amount")
                );
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }
}
