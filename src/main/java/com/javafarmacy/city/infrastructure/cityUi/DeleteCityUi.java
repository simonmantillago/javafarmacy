package com.javafarmacy.city.infrastructure.cityUi;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.city.application.DeleteCityUseCase;
import com.javafarmacy.city.domain.entity.City;

public class DeleteCityUi extends JFrame {
    private final DeleteCityUseCase deleteCityUseCase;
    private final CityUiController cityUiController;
    private JTextField txtId;
    private JTextArea resultArea;
    
    public DeleteCityUi(DeleteCityUseCase deleteCityUseCase, CityUiController cityUiController) {
        this.deleteCityUseCase = deleteCityUseCase;
        this.cityUiController = cityUiController;
    }

    public void showDeleteCity() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Delete City");
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

        JLabel titleLabel = new JLabel("Delete City");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter City Code:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteCity());
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
            cityUiController.showCrudOptions();
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

    private void deleteCity() {
        String cityCode = txtId.getText();
        City deletedCity = deleteCityUseCase.execute(cityCode);

        if (deletedCity != null) {
            String message = String.format(
                "City deleted successfully:\n\n" +
                "Code: %s\n" +
                "Name: %s\n"+
                "Country Code: %s\n",
                deletedCity.getCodecity(),
                deletedCity.getNamecity(),
                deletedCity.getCodereg()

            );
            resultArea.setText(message);
        } else {
            resultArea.setText("City not found or deletion failed.");
        }
    }
}

