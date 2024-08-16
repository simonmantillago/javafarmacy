package com.javafarmacy.farmacy.infrastructure.farmacyUi;


import com.javafarmacy.farmacy.application.FindFarmacyByIdUseCase;
import com.javafarmacy.farmacy.domain.entity.Farmacy;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class FindFarmacyUi extends JFrame {
    private final FindFarmacyByIdUseCase findFarmacyByIdUseCase;
    private final FarmacyCrudUi farmacyCrudUi;
    private JTextField txtId;
    private JTextArea resultArea;

    public FindFarmacyUi(FindFarmacyByIdUseCase findFarmacyByIdUseCase, FarmacyCrudUi farmacyCrudUi) {
        this.findFarmacyByIdUseCase = findFarmacyByIdUseCase;
        this.farmacyCrudUi = farmacyCrudUi;
    }

    public void showFindFarmacy() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find Farmacy");
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

        JLabel titleLabel = new JLabel("Find Farmacy");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Farmacy ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnFind = new JButton("Find");
        btnFind.addActionListener(e -> findFarmacy());
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

    private void findFarmacy() {
        String farmacyId = txtId.getText();
        Optional<Farmacy> farmacyOpt = findFarmacyByIdUseCase.execute(farmacyId);
        if (farmacyOpt.isPresent()) {
            Farmacy farmacy = farmacyOpt.get();
            String message = String.format(
                "Farmacy found:\n\n" +
                "ID: %d\n" +
                "Name: %s\n" +
                "Address: %s\n" +
                "Longitude: %.2f\n" +
                "Latitude: %.2f\n" +
                "City Code: %s\n" +
                "Logo: %s",
                farmacy.getIdfarmacy(),
                farmacy.getNamefarmacy(),
                farmacy.getAddressfarmacy(),
                farmacy.getLongitude(),
                farmacy.getLatfarmacy(),
                farmacy.getCodecityfarm(),
                farmacy.getLogofarmacy()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Farmacy not found!");
        }
    }
}