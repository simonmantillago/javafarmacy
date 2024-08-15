package com.javafarmacy.modeadministration.infrastructure.modeAdministrationUi;

import java.awt.*;
import java.util.Optional;
import javax.swing.*;

import com.javafarmacy.modeadministration.application.FindModeAdministrationByIdUseCase;
import com.javafarmacy.modeadministration.application.UpdateModeAdministrationUseCase;
import com.javafarmacy.modeadministration.domain.entity.ModeAdministration;

public class UpdateModeAdministrationUi extends JFrame {
    private final UpdateModeAdministrationUseCase updateModeAdministrationUseCase;
    private final FindModeAdministrationByIdUseCase findModeAdministrationByIdUseCase;  // Added findModeAdministrationByIdUseCase field
    private final ModeAdministrationUiController modeAdministrationUiController;

    private JTextField jTextField1; // ModeAdministration Code
    private JTextField jTextField2; // ModeAdministration Name
     // ModeAdministration Name
    
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back
    private JButton jButton4; // Find

    public UpdateModeAdministrationUi(UpdateModeAdministrationUseCase updateModeAdministrationUseCase, FindModeAdministrationByIdUseCase findModeAdministrationByIdUseCase, ModeAdministrationUiController modeAdministrationUiController) {
        this.updateModeAdministrationUseCase = updateModeAdministrationUseCase;
        this.findModeAdministrationByIdUseCase = findModeAdministrationByIdUseCase;  // Initialized findModeAdministrationByIdUseCase
        this.modeAdministrationUiController = modeAdministrationUiController;
    }

    public void frmUpdateModeAdministration() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update ModeAdministration");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Update ModeAdministration");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");
        jButton4 = new JButton("Find");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> updateModeAdministration());
        jButton3.addActionListener(e -> {
            dispose();
            modeAdministrationUiController.showCrudOptions();
        });
        jButton4.addActionListener(e -> findModeAdministration());

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("ModeAdministration Code:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(jButton4, 2, 0, 2);
        addComponent(new JLabel("ModeAdministration Name:"), 3, 0);
        addComponent(jTextField2, 3, 1);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setLocationRelativeTo(null);

        hideComponents();
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

    private void updateModeAdministration() {
        try {
            ModeAdministration modeadministration = new ModeAdministration();
            modeadministration.setId(Integer.parseInt(jTextField1.getText()));
            modeadministration.setDescriptionmode(jTextField2.getText());
       

            updateModeAdministrationUseCase.execute(modeadministration);
            JOptionPane.showMessageDialog(this, "ModeAdministration updated successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findModeAdministration() {
        String codeToUpdate = jTextField1.getText();
        Optional<ModeAdministration> modeadministrationToUpdate = findModeAdministrationByIdUseCase.execute(codeToUpdate);
    
        if (modeadministrationToUpdate.isPresent()) {
            ModeAdministration foundModeAdministration = modeadministrationToUpdate.get();
            jTextField2.setText(foundModeAdministration.getDescriptionmode());
            jTextField1.setEditable(false);
            showComponents();
            revalidate(); // Asegura que el layout se actualice
            repaint(); // Redibuja la ventana
        } else {
            JOptionPane.showMessageDialog(this, "ModeAdministration not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField1.setEditable(true);
        hideComponents();
    }

    private void hideComponents() {
        jTextField2.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);

    }

    private void showComponents() {
        jTextField2.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
}
