package com.javafarmacy.region.infrastructure.regionUi;

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

import com.javafarmacy.region.application.CreateRegionUseCase;
import com.javafarmacy.region.domain.entity.Region;

public class CreateRegionUi extends JFrame{
private final CreateRegionUseCase createRegionUseCase;
    private final RegionUiController regionUiController; // Use RegionUiController if that's the intended class

    private JTextField jTextField1; // Region Code
    private JTextField jTextField2; // Region Name
    private JTextField jTextField3; // country code
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back

    public CreateRegionUi(CreateRegionUseCase createRegionUseCase, RegionUiController regionUiController) { // Use RegionUiController
        this.createRegionUseCase = createRegionUseCase;
        this.regionUiController = regionUiController; // Use RegionUiController
    }

    public void frmRegRegion() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Region");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Create Region");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> saveRegion());
        jButton3.addActionListener(e -> {
            dispose();
            regionUiController.showCrudOptions(); // Adjusted to call the method in RegionUiController
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Increased spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Region Code:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("Region Name:"), 2, 0);
        addComponent(jTextField2, 2, 1);
        addComponent(new JLabel("Country Code:"), 3, 0);
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

    private void saveRegion() {
        try {
            Region region = new Region();
            region.setCodeRegion(jTextField1.getText());
            region.setNameRegion(jTextField2.getText());
            region.setCodeCountryReg(jTextField3.getText());

            createRegionUseCase.execute(region); // Corrected from "customer" to "region"
            JOptionPane.showMessageDialog(this, "Region added successfully!");
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
