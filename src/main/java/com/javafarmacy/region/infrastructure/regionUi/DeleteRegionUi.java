package com.javafarmacy.region.infrastructure.regionUi;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.region.application.DeleteRegionUseCase;
import com.javafarmacy.region.domain.entity.Region;

public class DeleteRegionUi extends JFrame {
    private final DeleteRegionUseCase deleteRegionUseCase;
    private final RegionUiController regionUiController;
    private JTextField txtId;
    private JTextArea resultArea;
    
    public DeleteRegionUi(DeleteRegionUseCase deleteRegionUseCase, RegionUiController regionUiController) {
        this.deleteRegionUseCase = deleteRegionUseCase;
        this.regionUiController = regionUiController;
    }

    public void showDeleteRegion() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Delete Region");
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

        JLabel titleLabel = new JLabel("Delete Region");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Region Code:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteRegion());
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

    private void deleteRegion() {
        String regionCode = txtId.getText();
        Region deletedRegion = deleteRegionUseCase.execute(regionCode);

        if (deletedRegion != null) {
            String message = String.format(
                "Region deleted successfully:\n\n" +
                "Code: %s\n" +
                "Name: %s\n"+
                "Country Code: %s\n",
                deletedRegion.getCodeRegion(),
                deletedRegion.getNameRegion(),
                deletedRegion.getCodeCountryReg()

            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Region not found or deletion failed.");
        }
    }
}

