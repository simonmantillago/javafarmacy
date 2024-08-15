package com.javafarmacy.unitmeasurement.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.unitmeasurement.domain.entity.UnitMeasurement;
import com.javafarmacy.unitmeasurement.domain.service.UnitMeasurementService;


public class UnitMeasurementRepository implements UnitMeasurementService {
private Connection connection;
    
    public  UnitMeasurementRepository(){
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
    public void createUnitMeasurement(UnitMeasurement unitMeasurement) {
        try {
            String query = "INSERT INTO unitMeasurement (nameum) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, unitMeasurement.getnameum());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("UnitMeasurement added successfully!");
            } else {
                System.out.println("UnitMeasurement addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUnitMeasurement(UnitMeasurement unitMeasurement) {
        String query = "UPDATE unitMeasurement SET nameum = ? WHERE idum = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, unitMeasurement.getnameum());
            ps.setInt(2, unitMeasurement.getIdum());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("UnitMeasurement updated successfully!");
            }else {
                System.out.println("UnitMeasurement update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UnitMeasurement deleteUnitMeasurement(String idum) {
        UnitMeasurement unitMeasurement = null;
        String selectQuery = "SELECT * FROM UnitMeasurement WHERE idum = ?";
        String deleteQuery = "DELETE FROM UnitMeasurement WHERE idum = ?";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
            

            selectPs.setString(1, idum);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    unitMeasurement = new UnitMeasurement(
                        rs.getInt("idum"),
                        rs.getString("nameum")
                    );
                }
            }
            
       
            if (unitMeasurement != null) {
                deletePs.setString(1, idum);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("UnitMeasurement deleted successfully!");
                    return unitMeasurement;
                }
            }
            
            System.out.println("UnitMeasurement deletion failed. Customer not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<UnitMeasurement> findUnitMeasurementById(String idum) {
    String query = "SELECT idum, nameum FROM unitMeasurement WHERE idum = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, idum);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    UnitMeasurement unitMeasurement = new UnitMeasurement(
                        rs.getInt("idum"),
                        rs.getString("nameum")
                    );
                    return Optional.of(unitMeasurement);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
