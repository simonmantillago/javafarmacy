package com.javafarmacy.country.infrastructure.CountryUi;

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

import com.javafarmacy.country.application.CreateCountryUseCase;
import com.javafarmacy.country.domain.entity.Country;

public class CreateCountryUi extends JFrame {
    private final CreateCountryUseCase createCountryUseCase;
    private final CountryUiController countryUiController; // Use CountryUiController if that's the intended class

    private JTextField jTextField1; // Country Code
    private JTextField jTextField2; // Country Name
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back

    public CreateCountryUi(CreateCountryUseCase createCountryUseCase, CountryUiController countryUiController) { // Use CountryUiController
        this.createCountryUseCase = createCountryUseCase;
        this.countryUiController = countryUiController; // Use CountryUiController
    }

    public void frmRegCountry() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Country");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Create Country");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> saveCountry());
        jButton3.addActionListener(e -> {
            dispose();
            countryUiController.showCrudOptions(); // Adjusted to call the method in CountryUiController
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Increased spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Country Code:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("Country Name:"), 2, 0);
        addComponent(jTextField2, 2, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 3;
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

    private void saveCountry() {
        try {
            Country country = new Country();
            country.setCodeCountry(jTextField1.getText());
            country.setNameCountry(jTextField2.getText());

            createCountryUseCase.execute(country); // Corrected from "customer" to "country"
            JOptionPane.showMessageDialog(this, "Country added successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");
    }
}
