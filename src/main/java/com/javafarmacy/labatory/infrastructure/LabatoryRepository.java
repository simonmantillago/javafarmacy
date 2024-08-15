package com.javafarmacy.labatory.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.labatory.domain.entity.Labatory;
import com.javafarmacy.labatory.domain.service.LabatoryService;

public class LabatoryRepository implements LabatoryService {
    private Connection connection;
    
    public LabatoryRepository(){
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
    public void createLabatory(Labatory labatory) {
try {
            String query = "INSERT INTO labatory (namelab,codecityreg) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, labatory.getNamelab());
            ps.setString(2, labatory.getCodecityreg());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Labatory added successfully!");
            } else {
                System.out.println("Labatory addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLabatory(Labatory labatory) {
        String query = "UPDATE labatory SET namelab = ?, codecityreg = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, labatory.getNamelab());
            ps.setString(2, labatory.getCodecityreg());
            ps.setInt(3, labatory.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Labatory updated successfully!");
            }else {
                System.out.println("Labatory update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    @Override
    public Labatory deleteLabatory(String codelabatory) {
        Labatory labatory = null;
        String selectQuery = "SELECT * FROM Labatory WHERE id = ?";
        String deleteQuery = "DELETE FROM Labatory WHERE id = ?";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
            
            // First, fetch the customer
            selectPs.setString(1, codelabatory);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    labatory = new Labatory(
                        rs.getString("namelab"),
                        rs.getString("codecityreg"),
                        rs.getInt("id")
                    
                    );
                }
            }
            
            // If customer exists, delete it
            if (labatory != null) {
                deletePs.setString(1, codelabatory);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Labatory deleted successfully!");
                    return labatory;
                }
            }
            
            System.out.println("Labatory deletion failed. Customer not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }    
    }

    @Override
    public Optional<Labatory> findLabatoryById(String codelabatory) {
        String query = "SELECT id, namelab, codecityreg  FROM labatory WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codelabatory);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Labatory labatory = new Labatory(
                        rs.getString("namelab"),
                        rs.getString("codecityreg"), 
                        rs.getInt("id")
                    );
                    return Optional.of(labatory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }    
}


