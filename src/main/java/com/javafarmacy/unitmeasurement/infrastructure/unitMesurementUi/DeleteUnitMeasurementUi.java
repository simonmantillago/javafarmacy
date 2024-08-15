package com.javafarmacy.unitmeasurement.infrastructure.unitMesurementUi;

import javax.swing.*;

import com.javafarmacy.unitmeasurement.application.DeleteUnitMeasurementUseCase;
import com.javafarmacy.unitmeasurement.domain.entity.UnitMeasurement;

import java.awt.*;

public class DeleteUnitMeasurementUi extends JFrame {
    private final DeleteUnitMeasurementUseCase deleteUnitMeasurementUseCase;
    private final UnitMeasurementUiController unitMeasurementUiController;
    private JTextField txtId;
    private JTextArea resultArea;
    
    public DeleteUnitMeasurementUi(DeleteUnitMeasurementUseCase deleteUnitMeasurementUseCase, UnitMeasurementUiController unitMeasurementUiController) {
        this.deleteUnitMeasurementUseCase = deleteUnitMeasurementUseCase;
        this.unitMeasurementUiController = unitMeasurementUiController;
    }

    public void showDeleteUnitMeasurement() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Delete UnitMeasurement");
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

        JLabel titleLabel = new JLabel("Delete UnitMeasurement");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter UnitMeasurement Code:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteUnitMeasurement());
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

    private void deleteUnitMeasurement() {
        String unitMeasurementCode = txtId.getText();
        UnitMeasurement deletedUnitMeasurement = deleteUnitMeasurementUseCase.execute(unitMeasurementCode);

        if (deletedUnitMeasurement != null) {
            String message = String.format(
                "UnitMeasurement deleted successfully:\n\n" +
                "Code: %d\n" +
                "Description: %s\n",
                deletedUnitMeasurement.getIdum(),
                deletedUnitMeasurement.getnameum()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("UnitMeasurement not found or deletion failed.");
        }
    }
}

