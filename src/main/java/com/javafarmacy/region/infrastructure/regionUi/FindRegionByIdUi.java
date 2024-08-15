package com.javafarmacy.region.infrastructure.regionUi;

import java.util.Optional;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.region.application.FindRegionByIdUseCase;
import com.javafarmacy.region.domain.entity.Region;

public class FindRegionByIdUi extends JFrame {
    private final FindRegionByIdUseCase findRegionByIdUseCase;
    private final RegionUiController regionUiController;
    private JTextField txtId;
    private JTextArea resultArea;



    public FindRegionByIdUi(FindRegionByIdUseCase findRegionByIdUseCase, RegionUiController regionUiController) {
        this.findRegionByIdUseCase = findRegionByIdUseCase;
        this.regionUiController = regionUiController;
    }

    public void showFindRegion() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find Region");
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

        JLabel titleLabel = new JLabel("Find Region");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Region ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnFind = new JButton("Find");
        btnFind.addActionListener(e -> findRegion());
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
            regionUiController.showCrudOptions();
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

    private void findRegion() {
        String regionId = txtId.getText();
        Optional<Region> regionOpt = findRegionByIdUseCase.execute(regionId);
        if (regionOpt.isPresent()) {
            Region region = regionOpt.get();
            String message = String.format(
                "Region found:\n\n" +
                "ID: %s\n" +
                "Name Region: %s\n" +
                "Country: %s\n",
                region.getCodeRegion(),
                region.getNameRegion(),
                region.getCodeCountryReg()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Region not found!");
        }
    }
}
