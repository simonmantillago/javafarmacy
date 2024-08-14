package com.javafarmacy.customer.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.customer.domain.entity.Customer;
import com.javafarmacy.customer.domain.service.CustomerService;



public class CustomerRepository implements CustomerService {
    private Connection connection;

    public CustomerRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCustomer(Customer customer) {
        try {
            String query = "INSERT INTO customer (idcustomer,namecustomer,lastnamecustomer,codecitycustomer,emailcustomer,birthDate,lon,latitud) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getIdcustomer());
            ps.setString(2, customer.getNamecustomer());
            ps.setString(3, customer.getLastnamecustomer());
            ps.setString(4, customer.getCodecitycustomer());
            ps.setString(5, customer.getEmailcustomer());
            ps.setString(6, customer.getBirthDate());
            ps.setFloat(7, customer.getLon());
            ps.setFloat(8, customer.getLatitud());
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer added successfully!");
            } else {
                System.out.println("Customer addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
        public void updateCustomer(Customer customer) {
            String query = "UPDATE customer SET namecustomer = ?, lastnamecustomer = ?, codecitycustomer = ?, emailcustomer = ?, birthDate = ?, lon = ?, latitud = ? WHERE idcustomer = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, customer.getNamecustomer());
                ps.setString(2, customer.getLastnamecustomer());
                ps.setString(3, customer.getCodecitycustomer());
                ps.setString(4, customer.getEmailcustomer());
                ps.setString(5, customer.getBirthDate());
                ps.setFloat(6, customer.getLon());
                ps.setFloat(7, customer.getLatitud());
                ps.setString(8, customer.getIdcustomer());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Customer updated successfully!");
                } else {
                    System.out.println("Customer update failed!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Customer deleteCustomer(String id) {
            Customer customer = null;
            String selectQuery = "SELECT * FROM customer WHERE idcustomer = ?";
            String deleteQuery = "DELETE FROM customer WHERE idcustomer = ?";
            
            try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
                PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
                
                // First, fetch the customer
                selectPs.setString(1, id);
                try (ResultSet rs = selectPs.executeQuery()) {
                    if (rs.next()) {
                        customer = new Customer(
                            rs.getString("idcustomer"),
                            rs.getString("namecustomer"),
                            rs.getString("lastnamecustomer"),
                            rs.getString("codecitycustomer"),
                            rs.getString("emailcustomer"),
                            rs.getString("birthDate"),
                            rs.getFloat("lon"),
                            rs.getFloat("latitud")
                        );
                    }
                }
                
                // If customer exists, delete it
                if (customer != null) {
                    deletePs.setString(1, id);
                    int rowsAffected = deletePs.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Customer deleted successfully!");
                        return customer;
                    }
                }
                
                System.out.println("Customer deletion failed. Customer not found.");
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    @Override
        public Optional<Customer> findCustomerById(String id) {
            String query = "SELECT idcustomer,namecustomer,lastnamecustomer,codecitycustomer,emailcustomer,birthdate,lon,latitud FROM customer WHERE idcustomer = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Customer customer = new Customer(
                            rs.getString("idcustomer"),
                            rs.getString("namecustomer"),
                            rs.getString("lastnamecustomer"),
                            rs.getString("codecitycustomer"),
                            rs.getString("emailcustomer"),
                            rs.getString("birthDate"),
                            rs.getFloat("lon"),
                            rs.getFloat("latitud")
                        );
                        return Optional.of(customer);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }


    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT idcustomer,namecustomer,lastnamecustomer,codecitycustomer,emailcustomer,birthdate,lon,latitud FROM customer";
        
        try (PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getString("idcustomer"),
                    rs.getString("namecustomer"),
                    rs.getString("lastnamecustomer"),
                    rs.getString("codecitycustomer"),
                    rs.getString("emailcustomer"),
                    rs.getString("birthDate"),
                    rs.getFloat("lon"),
                    rs.getFloat("latitud")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }
}
