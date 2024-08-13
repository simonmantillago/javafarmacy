package com.javafarmacy.country.infrastructure.CountryUi;

import java.awt.*;
import java.util.Optional;
import javax.swing.*;

import com.javafarmacy.country.application.UpdateCountryUseCase;
import com.javafarmacy.country.application.FindCountryByIdUseCase;  // Added import for FindCountryByIdUseCase
import com.javafarmacy.country.domain.entity.Country;

public class UpdateCountryUi extends JFrame {
    private final UpdateCountryUseCase updateCountryUseCase;
    private final FindCountryByIdUseCase findCountryByIdUseCase;  // Added findCountryByIdUseCase field
    private final CountryUiController countryUiController;

    private JTextField jTextField1; // Country Code
    private JTextField jTextField2; // Country Name
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back
    private JButton jButton4; // Find

    public UpdateCountryUi(UpdateCountryUseCase updateCountryUseCase, FindCountryByIdUseCase findCountryByIdUseCase, CountryUiController countryUiController) {
        this.updateCountryUseCase = updateCountryUseCase;
        this.findCountryByIdUseCase = findCountryByIdUseCase;  // Initialized findCountryByIdUseCase
        this.countryUiController = countryUiController;
    }

    public void frmUpdateCountry() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update Country");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Update Country");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");
        jButton4 = new JButton("Find");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> updateCountry());
        jButton3.addActionListener(e -> {
            dispose();
            countryUiController.showCrudOptions();
        });
        jButton4.addActionListener(e -> findCountry());

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Country Code:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(jButton4, 2, 0, 2);
        addComponent(new JLabel("Country Name:"), 3, 0);
        addComponent(jTextField2, 3, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 4;
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

    private void updateCountry() {
        try {
            Country country = new Country();
            country.setCodeCountry(jTextField1.getText());
            country.setNameCountry(jTextField2.getText());

            updateCountryUseCase.execute(country);
            JOptionPane.showMessageDialog(this, "Country updated successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findCountry() {
        String codeToUpdate = jTextField1.getText();
        Optional<Country> countryToUpdate = findCountryByIdUseCase.execute(codeToUpdate);
    
        if (countryToUpdate.isPresent()) {
            Country foundCountry = countryToUpdate.get();
            jTextField1.setText(foundCountry.getCodeCountry());
            jTextField2.setText(foundCountry.getNameCountry());
            jTextField1.setEditable(false);
            showComponents();
            revalidate(); // Asegura que el layout se actualice
            repaint(); // Redibuja la ventana
        } else {
            JOptionPane.showMessageDialog(this, "Country not found!", "Error", JOptionPane.ERROR_MESSAGE);
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
        jButton1.setVisible(false);
        jButton2.setVisible(false);
    }

    private void showComponents() {
        jTextField2.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
}
