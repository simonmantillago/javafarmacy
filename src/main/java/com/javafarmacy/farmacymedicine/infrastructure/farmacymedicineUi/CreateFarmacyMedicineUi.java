package com.javafarmacy.farmacymedicine.infrastructure.farmacymedicineUi;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.javafarmacy.farmacymedicine.application.CreateFarmacyMedicineUseCase;
import com.javafarmacy.farmacymedicine.domain.entity.FarmacyMedicine;

public class CreateFarmacyMedicineUi extends JFrame{
private final CreateFarmacyMedicineUseCase createFarmacyMedicineUseCase;
    private final FarmacyMedicineUiController farmacyMedicineUiController; // Use FarmacyMedicineUiController if that's the intended class

    private JTextField jTextField1; // FarmacyMedicine Code
    private JTextField jTextField2; // FarmacyMedicine Name
    private JTextField jTextField3; // country code
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back

    public CreateFarmacyMedicineUi(CreateFarmacyMedicineUseCase createFarmacyMedicineUseCase, FarmacyMedicineUiController farmacyMedicineUiController) { // Use FarmacyMedicineUiController
        this.createFarmacyMedicineUseCase = createFarmacyMedicineUseCase;
        this.farmacyMedicineUiController = farmacyMedicineUiController; // Use FarmacyMedicineUiController
    }

    public void frmRegFarmacyMedicine() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create FarmacyMedicine");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Create FarmacyMedicine");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> saveFarmacyMedicine());
        jButton3.addActionListener(e -> {
            dispose();
            farmacyMedicineUiController.showCrudOptions(); // Adjusted to call the method in FarmacyMedicineUiController
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Increased spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Farmacy Code:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("Medicine Name:"), 2, 0);
        addComponent(jTextField2, 2, 1);
        addComponent(new JLabel("Price:"), 3, 0);
        addComponent(jTextField3, 3, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setLocationRelativeTo(null);
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
        gbc.insets = new Insets(5, 5, 5, 5); // Spacing between components
        add(component, gbc);
    }

    private void saveFarmacyMedicine() {
        try {
            FarmacyMedicine farmacyMedicine = new FarmacyMedicine();
            farmacyMedicine.setIdfarmacy(Integer.parseInt(jTextField1.getText()));
            farmacyMedicine.setIdmedicine(Integer.parseInt(jTextField2.getText()));
            farmacyMedicine.setPrice(Float.parseFloat(jTextField3.getText()));

            createFarmacyMedicineUseCase.execute(farmacyMedicine); // Corrected from "customer" to "farmacyMedicine"
            JOptionPane.showMessageDialog(this, "FarmacyMedicine added successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
    }
}
