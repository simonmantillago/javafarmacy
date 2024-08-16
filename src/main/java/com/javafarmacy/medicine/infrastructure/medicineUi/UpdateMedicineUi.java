package com.javafarmacy.medicine.infrastructure.medicineUi;


import javax.swing.*;

import com.javafarmacy.medicine.application.FindMedicineByIdUseCase;
import com.javafarmacy.medicine.application.UpdateMedicineUseCase;
import com.javafarmacy.medicine.domain.entity.Medicine;

import java.awt.*;
import java.util.Optional;

public class UpdateMedicineUi extends JFrame {
    private final UpdateMedicineUseCase updateMedicineUseCase;
    private final FindMedicineByIdUseCase findMedicineByIdUseCase;
    private final MedicineCrudUi medicineCrudUi;
    
    // Components
    private JTextField jTextField1;
    private JTextField jTextField2; 
    private JTextField jTextField3; 
    private JTextField jTextField4; 
    private JTextField jTextField5; 
    private JTextField jTextField6;
    private JTextField jTextField7;
    private JTextField jTextField8;
    private JTextField jTextField9;
    private JTextField jTextField10;
    private JTextField jTextField11;
  
    

    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back
    private JButton jButton4; // Find Medicine by ID

    public UpdateMedicineUi(FindMedicineByIdUseCase findMedicineByIdUseCase, UpdateMedicineUseCase updateMedicineUseCase,MedicineCrudUi medicineCrudUi) {
        this.findMedicineByIdUseCase = findMedicineByIdUseCase;
        this.updateMedicineUseCase = updateMedicineUseCase;
        this.medicineCrudUi = medicineCrudUi;
    }

    public void frmUpdateMedicine() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update Medicine");
        setSize(600, 600);

        JLabel jLabel1 = new JLabel("Update Medicine");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();
        jTextField7 = new JTextField();
        jTextField8 = new JTextField();
        jTextField9 = new JTextField();
        jTextField10 = new JTextField();
        jTextField11 = new JTextField();


        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");
        jButton4 = new JButton("Find");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> updateMedicine());
        jButton3.addActionListener(e -> {
            dispose();
            medicineCrudUi.showFrame();
        });
        jButton4.addActionListener(e -> findMedicine());
        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar el espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame
        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Id:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(jButton4, 2, 0, 2);
        addComponent(new JLabel("Proceedings:"), 3, 0);
        addComponent(jTextField2, 3, 1);
        addComponent(new JLabel("Name:"), 4, 0);
        addComponent(jTextField3, 4, 1);
        addComponent(new JLabel("HealthRegister:"), 5, 0);
        addComponent(jTextField4, 5, 1);
        addComponent(new JLabel("Description:"), 6, 0);
        addComponent(jTextField5, 6, 1);
        addComponent(new JLabel("DescriptionShort:"), 7, 0);
        addComponent(jTextField6, 7, 1);
        addComponent(new JLabel("Administration Code:"), 8, 0);
        addComponent(jTextField7, 8, 1);
        addComponent(new JLabel("Component Code:"), 9, 0);
        addComponent(jTextField8, 9, 1);
        addComponent(new JLabel("Unit Code:"), 10, 0);
        addComponent(jTextField9, 10, 1);
        addComponent(new JLabel("Rol Name:"), 11, 0);
        addComponent(jTextField10, 11, 1);
        addComponent(new JLabel("Laboratory Code:"), 12, 0);
        addComponent(jTextField11, 12, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setLocationRelativeTo(null);
    
        // Initially hide all components except ID and Find button
        hideComponents();
    }

    private void addComponent(Component component, int row, int col) {
        addComponent(component, row, col, 1);
    }

    private void addComponent(Component component, int row, int col, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridwidth = width;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(component, gbc);
    }

    private void updateMedicine() {
        try {

            Medicine medicine = new Medicine();
            medicine.setId(Integer.parseInt(jTextField1.getText()));
            medicine.setProceedings(jTextField2.getText());
            medicine.setNamemedicine(jTextField3.getText());
            medicine.setHealthregister(jTextField4.getText());
            medicine.setDescription(jTextField5.getText());
            medicine.setDescriptionshort(jTextField6.getText());
            medicine.setCodemodeadmin(Integer.parseInt(jTextField7.getText()));
            medicine.setCodeap(Integer.parseInt(jTextField8.getText()));
            medicine.setCodeum(Integer.parseInt(jTextField9.getText()));
            medicine.setNamerol(jTextField10.getText());
            medicine.setCodelab(Integer.parseInt(jTextField11.getText()));

            updateMedicineUseCase.execute(medicine);
            JOptionPane.showMessageDialog(this, "Medicine updated successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        hideComponents();
    }

    private void findMedicine() {
        String idToUpdate = jTextField1.getText();
        Optional<Medicine> medicineToUpdate = findMedicineByIdUseCase.execute(idToUpdate);
        if (medicineToUpdate.isPresent()) {
            Medicine foundMedicine = medicineToUpdate.get();
            try {
                
                jTextField2.setText(foundMedicine.getProceedings());
                jTextField3.setText(foundMedicine.getNamemedicine());
                jTextField4.setText(foundMedicine.getHealthregister());
                jTextField5.setText(foundMedicine.getDescription());
                jTextField6.setText(foundMedicine.getDescriptionshort());
                jTextField7.setText(Integer.toString(foundMedicine.getCodemodeadmin()));
                jTextField8.setText(Integer.toString(foundMedicine.getCodeap()));
                jTextField9.setText(Integer.toString(foundMedicine.getCodeum()));
                jTextField10.setText(foundMedicine.getNamerol());
                jTextField11.setText(Integer.toString(foundMedicine.getCodelab()));


                jTextField1.setEditable(false);
                showComponents();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Medicine not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hideComponents() {
        jTextField1.setEditable(true);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jTextField6.setVisible(false);
        jTextField7.setVisible(false);
        jTextField8.setVisible(false);
        jTextField9.setVisible(false);
        jTextField10.setVisible(false);
        jTextField11.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
    }

    private void showComponents() {

        jTextField2.setVisible(true);
        jTextField3.setVisible(true);
        jTextField4.setVisible(true);
        jTextField5.setVisible(true);
        jTextField6.setVisible(true);
        jTextField7.setVisible(true);
        jTextField8.setVisible(true);
        jTextField9.setVisible(true);
        jTextField10.setVisible(true);
        jTextField11.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
}