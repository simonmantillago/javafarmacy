package com.javafarmacy.activeprinciple.infrastructure.activePrincipleUi;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.activeprinciple.application.DeleteActiveprincipleUseCase;
import com.javafarmacy.activeprinciple.domain.entity.Activeprinciple;

public class DeleteActiveprincipleUi extends JFrame {
    private final DeleteActiveprincipleUseCase deleteActiveprincipleUseCase;
    private final ActiveprincipleUiController activeprincipleUiController;
    private JTextField txtId;
    private JTextArea resultArea;
    
    public DeleteActiveprincipleUi(DeleteActiveprincipleUseCase deleteActiveprincipleUseCase, ActiveprincipleUiController activeprincipleUiController) {
        this.deleteActiveprincipleUseCase = deleteActiveprincipleUseCase;
        this.activeprincipleUiController = activeprincipleUiController;
    }

    public void showDeleteActiveprinciple() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Delete Activeprinciple");
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

        JLabel titleLabel = new JLabel("Delete Activeprinciple");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Activeprinciple Code:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteActiveprinciple());
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
            activeprincipleUiController.showCrudOptions();
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

    private void deleteActiveprinciple() {
        String activeprincipleCode = txtId.getText();
        Activeprinciple deletedActiveprinciple = deleteActiveprincipleUseCase.execute(activeprincipleCode);

        if (deletedActiveprinciple != null) {
            String message = String.format(
                "Activeprinciple deleted successfully:\n\n" +
                "Code: %d\n" +
                "Active Principle name: %s\n",
                deletedActiveprinciple.getIdap(),
                deletedActiveprinciple.getNameap()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Activeprinciple not found or deletion failed.");
        }
    }
}

