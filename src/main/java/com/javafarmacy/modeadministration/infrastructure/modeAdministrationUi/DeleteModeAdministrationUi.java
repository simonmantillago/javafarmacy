package com.javafarmacy.modeadministration.infrastructure.modeAdministrationUi;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.modeadministration.application.DeleteModeAdministrationUseCase;
import com.javafarmacy.modeadministration.domain.entity.ModeAdministration;

public class DeleteModeAdministrationUi extends JFrame {
    private final DeleteModeAdministrationUseCase deleteModeAdministrationUseCase;
    private final ModeAdministrationUiController modeAdministrationUiController;
    private JTextField txtId;
    private JTextArea resultArea;
    
    public DeleteModeAdministrationUi(DeleteModeAdministrationUseCase deleteModeAdministrationUseCase, ModeAdministrationUiController modeAdministrationUiController) {
        this.deleteModeAdministrationUseCase = deleteModeAdministrationUseCase;
        this.modeAdministrationUiController = modeAdministrationUiController;
    }

    public void showDeleteModeAdministration() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Delete ModeAdministration");
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

        JLabel titleLabel = new JLabel("Delete ModeAdministration");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter ModeAdministration Code:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteModeAdministration());
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
            modeAdministrationUiController.showCrudOptions();
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

    private void deleteModeAdministration() {
        String modeAdministrationCode = txtId.getText();
        ModeAdministration deletedModeAdministration = deleteModeAdministrationUseCase.execute(modeAdministrationCode);

        if (deletedModeAdministration != null) {
            String message = String.format(
                "ModeAdministration deleted successfully:\n\n" +
                "Code: %d\n" +
                "Description: %s\n",
                deletedModeAdministration.getId(),
                deletedModeAdministration.getDescriptionmode()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("ModeAdministration not found or deletion failed.");
        }
    }
}

