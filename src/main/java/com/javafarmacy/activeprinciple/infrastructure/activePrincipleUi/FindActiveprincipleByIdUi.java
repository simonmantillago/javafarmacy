package com.javafarmacy.activeprinciple.infrastructure.activePrincipleUi;

import java.util.Optional;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.activeprinciple.application.FindActiveprincipleByIdUseCase;
import com.javafarmacy.activeprinciple.domain.entity.Activeprinciple;

public class FindActiveprincipleByIdUi extends JFrame {
    private final FindActiveprincipleByIdUseCase findActiveprincipleByIdUseCase;
    private final ActiveprincipleUiController activeprincipleUiController;
    private JTextField txtId;
    private JTextArea resultArea;



    public FindActiveprincipleByIdUi(FindActiveprincipleByIdUseCase findActiveprincipleByIdUseCase, ActiveprincipleUiController activeprincipleUiController) {
        this.findActiveprincipleByIdUseCase = findActiveprincipleByIdUseCase;
        this.activeprincipleUiController = activeprincipleUiController;
    }

    public void showFindActiveprinciple() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find Activeprinciple");
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

        JLabel titleLabel = new JLabel("Find Activeprinciple");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Activeprinciple ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnFind = new JButton("Find");
        btnFind.addActionListener(e -> findActiveprinciple());
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

    private void findActiveprinciple() {
        String activeprincipleId = txtId.getText();
        Optional<Activeprinciple> activeprincipleOpt = findActiveprincipleByIdUseCase.execute(activeprincipleId);
        if (activeprincipleOpt.isPresent()) {
            Activeprinciple activeprinciple = activeprincipleOpt.get();
            String message = String.format(
                "Activeprinciple found:\n\n" +
                "ID: %d\n" +
                "Name Activeprinciple: %s\n",
                activeprinciple.getIdap(),
                activeprinciple.getNameap()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Activeprinciple not found!");
        }
    }
}
