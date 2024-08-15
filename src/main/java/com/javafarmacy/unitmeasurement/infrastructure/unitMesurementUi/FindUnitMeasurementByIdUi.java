package com.javafarmacy.unitmeasurement.infrastructure.unitMesurementUi;

import java.util.Optional;


import javax.swing.*;

import com.javafarmacy.unitmeasurement.application.FindUnitMeasurementByIdUseCase;
import com.javafarmacy.unitmeasurement.domain.entity.UnitMeasurement;

import java.awt.*;

public class FindUnitMeasurementByIdUi extends JFrame {
    private final FindUnitMeasurementByIdUseCase findUnitMeasurementByIdUseCase;
    private final UnitMeasurementUiController unitMeasurementUiController;
    private JTextField txtId;
    private JTextArea resultArea;



    public FindUnitMeasurementByIdUi(FindUnitMeasurementByIdUseCase findUnitMeasurementByIdUseCase, UnitMeasurementUiController unitMeasurementUiController) {
        this.findUnitMeasurementByIdUseCase = findUnitMeasurementByIdUseCase;
        this.unitMeasurementUiController = unitMeasurementUiController;
    }

    public void showFindUnitMeasurement() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find UnitMeasurement");
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

        JLabel titleLabel = new JLabel("Find UnitMeasurement");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter UnitMeasurement ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnFind = new JButton("Find");
        btnFind.addActionListener(e -> findUnitMeasurement());
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
            unitMeasurementUiController.showCrudOptions();
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

    private void findUnitMeasurement() {
        String unitMeasurementId = txtId.getText();
        Optional<UnitMeasurement> unitMeasurementOpt = findUnitMeasurementByIdUseCase.execute(unitMeasurementId);
        if (unitMeasurementOpt.isPresent()) {
            UnitMeasurement unitMeasurement = unitMeasurementOpt.get();
            String message = String.format(
                "UnitMeasurement found:\n\n" +
                "ID: %d\n" +
                "UnitMeasurement: %s\n",
                unitMeasurement.getIdum(),
                unitMeasurement.getnameum()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("UnitMeasurement not found!");
        }
    }
}
