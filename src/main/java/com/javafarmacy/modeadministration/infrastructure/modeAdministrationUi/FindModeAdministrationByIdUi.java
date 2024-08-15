package com.javafarmacy.modeadministration.infrastructure.modeAdministrationUi;

import java.util.Optional;


import javax.swing.*;
import java.awt.*;
import com.javafarmacy.modeadministration.application.FindModeAdministrationByIdUseCase;
import com.javafarmacy.modeadministration.domain.entity.ModeAdministration;

public class FindModeAdministrationByIdUi extends JFrame {
    private final FindModeAdministrationByIdUseCase findModeAdministrationByIdUseCase;
    private final ModeAdministrationUiController modeAdministrationUiController;
    private JTextField txtId;
    private JTextArea resultArea;



    public FindModeAdministrationByIdUi(FindModeAdministrationByIdUseCase findModeAdministrationByIdUseCase, ModeAdministrationUiController modeAdministrationUiController) {
        this.findModeAdministrationByIdUseCase = findModeAdministrationByIdUseCase;
        this.modeAdministrationUiController = modeAdministrationUiController;
    }

    public void showFindModeAdministration() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Find ModeAdministration");
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

        JLabel titleLabel = new JLabel("Find ModeAdministration");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLabel, 0, 0, 2);

        JLabel lblId = new JLabel("Enter ModeAdministration ID:");
        addComponent(lblId, 1, 0);

        txtId = new JTextField();
        addComponent(txtId, 1, 1);

        JButton btnFind = new JButton("Find");
        btnFind.addActionListener(e -> findModeAdministration());
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

    private void findModeAdministration() {
        String modeAdministrationId = txtId.getText();
        Optional<ModeAdministration> modeAdministrationOpt = findModeAdministrationByIdUseCase.execute(modeAdministrationId);
        if (modeAdministrationOpt.isPresent()) {
            ModeAdministration modeAdministration = modeAdministrationOpt.get();
            String message = String.format(
                "ModeAdministration found:\n\n" +
                "ID: %d\n" +
                "Name ModeAdministration: %s\n",
                modeAdministration.getId(),
                modeAdministration.getDescriptionmode()
            );
            resultArea.setText(message);
        } else {
            resultArea.setText("ModeAdministration not found!");
        }
    }
}
