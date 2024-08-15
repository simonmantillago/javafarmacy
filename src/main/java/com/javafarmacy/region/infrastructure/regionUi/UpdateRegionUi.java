package com.javafarmacy.region.infrastructure.regionUi;

import java.awt.*;
import java.util.Optional;
import javax.swing.*;

import com.javafarmacy.region.application.FindRegionByIdUseCase;
import com.javafarmacy.region.application.UpdateRegionUseCase;
import com.javafarmacy.region.domain.entity.Region;

public class UpdateRegionUi extends JFrame {
    private final UpdateRegionUseCase updateRegionUseCase;
    private final FindRegionByIdUseCase findRegionByIdUseCase;  // Added findRegionByIdUseCase field
    private final RegionUiController regionUiController;

    private JTextField jTextField1; // Region Code
    private JTextField jTextField2; // Region Name
    private JTextField jTextField3; // Region Name
    
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back
    private JButton jButton4; // Find

    public UpdateRegionUi(UpdateRegionUseCase updateRegionUseCase, FindRegionByIdUseCase findRegionByIdUseCase, RegionUiController regionUiController) {
        this.updateRegionUseCase = updateRegionUseCase;
        this.findRegionByIdUseCase = findRegionByIdUseCase;  // Initialized findRegionByIdUseCase
        this.regionUiController = regionUiController;
    }

    public void frmUpdateRegion() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update Region");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Update Region");
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
        jButton2.addActionListener(e -> updateRegion());
        jButton3.addActionListener(e -> {
            dispose();
            regionUiController.showCrudOptions();
        });
        jButton4.addActionListener(e -> findRegion());

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Region Code:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(jButton4, 2, 0, 2);
        addComponent(new JLabel("Region Name:"), 3, 0);
        addComponent(jTextField2, 3, 1);
        addComponent(new JLabel("Country Code:"), 4, 0);
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

    private void updateRegion() {
        try {
            Region region = new Region();
            region.setCodeRegion(jTextField1.getText());
            region.setNameRegion(jTextField2.getText());
            region.setCodeCountryReg(jTextField3.getText());

            updateRegionUseCase.execute(region);
            JOptionPane.showMessageDialog(this, "Region updated successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findRegion() {
        String codeToUpdate = jTextField1.getText();
        Optional<Region> regionToUpdate = findRegionByIdUseCase.execute(codeToUpdate);
    
        if (regionToUpdate.isPresent()) {
            Region foundRegion = regionToUpdate.get();
            jTextField1.setText(foundRegion.getCodeRegion());
            jTextField2.setText(foundRegion.getNameRegion());
            jTextField3.setText(foundRegion.getCodeCountryReg());
            jTextField1.setEditable(false);
            showComponents();
            revalidate(); // Asegura que el layout se actualice
            repaint(); // Redibuja la ventana
        } else {
            JOptionPane.showMessageDialog(this, "Region not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField1.setEditable(true);
        hideComponents();
    }

    private void hideComponents() {
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);

    }

    private void showComponents() {
        jTextField2.setVisible(true);
        jTextField3.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
}
