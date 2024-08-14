package com.javafarmacy.city.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.city.domain.entity.City;
import com.javafarmacy.city.domain.service.CityService;

public class CityRepository implements CityService{
    private Connection connection;
    
    public  CityRepository(){
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
    public void createCity(City city) {
        try {
            String query = "INSERT INTO city (codecity,namecity,codereg) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, city.getCodecity());
            ps.setString(2, city.getNamecity());
            ps.setString(3, city.getCodereg());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("City added successfully!");
            } else {
                System.out.println("City addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCity(City city) {
        String query = "UPDATE city SET namecity = ?, codereg = ? WHERE codecity = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, city.getNamecity());
            ps.setString(2, city.getCodereg());
            ps.setString(3, city.getCodecity());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("city updated successfully!");
            }else {
                System.out.println("city update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    @Override
    public City deleteCity(String codeCity) {
        City city = null;
        String selectQuery = "SELECT * FROM city WHERE codecity = ?";
        String deleteQuery = "DELETE FROM city WHERE codecity = ?";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
            
            // First, fetch the customer
            selectPs.setString(1, codeCity);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    city = new City(
                        rs.getString("codecity"),
                        rs.getString("namecity"),
                        rs.getString("codereg")
                    
                    );
                }
            }
            
            // If customer exists, delete it
            if (city != null) {
                deletePs.setString(1, codeCity);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("City deleted successfully!");
                    return city;
                }
            }
            
            System.out.println("City deletion failed. Customer not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }    
    }

    @Override
    public Optional<City> findCityById(String codeCity) {
        String query = "SELECT codecity, namecity, codereg FROM city WHERE codecity = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeCity);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    City city = new City(
                        rs.getString("codecity"),
                        rs.getString("namecity"),
                        rs.getString("codereg")
                    );
                    return Optional.of(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
