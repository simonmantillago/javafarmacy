package com.javafarmacy.farmacy.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.farmacy.domain.entity.Farmacy;
import com.javafarmacy.farmacy.domain.service.FarmacyService;

public class FarmacyRepository implements FarmacyService{
    private Connection connection;

    public FarmacyRepository() {
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
    public void createFarmacy(Farmacy farmacy) {
try {
            String query = "INSERT INTO farmacy (namefarmacy,adressfarmacy,longitude,latfarmacy,codecityfarmacy,logofarmacy) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, farmacy.getNamefarmacy());
            ps.setString(2, farmacy.getAddressfarmacy());
            ps.setFloat(3, farmacy.getLongitude());
            ps.setFloat(4, farmacy.getLatfarmacy());
            ps.setString(5, farmacy.getCodecityfarm());
            ps.setString(6, farmacy.getLogofarmacy());
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Farmacy added successfully!");
            } else {
                System.out.println("Farmacy addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFarmacy(Farmacy farmacy) {
        String query = "UPDATE farmacy SET namefarmacy = ?, adressfarmacy = ?, longitude = ?, latfarmacy = ?, codecityfarmacy = ?, logofarmacy = ? WHERE idfarmacy = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, farmacy.getNamefarmacy());
            ps.setString(2, farmacy.getAddressfarmacy());
            ps.setFloat(3, farmacy.getLongitude());
            ps.setFloat(4, farmacy.getLatfarmacy());
            ps.setString(5, farmacy.getCodecityfarm());
            ps.setString(6, farmacy.getLogofarmacy());
            ps.setInt(7, farmacy.getIdfarmacy());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Farmacy updated successfully!");
            } else {
                System.out.println("Farmacy update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Farmacy deleteFarmacy(String codeFarmacy) {
        Farmacy farmacy = null;
            String selectQuery = "SELECT * FROM farmacy WHERE idfarmacy = ?";
            String deleteQuery = "DELETE FROM farmacy WHERE idfarmacy = ?";
            
            try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
                PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
                
                // First, fetch the farmacy
                selectPs.setString(1, codeFarmacy);
                try (ResultSet rs = selectPs.executeQuery()) {
                    if (rs.next()) {
                        farmacy = new Farmacy(
                            rs.getInt("idfarmacy"),
                            rs.getString("namefarmacy"),
                            rs.getString("adressfarmacy"),
                            rs.getFloat("longitude"),
                            rs.getFloat("latfarmacy"),
                            rs.getString("codecityfarmacy"),
                            rs.getString("logofarmacy")
                        );
                    }
                }
                
                // If farmacy exists, delete it
                if (farmacy != null) {
                    deletePs.setString(1, codeFarmacy);
                    int rowsAffected = deletePs.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Farmacy deleted successfully!");
                        return farmacy;
                    }
                }
                
                System.out.println("Farmacy deletion failed. Farmacy not found.");
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public Optional<Farmacy> findFarmacyById(String codeFarmacy) {
            String query = "SELECT idfarmacy,namefarmacy,adressfarmacy,longitude,latfarmacy,codecityfarmacy,logofarmacy FROM farmacy WHERE idfarmacy = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, codeFarmacy);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Farmacy farmacy = new Farmacy(
                            rs.getInt("idfarmacy"),
                            rs.getString("namefarmacy"),
                            rs.getString("adressfarmacy"),
                            rs.getFloat("longitude"),
                            rs.getFloat("latfarmacy"),
                            rs.getString("codecityfarmacy"),
                            rs.getString("logofarmacy")
                        );
                        return Optional.of(farmacy);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
    }
}
