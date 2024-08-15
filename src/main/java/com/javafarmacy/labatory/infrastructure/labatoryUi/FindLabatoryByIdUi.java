package com.javafarmacy.labatory.infrastructure.labatoryUi;

import java.util.Optional;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.labatory.application.FindLabatoryByIdUseCase;
import com.javafarmacy.labatory.domain.entity.Labatory;

public class FindLabatoryByIdUi extends JFrame {
    private final FindLabatoryByIdUseCase findLabatoryByIdUseCase;
    private final LabatoryUiController labatoryUiController;
    private JTextField txtId;
    private JTextArea resultArea;



    public FindLabatoryByIdUi(FindLabatoryByIdUseCase findLabatoryByIdUseCase, LabatoryUiController labatoryUiController) {
        this.findLabatoryByIdUseCase = findLabatoryByIdUseCase;
        this.labatoryUiController = labatoryUiController;
    }

    public void showFindLabatory() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find Labatory");
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

        JLabel titleLabel = new JLabel("Find Labatory");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter Labatory ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnFind = new JButton("Find");
        btnFind.addActionListener(e -> findLabatory());
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
            labatoryUiController.showCrudOptions();
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

    private void findLabatory() {
        String labatoryId = txtId.getText();
        Optional<Labatory> labatoryOpt = findLabatoryByIdUseCase.execute(labatoryId);
        if (labatoryOpt.isPresent()) {
            Labatory labatory = labatoryOpt.get();
            String message = String.format(
                "Labatory found:\n\n" +
                "ID: %d\n" +
                "Name Labatory: %s\n" +
                "City code: %s\n",
                labatory.getId(),
                labatory.getNamelab(),
                labatory.getCodecityreg()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("Labatory not found!");
        }
    }
}
