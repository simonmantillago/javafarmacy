package com.javafarmacy.country.infrastructure.CountryUi;

import javax.swing.*;

import com.javafarmacy.country.application.DeleteCountryUseCase;
import com.javafarmacy.country.domain.entity.Country;

import java.awt.*;

public class DeleteCountryUi extends JFrame {
    private final DeleteCountryUseCase deleteCountryUseCase;
    private final CountryUiController countryUiController;
    private JTextField txtId;
    private JTextArea resultArea;
    
    public DeleteCountryUi(DeleteCountryUseCase deleteCountryUseCase, CountryUiController countryUiController) {
        this.deleteCountryUseCase = deleteCountryUseCase;
        this.countryUiController = countryUiController;
    }

    public void showDeleteCustomer() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Delete Country");
        setSize(500, 500);

        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Delete Country");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Country Code:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteCountry());
        addComponent(btnDelete, 2, 0, 2);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> {
            dispose();
            countryUiController.showCrudOptions();
        });
        addComponent(btnClose, 4, 0, 2);
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

    private void deleteCountry() {
        String countryCode = txtId.getText();
        Country deletedCountry = deleteCountryUseCase.execute(countryCode);

        if (deletedCountry != null) {
            String message = String.format(
                "Country deleted successfully:\n\n" +
                "Code: %s\n" +
                "Name: %s\n",
                deletedCountry.getCodeCountry(),
                deletedCountry.getNameCountry()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Country not found or deletion failed.");
        }
    }
}
