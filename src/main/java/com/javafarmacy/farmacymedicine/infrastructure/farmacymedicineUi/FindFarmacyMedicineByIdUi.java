package com.javafarmacy.farmacymedicine.infrastructure.farmacymedicineUi;

import java.util.Optional;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.farmacymedicine.application.FindFarmacyMedicineByIdUseCase;
import com.javafarmacy.farmacymedicine.domain.entity.FarmacyMedicine;

public class FindFarmacyMedicineByIdUi extends JFrame {
    private final FindFarmacyMedicineByIdUseCase findFarmacyMedicineByIdUseCase;
    private final FarmacyMedicineUiController farmacyMedicineUiController;
    private JTextField txtIdfarmacy;
    private JTextField txtIdmedicine;
    private JTextArea resultArea;



    public FindFarmacyMedicineByIdUi(FindFarmacyMedicineByIdUseCase findFarmacyMedicineByIdUseCase, FarmacyMedicineUiController farmacyMedicineUiController) {
        this.findFarmacyMedicineByIdUseCase = findFarmacyMedicineByIdUseCase;
        this.farmacyMedicineUiController = farmacyMedicineUiController;
    }

    public void showFindFarmacyMedicine() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find FarmacyMedicine");
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

        JLabel titleLabel = new JLabel("Find FarmacyMedicine");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter FarmacyID:");
        addComponent(lblId, 1, 0);

        JLabel lblIdmed = new JLabel("Enter Medicine ID:");
        addComponent(lblIdmed, 2, 0);

        txtIdfarmacy = new JTextField();
        addComponent(txtIdfarmacy, 1, 1);

        txtIdmedicine = new JTextField();
        addComponent(txtIdmedicine, 2, 1);

        JButton btnFind = new JButton("Find");
        btnFind.addActionListener(e -> findFarmacyMedicine());
        addComponent(btnFind, 3, 0, 2);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> {
            dispose();
            farmacyMedicineUiController.showCrudOptions();
        });
        addComponent(btnClose, 5, 0, 2);
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

    private void findFarmacyMedicine() {
        String farmacyCode = txtIdfarmacy.getText();
        String medicineCode = txtIdmedicine.getText();
        Optional<FarmacyMedicine> farmacyMedicineOpt = findFarmacyMedicineByIdUseCase.execute(farmacyCode,medicineCode);
        if (farmacyMedicineOpt.isPresent()) {
            FarmacyMedicine farmacyMedicine = farmacyMedicineOpt.get();
            String message = String.format(
                "FarmacyMedicine found:\n\n" +
                "Farmacy Code: %d\n" +
                "Medicine Code: %d\n"+
                "Price: %.2f\n",
                farmacyMedicine.getIdfarmacy(),
                farmacyMedicine.getIdmedicine(),
                farmacyMedicine.getPrice()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("FarmacyMedicine not found!");
        }
    }
}
