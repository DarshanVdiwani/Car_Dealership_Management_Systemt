package com.dealership.ui;

import com.dealership.dao.CarDAO;

import com.dealership.dao.CarDAOImpl;
import com.dealership.dao.CustomerDAO;
import com.dealership.dao.CustomerDAOImpl;
import com.dealership.dao.SaleDAO;
import com.dealership.dao.SaleDAOImpl;
import com.dealership.dao.CustomerPurchaseDAO;
import com.dealership.dao.CustomerPurchaseDAOImpl;
import com.dealership.model.Car;
import com.dealership.model.Customer;
import com.dealership.model.CustomerPurchase;
import com.dealership.model.Sale;
import com.dealership.service.InventoryService;
import com.dealership.service.AuthService;
import com.dealership.service.CustomerPurchaseService;
import com.dealership.service.CustomerService;
import com.dealership.service.SaleService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	CarDAO carDAO = new CarDAOImpl();
    	CustomerDAO customerDAO = new CustomerDAOImpl();
    	SaleDAO saleDAO = new SaleDAOImpl();
    	CustomerPurchaseDAO customerPurchaseDAO = new CustomerPurchaseDAOImpl();
    	
    	InventoryService inventoryService = new InventoryService(carDAO);
    	CustomerService customerService = new CustomerService(customerDAO);
    	SaleService saleService = new SaleService(saleDAO);
    	CustomerPurchaseService customerPurchaseService = new CustomerPurchaseService(customerPurchaseDAO);
    	AuthService authService = new AuthService();
        boolean exitLogin = false;
        while(!exitLogin) {
        	System.out.println("\n\t\t\t\033[1;36m=== Welcome to Car Dealership System ===\033[0m");
        	System.out.print("\t\t\t\033[1;32mEnter username :\033[0m");
        	String uname = scanner.nextLine();
        	System.out.print("\t\t\t\033[1;32mEnter password :\033[0m");

        	String pwd = scanner.nextLine();
        	if (authService.authenticate(uname, pwd)) {
        		System.out.println("\n\t\t\t\033[1;32mAuthentication successful!\033[0m");
        		boolean exit = false;
                while (!exit) {
                    try {
                        printMenu();
                        int choice = getUserChoice();

                        switch (choice) {
                            case 1:
                                addCar(inventoryService);
                                break;
                            case 2:
                                showAllCars(inventoryService);
                                break;
                            case 3:
                                addCustomer(customerService);
                                break;
                            case 4:
                                showAllCustomers(customerService);
                                break;
                            case 5:
                            	updateCar(inventoryService);
                                break;
                            case 6:
                                purchaseCar(saleService,customerPurchaseService);
                                break;
                            case 7:
                            	showSalesReport(saleService);
                                break;
                            case 8:
                            	showAllCustomerPurchases(customerPurchaseService);
                            	break;
                            case 9:
                                exit = true;
                                exitLogin = true;
                                break;
                            default:
                                System.out.println("\t\t\t\033[1;31mInvalid choice. Please try again.\\033[0m");
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("\\t\\t\\t\\033[1;31mAn error occurred: " + e.getMessage() + "\\033[0m");
                    }
                }
        		
        	}
        }
    
        scanner.close();
    }

    private static void printMenu() {
    	System.out.println("\n\t\t\t\033[1;36m=== Welcome to Car Dealership System ===\033[0m");
    	System.out.println("\t\t\tChoose an option:");
    	System.out.println("\t\t\t\033[1;33m1. Add Car\033[0m");
    	System.out.println("\t\t\t\033[1;33m2. Show Car Details\033[0m");
    	System.out.println("\t\t\t\033[1;33m3. Add Customer\033[0m");
    	System.out.println("\t\t\t\033[1;33m4. Show Customer Details\033[0m");
    	System.out.println("\t\t\t\033[1;33m5. Update Car Details\033[0m");
    	System.out.println("\t\t\t\033[1;33m6. Purchase Car\033[0m");
    	System.out.println("\t\t\t\033[1;33m7. Show Sales Report\033[0m");
    	System.out.println("\t\t\t\033[1;33m8. Show Customer Purchase Details\033[0m");
    	System.out.println("\t\t\t\033[1;33m9. Exit\033[0m");

    }

    private static int getUserChoice() {
        System.out.print("\n\t\t\tEnter your choice: ");
        return scanner.nextInt();
    }

    private static void addCar(InventoryService inventoryService) {
       
    	try {
    	    System.out.println("\033[1;34mEnter car details:\033[0m"); 
    	    System.out.print("Make: ");
    	    String make = scanner.next();
    	    System.out.print("Model: ");
    	    String model = scanner.next();
    	    System.out.print("Year: ");
    	    int year = scanner.nextInt();
    	    System.out.print("Price: ");
    	    double price = scanner.nextDouble();
    	    System.out.print("Quantity: ");
    	    int quantity = scanner.nextInt();
    	    Car newCar = new Car(0, make, model, year, price, quantity);
    	    inventoryService.addCar(newCar);
    	    System.out.println("\n\t\t\t\033[1;32mCar added successfully!\033[0m"); 
    	} catch (Exception e) {
    	    System.out.println("\033[1;31mFailed to add car: " + e.getMessage() + "\033[0m"); 
    	}

    }

    private static void showAllCars(InventoryService inventoryService) {
    	try {
    	    ArrayList<Car> allCars = inventoryService.getAllCars();
    	    
    	    // Print table header
    	    System.out.println("\t\t\t\033[1;34m--------------------------------------------------------------------------\033[0m");
    	    System.out.println("\t\t\t\033[1;34m|   ID   |     Make     |    Model    |  Year  |  Price       | Quantity |\033[0m");
    	    System.out.println("\t\t\t\033[1;34m--------------------------------------------------------------------------\033[0m");

    	    for (Car c : allCars) {
    	        System.out.printf("\t\t\t\033[1;34m|\033[0m \033[1;37m%-5d\033[0m \033[1;34m|\033[0m \033[1;37m%-12s\033[0m \033[1;34m|\033[0m \033[1;37m%-11s\033[0m \033[1;34m|\033[0m  \033[1;37m%-5d\033[0m \033[1;34m|\033[0m \033[1;37m$%-10.2f\033[0m \033[1;34m|\033[0m   \033[1;37m%-6d\033[0m \033[1;34m|\033[0m\n",
    	                c.getId(), c.getMake(), c.getModel(), c.getYear(), c.getPrice(), c.getQuantity());
    	    }


    	    System.out.println("\t\t\t\033[1;34m--------------------------------------------------------------------------\033[0m");
    	} catch (Exception e) {
    	    System.out.println("\t\t\t\033[1;31mFailed to retrieve cars: " + e.getMessage() + "\033[0m");
    	}

    }

    private static void addCustomer(CustomerService customerService) {
    	try {
    	    System.out.println("\t\t\t\033[1;34mEnter customer details:\033[0m");
    	    System.out.print("\t\t\t\033[1;32mName:\033[0m ");
    	    String name = scanner.next();
    	    System.out.print("\t\t\t\033[1;32mAddress:\033[0m ");
    	    String address = scanner.next();
    	    System.out.print("\t\t\t\033[1;32mContact:\033[0m ");
    	    String contact = scanner.next();
    	    
    	    Customer newCustomer = new Customer(0, name, address, contact);
    	    customerService.addCustomer(newCustomer);
    	    
    	    System.out.println("\t\t\t\033[1;32mCustomer added successfully!\033[0m"); 
    	} catch (Exception e) {
    	    System.out.println("\033[1;31mFailed to add customer: " + e.getMessage() + "\033[0m"); 
    	}

    }

    private static void showAllCustomers(CustomerService customerService) {
    	try {
    	    List<Customer> allCustomers = customerService.getAllCustomers();

    	    
    	    System.out.println("\t\t\t\033[1;34m-----------------------------------------------------------------\033[0m");
    	    System.out.println("\t\t\t\033[1;34m|   ID   |        Name        |       Address       |  Contact  |\033[0m");
    	    System.out.println("\t\t\t\033[1;34m-----------------------------------------------------------------\033[0m");

    	    for (Customer c : allCustomers) {
    	        System.out.printf("\t\t\t\033[1;34m|\033[0m \033[1;37m%-5d\033[0m \033[1;34m|\033[0m \033[1;37m%-19s\033[0m \033[1;34m|\033[0m \033[1;37m%-19s\033[0m \033[1;34m|\033[0m \033[1;37m%-9s\033[0m \033[1;34m|\033[0m\n",
    	                c.getId(), c.getName(), c.getAddress(), c.getContact());
    	    }
    	    System.out.println("\t\t\t\033[1;34m-----------------------------------------------------------------\033[0m");
    	} catch (Exception e) {
    	    System.out.println("\t\t\t\033[1;31mFailed to retrieve customers: " + e.getMessage() + "\033[0m");
    	}
 }

    private static void purchaseCar(SaleService saleService,CustomerPurchaseService customerPurchaseService) {
    	try {
    	    CarDAO carDAO = new CarDAOImpl();
    	    System.out.println("\t\t\t\033[1;34mEnter sale details:\033[0m"); 
    	    System.out.print("\t\t\t\033[1;32mCar ID:\033[0m ");
    	    int carId = scanner.nextInt();
    	    System.out.print("\t\t\t\033[1;32mCustomer ID:\033[0m ");
    	    int customerId = scanner.nextInt();
    	    double amount = carDAO.getCarPriceById(carId);

    	    Sale newSale = new Sale(0, carId, customerId, new Date(), amount);
    	    CustomerPurchase purchase = new CustomerPurchase(0, customerId, carId, new Date(), amount);
    	    saleService.addSale(newSale);
    	    customerPurchaseService.addPurchase(purchase);
    	    InventoryService inventoryService = new InventoryService(carDAO);
    	    inventoryService.decreaseCarQuantity(carId, 1);
    	    System.out.println("\t\t\t\033[1;32mSale recorded successfully!\033[0m"); 
    	} catch (Exception e) {
    	    System.out.println("\t\t\t\033[1;31mFailed to record sale: " + e.getMessage() + "\033[0m"); 
    	}

    }


    private static void showSalesReport(SaleService saleService) {
    	try {
    	    System.out.println("\t\t\t\033[1;34mChoose an option for Sales Report:\033[0m");
    	    System.out.println("\t\t\t\033[1;33m1. Get Sales by Car ID\033[0m");
    	    System.out.println("\t\t\t\033[1;33m2. Get Sales by Customer ID\033[0m");
    	    System.out.println("\t\t\t\033[1;33m3. Get All Sales\033[0m"); 
    	    System.out.println("\t\t\t\033[1;33m4. Get Sales by Date Range\033[0m"); 
    	    
    	    int choice = scanner.nextInt();

    	    switch (choice) {
    	        case 1:
    	            System.out.print("\t\t\t\033[1;32mEnter Car ID:\033[0m "); 
    	            int carId = scanner.nextInt();

    	            System.out.println("\u001B[1mSales by Car ID:\u001B[0m");
    	            System.out.println("\u001B[4mID   | Car ID | Customer ID | Sale Date          | Amount   \u001B[0m");

    	            
    	            for (Sale sale : saleService.getSalesByCarId(carId)) {
    	                System.out.printf("%-5d| %-7d| %-12d| %-20s| $%-8.2f%n",
    	                        sale.getId(),
    	                        sale.getCarId(),
    	                        sale.getCustomerId(),
    	                        sale.getSaleDate(),
    	                        sale.getAmount());
    	            }

    	            break;
    	        case 2:
    	            System.out.print("\t\t\t\033[1;32mEnter Customer ID:\033[0m "); 
    	            int customerId = scanner.nextInt();
    	            System.out.println("\t\t\t\u001B[1mSales by Customer ID:\u001B[0m");
    	            System.out.println("\t\t\t\u001B[4mID   | Car ID | Customer ID | Sale Date          | Amount   \u001B[0m");

    	            
    	            for (Sale sale : saleService.getSalesByCustomerId(customerId)) {
    	                System.out.printf("\t\t\t%-5d| %-7d| %-12d| %-20s| $%-8.2f%n",
    	                        sale.getId(),
    	                        sale.getCarId(),
    	                        sale.getCustomerId(),
    	                        sale.getSaleDate(),
    	                        sale.getAmount());
    	            }
    	            break;
    	        case 3:
    	        	System.out.println("\t\t\t\u001B[1mAll Sales:\u001B[0m");
    	        	System.out.println("\t\t\t\u001B[4mID   | Car ID | Customer ID | Sale Date          | Amount   \u001B[0m");

    	        
    	        	for (Sale sale : saleService.getAllSales()) {
    	        	    System.out.printf("\t\t\t%-5d| %-7d| %-12d| %-20s| $%-8.2f%n",
    	        	            sale.getId(),
    	        	            sale.getCarId(),
    	        	            sale.getCustomerId(),
    	        	            sale.getSaleDate(),
    	        	            sale.getAmount());
    	        	}
    	            break;
    	        case 4:
    	            System.out.print("\t\t\t\033[1;32mEnter Start Date (YYYY-MM-DD):\033[0m "); 
    	            String startDateStr = scanner.next();
    	            System.out.print("\t\t\t\033[1;32mEnter End Date (YYYY-MM-DD):\033[0m "); 
    	            String endDateStr = scanner.next();
    	            Date startDate = java.sql.Date.valueOf(startDateStr);
    	            Date endDate = java.sql.Date.valueOf(endDateStr);
    	            System.out.println("\t\t\t\u001B[1mSales within Date Range:\u001B[0m");
    	            System.out.println("\t\t\t\u001B[4mID   | Car ID | Customer ID | Sale Date          | Amount   \u001B[0m");

    	            for (Sale sale : saleService.getSalesByDateRange(startDate, endDate)) {
    	                System.out.printf("\t\t\t%-5d| %-7d| %-12d| %-20s| $%-8.2f%n",
    	                        sale.getId(),
    	                        sale.getCarId(),
    	                        sale.getCustomerId(),
    	                        sale.getSaleDate(),
    	                        sale.getAmount());
    	            }
    	            break;
    	        default:
    	            System.out.println("\t\t\t\033[1;31mInvalid choice. Please try again.\033[0m"); 
    	            break;
    	    }
    	} catch (Exception e) {
    	    System.out.println("\t\t\t\033[1;31mFailed to retrieve sales report: " + e.getMessage() + "\033[0m"); 
    	}

    }
    
    private static void updateCar(InventoryService inventoryService) {
    try {
        System.out.println("\t\t\t\033[1;34mEnter car ID to update:\033[0m"); 
        int carId = scanner.nextInt();
        Car car = inventoryService.getCarById(carId);
        if (car == null) {
            System.out.println("\t\t\t\033[1;31mCar with ID " + carId + " not found.\033[0m"); 
            return;
        }
        System.out.println("\t\t\t\033[1;34mChoose the field to update:\033[0m"); 
        System.out.println("\t\t\t\033[1;33m1. Make\033[0m"); 
        System.out.println("\t\t\t\033[1;33m2. Model\033[0m"); 
        System.out.println("\t\t\t\033[1;33m3. Year\033[0m"); 
        System.out.println("\t\t\t\033[1;33m4. Price\033[0m"); 
        System.out.println("\t\t\t\033[1;33m5. Quantity\033[0m"); 
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("\t\t\t\033[1;32mEnter new make:\033[0m "); 
                car.setMake(scanner.next());
                break;
            case 2:
                System.out.print("\t\t\t\033[1;32mEnter new model:\033[0m "); 
                car.setModel(scanner.next());
                break;
            case 3:
                System.out.print("\t\t\t\033[1;32mEnter new year:\033[0m "); 
                car.setYear(scanner.nextInt());
                break;
            case 4:
                System.out.print("\t\t\t\033[1;32mEnter new price:\033[0m "); 
                car.setPrice(scanner.nextDouble());
                break;
            case 5:
                System.out.print("\t\t\t\033[1;32mEnter new quantity:\033[0m "); 
                car.setQuantity(scanner.nextInt());
                break;
            default:
                System.out.println("\t\t\t\033[1;31mInvalid choice.\033[0m"); 
                return;
        }
        inventoryService.updateCar(car);
        System.out.println("\t\t\t\033[1;32mCar details updated successfully!\033[0m"); 
    } catch (Exception e) {
        System.out.println("\t\t\t\033[1;31mFailed to update car details: " + e.getMessage() + "\033[0m"); 
    }
}

    private static void showAllCustomerPurchases(CustomerPurchaseService customerPurchase) {
        try {
            ArrayList<CustomerPurchase> allCustomersPurchase = customerPurchase.getAllCustomerPurchases();
            System.out.println("\t\t\t\033[1;34mAll Customer Purchases:\033[0m"); 
            // Print table header
            System.out.println("\t\t\t\033[1;34m---------------------------------------------------------------\033[0m");
            System.out.println("\t\t\t\033[1;34m|   ID   |  Customer ID  |    Car ID    |     Date     | Amount |\033[0m");
            System.out.println("\t\t\t\033[1;34m---------------------------------------------------------------\033[0m");

 
            for (CustomerPurchase c : allCustomersPurchase) {
                System.out.printf("\t\t\t\033[1;34m|\033[0m \033[1;37m%-5d\033[0m \033[1;34m|\033[0m \033[1;37m%-13d\033[0m \033[1;34m|\033[0m \033[1;37m%-12d\033[0m \033[1;34m|\033[0m \033[1;37m%-12s\033[0m \033[1;34m|\033[0m \033[1;37m%-6.2f\033[0m \033[1;34m|\033[0m\n",
                        c.getId(), c.getCustomerId(), c.getCarId(), c.getPurchaseDate(), c.getPurchaseAmount());
            }

            System.out.println("\t\t\t\033[1;34m---------------------------------------------------------------\033[0m");
        } catch (Exception e) {
            System.out.println("\t\t\t\033[1;31mFailed to retrieve customer purchases: " + e.getMessage() + "\033[0m"); 
        }
    }



}
