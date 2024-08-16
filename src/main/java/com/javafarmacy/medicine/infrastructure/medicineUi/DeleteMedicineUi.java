package com.javafarmacy.medicine.infrastructure.medicineUi;

import javax.swing.*;

import com.javafarmacy.medicine.application.DeleteMedicineUseCase;
import com.javafarmacy.medicine.domain.entity.Medicine;

import java.awt.*;

public class DeleteMedicineUi extends JFrame {
    private final DeleteMedicineUseCase deleteMedicineUseCase;
    private final MedicineCrudUi medicineCrudUi;
    private JTextField txtId;
    private JTextArea resultArea;

    public DeleteMedicineUi(DeleteMedicineUseCase deleteMedicineUseCase,MedicineCrudUi medicineCrudUi) {
        this.deleteMedicineUseCase = deleteMedicineUseCase;
        this.medicineCrudUi = medicineCrudUi;
    }

    public void showDeleteMedicine() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Delete Medicine");
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

        JLabel titleLabel = new JLabel("Delete Medicine");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Medicine ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteMedicine());
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
            medicineCrudUi.showFrame();
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

    private void deleteMedicine() {
        String medicineId = txtId.getText();
        Medicine deletedMedicine = deleteMedicineUseCase.execute(medicineId);
        if (deletedMedicine != null) {
            String message = String.format(
                "Medicine deleted:\n\n" +
                "Id: %d\n" +
                "Proceedings: %s\n" +
                "Name: %s\n" +
                "HealthRegister: %s\n" +
                "Description: %s\n" +
                "DescriotionShort: %s\n" +
                "Administration Mode Code: %d"+
                "Component Code: %d\n" +
                "Unit Code: %d\n" +
                "Rol Name: %s\n" +
                "Lab Code: %d\n",
                deletedMedicine.getId(),
                deletedMedicine.getProceedings(),
                deletedMedicine.getNamemedicine(),
                deletedMedicine.getHealthregister(),
                deletedMedicine.getDescription(),
                deletedMedicine.getDescriptionshort(),
                deletedMedicine.getCodemodeadmin(),
                deletedMedicine.getCodeap(),
                deletedMedicine.getCodeum(),
                deletedMedicine.getNamerol(),
                deletedMedicine.getCodelab()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Medicine not found or deletion failed.");
        }
    }
}