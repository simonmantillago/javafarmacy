package com.javafarmacy.region.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.region.domain.entity.Region;
import com.javafarmacy.region.domain.service.RegionService;

public class RegionRepository implements RegionService {
    private Connection connection;
    
    public  RegionRepository(){
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
    public void createRegion(Region region) {
        try {
            String query = "INSERT INTO region (codereg,namereg,codecountry) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, region.getCodeRegion());
            ps.setString(2, region.getNameRegion());
            ps.setString(3, region.getCodeCountryReg());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Region added successfully!");
            } else {
                System.out.println("Region addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
        public void updateRegion(Region region) {
            String query = "UPDATE region SET namereg = ?, codecountry = ? WHERE codereg = ?";
            try(PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1, region.getNameRegion());
                ps.setString(2, region.getCodeCountryReg());
                ps.setString(3, region.getCodeRegion());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Region updated successfully!");
                }else {
                    System.out.println("Region update failed!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }   
        }

    @Override
    public Region deleteRegion(String codeRegion) {
        Region region = null;
        String selectQuery = "SELECT * FROM Region WHERE codereg = ?";
        String deleteQuery = "DELETE FROM Region WHERE codereg = ?";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
            
            // First, fetch the customer
            selectPs.setString(1, codeRegion);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    region = new Region(
                        rs.getString("codereg"),
                        rs.getString("namereg"),
                        rs.getString("codeCountryreg")
                    
                    );
                }
            }
            
            // If customer exists, delete it
            if (region != null) {
                deletePs.setString(1, codeRegion);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Region deleted successfully!");
                    return region;
                }
            }
            
            System.out.println("Region deletion failed. Customer not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }    
    }

    @Override
    public Optional<Region> findRegionById(String codeRegion) {
        String query = "SELECT codereg, nameregion, codecountry  FROM region WHERE coderegion = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeRegion);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Region region = new Region(
                        rs.getString("codecountry"),
                        rs.getString("namecountry"),
                        rs.getString("codecountryreg")
                    );
                    return Optional.of(region);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
