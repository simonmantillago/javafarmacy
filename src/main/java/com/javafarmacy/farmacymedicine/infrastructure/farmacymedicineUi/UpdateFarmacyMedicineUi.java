package com.javafarmacy.farmacymedicine.infrastructure.farmacymedicineUi;

import java.awt.*;
import java.util.Optional;
import javax.swing.*;

import com.javafarmacy.farmacymedicine.application.FindFarmacyMedicineByIdUseCase;
import com.javafarmacy.farmacymedicine.application.UpdateFarmacyMedicineUseCase;
import com.javafarmacy.farmacymedicine.domain.entity.FarmacyMedicine;

public class UpdateFarmacyMedicineUi extends JFrame {
    private final UpdateFarmacyMedicineUseCase updateFarmacyMedicineUseCase;
    private final FindFarmacyMedicineByIdUseCase findFarmacyMedicineByIdUseCase;  // Added findFarmacyMedicineByIdUseCase field
    private final FarmacyMedicineUiController farmacyMedicineUiController;

    private JTextField jTextField1; // FarmacyMedicine Code
    private JTextField jTextField2; // FarmacyMedicine Name
    private JTextField jTextField3; // FarmacyMedicine Name
    
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back
    private JButton jButton4; // Find

    public UpdateFarmacyMedicineUi(UpdateFarmacyMedicineUseCase updateFarmacyMedicineUseCase, FindFarmacyMedicineByIdUseCase findFarmacyMedicineByIdUseCase, FarmacyMedicineUiController farmacyMedicineUiController) {
        this.updateFarmacyMedicineUseCase = updateFarmacyMedicineUseCase;
        this.findFarmacyMedicineByIdUseCase = findFarmacyMedicineByIdUseCase;  // Initialized findFarmacyMedicineByIdUseCase
        this.farmacyMedicineUiController = farmacyMedicineUiController;
    }

    public void frmUpdateFarmacyMedicine() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update FarmacyMedicine");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Update FarmacyMedicine");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");
        jButton4 = new JButton("Find");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> updateFarmacyMedicine());
        jButton3.addActionListener(e -> {
            dispose();
            farmacyMedicineUiController.showCrudOptions();
        });
        jButton4.addActionListener(e -> findFarmacyMedicine());

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Farmacy Code:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("Medicine Name:"), 2, 0);
        addComponent(jTextField2, 2, 1);
        addComponent(jButton4, 3, 0, 2);
        addComponent(new JLabel("Price:"), 4, 0);
        addComponent(jTextField3, 4, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setLocationRelativeTo(null);

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

    private void updateFarmacyMedicine() {
        try {
            FarmacyMedicine farmacyMedicine = new FarmacyMedicine();
            farmacyMedicine.setIdfarmacy(Integer.parseInt(jTextField1.getText()));
            farmacyMedicine.setIdmedicine(Integer.parseInt(jTextField2.getText()));
            farmacyMedicine.setPrice(Float.parseFloat(jTextField3.getText()));

            updateFarmacyMedicineUseCase.execute(farmacyMedicine);
            JOptionPane.showMessageDialog(this, "FarmacyMedicine updated successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findFarmacyMedicine() {
        String codeToUpdateFarmacy = jTextField1.getText();
        String codeToUpdateMedicine = jTextField2.getText();
        Optional<FarmacyMedicine> farmacyMedicineToUpdate = findFarmacyMedicineByIdUseCase.execute(codeToUpdateFarmacy,codeToUpdateMedicine);
    
        if (farmacyMedicineToUpdate.isPresent()) {
            FarmacyMedicine foundFarmacyMedicine = farmacyMedicineToUpdate.get();
            jTextField1.setText(String.valueOf(foundFarmacyMedicine.getIdfarmacy()));
            jTextField2.setText(String.valueOf(foundFarmacyMedicine.getIdmedicine()));
            jTextField3.setText(String.valueOf(foundFarmacyMedicine.getPrice()));
            jTextField1.setEditable(false);
            jTextField2.setEditable(false);
            showComponents();
            revalidate(); // Asegura que el layout se actualice
            repaint(); // Redibuja la ventana
        } else {
            JOptionPane.showMessageDialog(this, "FarmacyMedicine not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField1.setEditable(true);
        jTextField2.setEditable(true);
        hideComponents();
    }

    private void hideComponents() {
        jTextField3.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);

    }

    private void showComponents() {
 
        jTextField3.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
}
