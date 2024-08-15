package com.javafarmacy.unitmeasurement.infrastructure.unitMesurementUi;

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

import com.javafarmacy.unitmeasurement.application.CreateUnitMeasurementUseCase;
import com.javafarmacy.unitmeasurement.domain.entity.UnitMeasurement;


public class CreateUnitMeasurementUi extends JFrame{
    private final CreateUnitMeasurementUseCase createUnitMeasurementUseCase;
    private final UnitMeasurementUiController unitMeasurementUiController; // Use UnitMeasurementUiController if that's the intended class

    private JTextField jTextField1; // UnitMeasurement Name


    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back

    public CreateUnitMeasurementUi(CreateUnitMeasurementUseCase createUnitMeasurementUseCase, UnitMeasurementUiController unitMeasurementUiController) { // Use UnitMeasurementUiController
        this.createUnitMeasurementUseCase = createUnitMeasurementUseCase;
        this.unitMeasurementUiController = unitMeasurementUiController; // Use UnitMeasurementUiController
    }

    public void frmRegUnitMeasurement() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create UnitMeasurement");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Create UnitMeasurement");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
  
    

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> saveUnitMeasurement());
        jButton3.addActionListener(e -> {
            dispose();
            unitMeasurementUiController.showCrudOptions(); // Adjusted to call the method in UnitMeasurementUiController
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Increased spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("UnitMeasurement Name:"), 1, 0);
        addComponent(jTextField1, 1, 1);



        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 2;
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

    private void saveUnitMeasurement() {
        try {
            UnitMeasurement unitMeasurement = new UnitMeasurement();
            unitMeasurement.setnameum(jTextField1.getText());
           

            createUnitMeasurementUseCase.execute(unitMeasurement); // Corrected from "customer" to "unitMeasurement"
            JOptionPane.showMessageDialog(this, "UnitMeasurement added successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");


    }
}
