package com.javafarmacy.activeprinciple.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.activeprinciple.domain.entity.Activeprinciple;
import com.javafarmacy.activeprinciple.domain.service.ActiveprincipleService;

public class ActiveprincipleRepository implements ActiveprincipleService {
    private Connection connection;
    
    public  ActiveprincipleRepository(){
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
    public void createActiveprinciple(Activeprinciple activeprinciple) {
        try {
            String query = "INSERT INTO activeprinciple (nameap) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, activeprinciple.getNameap());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Activeprinciple added successfully!");
            } else {
                System.out.println("Activeprinciple addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateActiveprinciple(Activeprinciple activeprinciple) {
        String query = "UPDATE activeprinciple SET nameap = ? WHERE idap = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, activeprinciple.getNameap());
            ps.setInt(2, activeprinciple.getIdap());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Activeprinciple updated successfully!");
            }else {
                System.out.println("Activeprinciple update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Activeprinciple deleteActiveprinciple(String codeActiveprinciple) {
        Activeprinciple activeprinciple = null;
        String selectQuery = "SELECT * FROM Activeprinciple WHERE idap = ?";
        String deleteQuery = "DELETE FROM Activeprinciple WHERE idap = ?";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
            

            selectPs.setString(1, codeActiveprinciple);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    activeprinciple = new Activeprinciple(
                        rs.getInt("idap"),
                        rs.getString("nameap")
                    );
                }
            }
            
       
            if (activeprinciple != null) {
                deletePs.setString(1, codeActiveprinciple);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Activeprinciple deleted successfully!");
                    return activeprinciple;
                }
            }
            
            System.out.println("Activeprinciple deletion failed. Customer not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Activeprinciple> findActiveprincipleById(String codeActiveprinciple) {
String query = "SELECT idap, nameap FROM activeprinciple WHERE idap = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeActiveprinciple);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Activeprinciple activeprinciple = new Activeprinciple(
                        rs.getInt("idap"),
                        rs.getString("nameap")
                    );
                    return Optional.of(activeprinciple);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
