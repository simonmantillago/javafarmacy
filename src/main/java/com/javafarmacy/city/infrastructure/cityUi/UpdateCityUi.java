package com.javafarmacy.city.infrastructure.cityUi;

import java.awt.*;
import java.util.Optional;
import javax.swing.*;

import com.javafarmacy.city.application.FindCityByIdUseCase;
import com.javafarmacy.city.application.UpdateCityUseCase;
import com.javafarmacy.city.domain.entity.City;

public class UpdateCityUi extends JFrame {
    private final UpdateCityUseCase updateCityUseCase;
    private final FindCityByIdUseCase findCityByIdUseCase;  // Added findCityByIdUseCase field
    private final CityUiController cityUiController;

    private JTextField jTextField1; // City Code
    private JTextField jTextField2; // City Name
    private JTextField jTextField3; // City Name
    
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back
    private JButton jButton4; // Find

    public UpdateCityUi(UpdateCityUseCase updateCityUseCase, FindCityByIdUseCase findCityByIdUseCase, CityUiController cityUiController) {
        this.updateCityUseCase = updateCityUseCase;
        this.findCityByIdUseCase = findCityByIdUseCase;  // Initialized findCityByIdUseCase
        this.cityUiController = cityUiController;
    }

    public void frmUpdateCity() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update City");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Update City");
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
        jButton2.addActionListener(e -> updateCity());
        jButton3.addActionListener(e -> {
            dispose();
            cityUiController.showCrudOptions();
        });
        jButton4.addActionListener(e -> findCity());

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("City Code:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(jButton4, 2, 0, 2);
        addComponent(new JLabel("City Name:"), 3, 0);
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

    private void updateCity() {
        try {
            City city = new City();
            city.setCodecity(jTextField1.getText());
            city.setNamecity(jTextField2.getText());
            city.setCodereg(jTextField3.getText());

            updateCityUseCase.execute(city);
            JOptionPane.showMessageDialog(this, "City updated successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findCity() {
        String codeToUpdate = jTextField1.getText();
        Optional<City> cityToUpdate = findCityByIdUseCase.execute(codeToUpdate);
    
        if (cityToUpdate.isPresent()) {
            City foundCity = cityToUpdate.get();
            jTextField1.setText(foundCity.getCodecity());
            jTextField2.setText(foundCity.getNamecity());
            jTextField3.setText(foundCity.getCodereg());
            jTextField1.setEditable(false);
            showComponents();
            revalidate(); // Asegura que el layout se actualice
            repaint(); // Redibuja la ventana
        } else {
            JOptionPane.showMessageDialog(this, "City not found!", "Error", JOptionPane.ERROR_MESSAGE);
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
