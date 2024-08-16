package com.javafarmacy.medicine.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.javafarmacy.medicine.domain.entity.Medicine;
import com.javafarmacy.medicine.domain.service.MedicineService;

public class MedicineRepository implements MedicineService {
    private Connection connection;
    
    public MedicineRepository(){
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
    public void createMedicine(Medicine medicine) {
        try {
            String query = "INSERT INTO medicine (proceedings,namemedicine,healthregister,description,descriptionshort,codemodeadmin,codeap,codeum,namerol,codelab) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, medicine.getProceedings());
            ps.setString(2, medicine.getNamemedicine());
            ps.setString(3, medicine.getHealthregister());
            ps.setString(4, medicine.getDescription());
            ps.setString(5, medicine.getDescriptionshort());
            ps.setInt(6, medicine.getCodemodeadmin());
            ps.setInt(7, medicine.getCodeap());
            ps.setInt(8, medicine.getCodeum());
            ps.setString(9, medicine.getNamerol());
            ps.setInt(10, medicine.getCodelab());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("medicine added successfully!");
            } else {
                System.out.println("medicine addition failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        String query = "UPDATE medicine SET proceedings = ?,namemedicine = ?,healthregister = ?,description = ?,descriptionshort = ?,codemodeadmin = ?,codeap = ?,codeum = ?,namerol = ?,codelab = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, medicine.getProceedings());
            ps.setString(2, medicine.getNamemedicine());
            ps.setString(3, medicine.getHealthregister());
            ps.setString(4, medicine.getDescription());
            ps.setString(5, medicine.getDescriptionshort());
            ps.setInt(6, medicine.getCodemodeadmin());
            ps.setInt(7, medicine.getCodeap());
            ps.setInt(8, medicine.getCodeum());
            ps.setString(9, medicine.getNamerol());
            ps.setInt(10, medicine.getCodelab());
            ps.setInt(11, medicine.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Medicine updated successfully!");
            }else {
                System.out.println("Medicine update failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }

    @Override
    public Medicine deleteMedicine(String codeMedicine) {
        Medicine medicine = null;
        String selectQuery = "SELECT * FROM Medicine WHERE id = ?";
        String deleteQuery = "DELETE FROM Medicine WHERE id = ?";
        
        try (PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
            
            // First, fetch the customer
            selectPs.setString(1, codeMedicine);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    medicine = new Medicine(
                        rs.getInt("id"),
                        rs.getString("proceedings"),
                        rs.getString("namemedicine"),
                        rs.getString("healthregister"),
                        rs.getString("description"),
                        rs.getString("descriptionshort"),
                        rs.getInt("codemodeadmin"),
                        rs.getInt("codeap"),
                        rs.getInt("codeum"),
                        rs.getString("namerol"),
                        rs.getInt("codelab")
                    );
                }
            }
            
            // If customer exists, delete it
            if (medicine != null) {
                deletePs.setString(1, codeMedicine);
                int rowsAffected = deletePs.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Medicine deleted successfully!");
                    return medicine;
                }
            }
            
            System.out.println("Medicine deletion failed. Customer not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }    
    }

    @Override
    public Optional<Medicine> findMedicineById(String codeMedicine) {
        String query = "SELECT id,proceedings,namemedicine,healthregister,description,descriptionshort,codemodeadmin,codeap,codeum,namerol,codelab  FROM medicine WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeMedicine);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Medicine medicine = new Medicine(
                        rs.getInt("id"),
                        rs.getString("proceedings"),
                        rs.getString("namemedicine"),
                        rs.getString("healthregister"),
                        rs.getString("description"),
                        rs.getString("descriptionshort"),
                        rs.getInt("codemodeadmin"),
                        rs.getInt("codeap"),
                        rs.getInt("codeum"),
                        rs.getString("namerol"),
                        rs.getInt("codelab")
                    );
                    return Optional.of(medicine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }    
}


