package com.javafarmacy.country.infrastructure.CountryUi;

import java.util.Optional;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.country.application.FindCountryByIdUseCase;
import com.javafarmacy.country.domain.entity.Country;

public class FindCountryByIdUi extends JFrame {
    private final FindCountryByIdUseCase findCountryByIdUseCase;
    private final CountryUiController countryUiController;
    private JTextField txtId;
    private JTextArea resultArea;



    public FindCountryByIdUi(FindCountryByIdUseCase findCountryByIdUseCase, CountryUiController countryUiController) {
        this.findCountryByIdUseCase = findCountryByIdUseCase;
        this.countryUiController = countryUiController;
    }

    public void showFindCountry() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find Country");
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

        JLabel titleLabel = new JLabel("Find Country");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Country ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnFind = new JButton("Find");
        btnFind.addActionListener(e -> findCountry());
        addComponent(btnFind, 2, 0, 2);

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

    private void findCountry() {
        String countryId = txtId.getText();
        Optional<Country> countryOpt = findCountryByIdUseCase.execute(countryId);
        if (countryOpt.isPresent()) {
            Country country = countryOpt.get();
            String message = String.format(
                "Country found:\n\n" +
                "ID: %s\n" +
                "Country Name: %s\n",
                country.getCodeCountry(),
                country.getNameCountry()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Country not found!");
        }
    }
}
