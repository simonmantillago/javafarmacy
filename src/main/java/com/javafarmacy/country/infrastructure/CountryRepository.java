package com.javafarmacy.country.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.country.domain.entity.Country;
import com.javafarmacy.country.domain.service.CountryService;

public class CountryRepository implements CountryService {
    private Connection connection;
    
    public  CountryRepository(){
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
    public void createCountry(Country country) {
        try {
            String query = "INSERT INTO country (codeCountry,nameCountry) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, country.getCodeCountry());
            ps.setString(2, country.getNameCountry());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Country added successfully!");
            } else {
                System.out.println("Country addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCountry(Country country) {
        String query = "UPDATE country SET codeCountry = ?, nameCountry = ? WHERE codecountry = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, country.getCodeCountry());
            ps.setString(2, country.getNameCountry());
            ps.setString(3, country.getCodeCountry());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Country updated successfully!");
            }else {
                System.out.println("Country update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Country deleteCountry(String id) {
        Country country = null;
        String selectQuery = "SELECT * FROM Country WHERE codeCountry = ?";
        String deleteQuery = "DELETE FROM Country WHERE codeCountry = ?";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
            
            // First, fetch the customer
            selectPs.setString(1, id);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    country = new Country(
                        rs.getString("codeCountry"),
                        rs.getString("nameCountry")
                    );
                }
            }
            
            // If customer exists, delete it
            if (country != null) {
                deletePs.setString(1, id);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Country deleted successfully!");
                    return country;
                }
            }
            
            System.out.println("Country deletion failed. Customer not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Optional<Country> findCountryById(String codeCountry) {
        String query = "SELECT codecountry, namecountry FROM country WHERE codecountry = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeCountry);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Country country = new Country(
                        rs.getString("codecountry"),
                        rs.getString("namecountry")
                    );
                    return Optional.of(country);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
