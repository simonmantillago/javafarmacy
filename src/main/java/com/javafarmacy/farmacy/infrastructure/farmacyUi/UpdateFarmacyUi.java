package com.javafarmacy.farmacy.infrastructure.farmacyUi;


import javax.swing.*;

import com.javafarmacy.farmacy.application.FindFarmacyByIdUseCase;
import com.javafarmacy.farmacy.application.UpdateFarmacyUseCase;
import com.javafarmacy.farmacy.domain.entity.Farmacy;

import java.awt.*;
import java.util.Optional;

public class UpdateFarmacyUi extends JFrame {
    private final UpdateFarmacyUseCase updateFarmacyUseCase;
    private final FindFarmacyByIdUseCase findFarmacyByIdUseCase;
    private final FarmacyCrudUi farmacyCrudUi;
    
    // Components
    private JTextField jTextField1; // ID Number
    private JTextField jTextField2; // First Name
    private JTextField jTextField3; // Last Name
    private JTextField jTextField4; // City ID
    private JTextField jTextField5; // Email
    private JTextField jTextField6; // BirthDate
    private JTextField jTextField7; // lon

    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back
    private JButton jButton4; // Find Farmacy by ID

    public UpdateFarmacyUi(FindFarmacyByIdUseCase findFarmacyByIdUseCase, UpdateFarmacyUseCase updateFarmacyUseCase,FarmacyCrudUi farmacyCrudUi) {
        this.findFarmacyByIdUseCase = findFarmacyByIdUseCase;
        this.updateFarmacyUseCase = updateFarmacyUseCase;
        this.farmacyCrudUi = farmacyCrudUi;
    }

    public void frmUpdateFarmacy() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update Farmacy");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Update Farmacy");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();
        jTextField7 = new JTextField();





        

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");
        jButton4 = new JButton("Find");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> updateFarmacy());
        jButton3.addActionListener(e -> {
            dispose();
            farmacyCrudUi.showFrame();
        });
        jButton4.addActionListener(e -> findFarmacy());
        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar el espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame
        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("ID Number:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(jButton4, 2, 0, 2);
        addComponent(new JLabel("Name:"), 3, 0);
        addComponent(jTextField2, 3, 1);
        addComponent(new JLabel("Address:"), 4, 0);
        addComponent(jTextField3, 4, 1);
        addComponent(new JLabel("Longitude:"), 5, 0);
        addComponent(jTextField4, 5, 1);
        addComponent(new JLabel("Latitude:"), 6, 0);
        addComponent(jTextField5, 6, 1);
        addComponent(new JLabel("City Code:"), 7, 0);
        addComponent(jTextField6, 7, 1);
        addComponent(new JLabel("Logo:"), 8, 0);
        addComponent(jTextField7, 8, 1);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 10;
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

    private void updateFarmacy() {
        try {

            Farmacy farmacy = new Farmacy();
            farmacy.setIdfarmacy(Integer.parseInt(jTextField1.getText()));
            farmacy.setNamefarmacy(jTextField2.getText());
            farmacy.setAddressfarmacy(jTextField3.getText());
            farmacy.setLongitude(Float.parseFloat(jTextField4.getText()));
            farmacy.setLatfarmacy(Float.parseFloat(jTextField5.getText()));
            farmacy.setCodecityfarm(jTextField6.getText());
            farmacy.setLogofarmacy(jTextField7.getText());

            updateFarmacyUseCase.execute(farmacy);
            JOptionPane.showMessageDialog(this, "Farmacy updated successfully!");
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
        hideComponents();
    }

    private void findFarmacy() {
        String idToUpdate = jTextField1.getText();
        Optional<Farmacy> farmacyToUpdate = findFarmacyByIdUseCase.execute(idToUpdate);
        if (farmacyToUpdate.isPresent()) {
            Farmacy foundFarmacy = farmacyToUpdate.get();
            try {
                
                jTextField2.setText(foundFarmacy.getNamefarmacy());
                jTextField3.setText(foundFarmacy.getAddressfarmacy());
                jTextField4.setText(Float.toString(foundFarmacy.getLongitude()));
                jTextField5.setText(Float.toString(foundFarmacy.getLatfarmacy()));
                jTextField6.setText(foundFarmacy.getCodecityfarm());
                jTextField7.setText(foundFarmacy.getLogofarmacy());

                jTextField1.setEditable(false);
                showComponents();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Farmacy not found!", "Error", JOptionPane.ERROR_MESSAGE);
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
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
}