package com.javafarmacy.modeadministration.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.modeadministration.domain.entity.ModeAdministration;
import com.javafarmacy.modeadministration.domain.service.ModeAdministrationService;

public class ModeAdministrationRepository implements ModeAdministrationService {
private Connection connection;
    
    public  ModeAdministrationRepository(){
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
    public void createModeAdministration(ModeAdministration modeAdministration) {
        try {
            String query = "INSERT INTO modeAdministration (descriptionmode) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, modeAdministration.getDescriptionmode());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("ModeAdministration added successfully!");
            } else {
                System.out.println("ModeAdministration addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModeAdministration(ModeAdministration modeAdministration) {
        String query = "UPDATE modeAdministration SET descriptionmode = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, modeAdministration.getDescriptionmode());
            ps.setInt(2, modeAdministration.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("ModeAdministration updated successfully!");
            }else {
                System.out.println("ModeAdministration update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ModeAdministration deleteModeAdministration(String codeModeAdministration) {
        ModeAdministration modeAdministration = null;
        String selectQuery = "SELECT * FROM ModeAdministration WHERE id = ?";
        String deleteQuery = "DELETE FROM ModeAdministration WHERE id = ?";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
            

            selectPs.setString(1, codeModeAdministration);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    modeAdministration = new ModeAdministration(
                        rs.getInt("id"),
                        rs.getString("descriptionmode")
                    );
                }
            }
            
       
            if (modeAdministration != null) {
                deletePs.setString(1, codeModeAdministration);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("ModeAdministration deleted successfully!");
                    return modeAdministration;
                }
            }
            
            System.out.println("ModeAdministration deletion failed. Customer not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<ModeAdministration> findModeAdministrationById(String codeModeAdministration) {
    String query = "SELECT id, descriptionmode FROM modeAdministration WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeModeAdministration);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    ModeAdministration modeAdministration = new ModeAdministration(
                        rs.getInt("id"),
                        rs.getString("descriptionmode")
                    );
                    return Optional.of(modeAdministration);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
