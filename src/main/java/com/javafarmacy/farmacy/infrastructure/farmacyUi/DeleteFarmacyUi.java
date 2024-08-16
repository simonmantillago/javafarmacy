package com.javafarmacy.farmacy.infrastructure.farmacyUi;

import javax.swing.*;

import com.javafarmacy.farmacy.application.DeleteFarmacyUseCase;
import com.javafarmacy.farmacy.domain.entity.Farmacy;

import java.awt.*;

public class DeleteFarmacyUi extends JFrame {
    private final DeleteFarmacyUseCase deleteFarmacyUseCase;
    private final FarmacyCrudUi farmacyCrudUi;
    private JTextField txtId;
    private JTextArea resultArea;

    public DeleteFarmacyUi(DeleteFarmacyUseCase deleteFarmacyUseCase,FarmacyCrudUi farmacyCrudUi) {
        this.deleteFarmacyUseCase = deleteFarmacyUseCase;
        this.farmacyCrudUi = farmacyCrudUi;
    }

    public void showDeleteFarmacy() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Delete Farmacy");
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

        JLabel titleLabel = new JLabel("Delete Farmacy");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Farmacy ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteFarmacy());
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
            farmacyCrudUi.showFrame();
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

    private void deleteFarmacy() {
        String farmacyId = txtId.getText();
        Farmacy deletedFarmacy = deleteFarmacyUseCase.execute(farmacyId);
        if (deletedFarmacy != null) {
            String message = String.format(
                "Farmacy deleted:\n\n" +
                "ID: %d\n" +
                "Name: %s\n" +
                "Address: %s\n" +
                "Longitude: %.2f\n" +
                "Latitude: %.2f\n" +
                "City Code: %s\n" +
                "Logo: %s",
                deletedFarmacy.getIdfarmacy(),
                deletedFarmacy.getNamefarmacy(),
                deletedFarmacy.getAddressfarmacy(),
                deletedFarmacy.getLongitude(),
                deletedFarmacy.getLatfarmacy(),
                deletedFarmacy.getCodecityfarm(),
                deletedFarmacy.getLogofarmacy()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Farmacy not found or deletion failed.");
        }
    }
}