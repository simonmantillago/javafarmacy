package com.javafarmacy.farmacymedicine.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.farmacymedicine.domain.entity.FarmacyMedicine;
import com.javafarmacy.farmacymedicine.domain.service.FarmacyMedicineService;

public class FarmacyMedicineRepository implements FarmacyMedicineService {
    private Connection connection;
    
    public  FarmacyMedicineRepository(){
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
    public void createFarmacyMedicine(FarmacyMedicine farmacyMedicine) {
        try {
            String query = "INSERT INTO farmacyMedicine (idfarmacy,idmedicine,price) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, farmacyMedicine.getIdfarmacy());
            ps.setInt(2, farmacyMedicine.getIdmedicine());
            ps.setFloat(3, farmacyMedicine.getPrice());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("FarmacyMedicine added successfully!");
            } else {
                System.out.println("FarmacyMedicine addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFarmacyMedicine(FarmacyMedicine farmacyMedicine) {
       String query = "UPDATE farmacyMedicine SET price = ? WHERE idfarmacy = ? AND idmedicine = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setFloat(1, farmacyMedicine.getPrice());
            ps.setInt(2, farmacyMedicine.getIdfarmacy());
            ps.setInt(3, farmacyMedicine.getIdmedicine());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("FarmacyMedicine updated successfully!");
            }else {
                System.out.println("FarmacyMedicine update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FarmacyMedicine deleteFarmacyMedicine(String codeFarmacy, String codeMedicine ) {
        FarmacyMedicine farmacyMedicine = null;
        String selectQuery = "SELECT * FROM FarmacyMedicine WHERE idfarmacy = ? AND idmedicine = ?";
        String deleteQuery = "DELETE FROM FarmacyMedicine WHERE idfarmacy = ? AND idmedicine = ?";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
            
            // First, fetch the customer
            selectPs.setString(1, codeFarmacy);
            selectPs.setString(2, codeMedicine);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    farmacyMedicine = new FarmacyMedicine(
                        rs.getInt("idfarmacy"),
                        rs.getInt("idmedicine"),
                        rs.getFloat("price")
                    );
                }
            }
            
            // If customer exists, delete it
            if (farmacyMedicine != null) {
                deletePs.setString(1, codeFarmacy);
                deletePs.setString(2, codeMedicine);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("FarmacyMedicine deleted successfully!");
                    return farmacyMedicine;
                }
            }
            
            System.out.println("FarmacyMedicine deletion failed. Customer not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<FarmacyMedicine> findFarmacyMedicineById(String codeFarmacy, String codeMedicine) {
        String query = "SELECT idfarmacy, idmedicine, price FROM farmacyMedicine WHERE idfarmacy = ? AND idmedicine = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeFarmacy);
            ps.setString(2, codeMedicine);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    FarmacyMedicine farmacyMedicine = new FarmacyMedicine(
                        rs.getInt("idfarmacy"),
                        rs.getInt("idmedicine"),
                        rs.getFloat("price") // Asegúrate de que esta columna está siendo seleccionada y obtenida
                    );
                    return Optional.of(farmacyMedicine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
