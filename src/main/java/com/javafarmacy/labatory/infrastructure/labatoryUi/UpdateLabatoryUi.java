package com.javafarmacy.labatory.infrastructure.labatoryUi;

import java.awt.*;
import java.util.Optional;
import javax.swing.*;

import com.javafarmacy.labatory.application.FindLabatoryByIdUseCase;
import com.javafarmacy.labatory.application.UpdateLabatoryUseCase;
import com.javafarmacy.labatory.domain.entity.Labatory;

public class UpdateLabatoryUi extends JFrame {
    private final UpdateLabatoryUseCase updateLabatoryUseCase;
    private final FindLabatoryByIdUseCase findLabatoryByIdUseCase;  // Added findLabatoryByIdUseCase field
    private final LabatoryUiController labatoryUiController;

    private JTextField jTextField1; // Labatory Code
    private JTextField jTextField2; // Labatory Name
    private JTextField jTextField3; // Labatory Name
    
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back
    private JButton jButton4; // Find

    public UpdateLabatoryUi(UpdateLabatoryUseCase updateLabatoryUseCase, FindLabatoryByIdUseCase findLabatoryByIdUseCase, LabatoryUiController labatoryUiController) {
        this.updateLabatoryUseCase = updateLabatoryUseCase;
        this.findLabatoryByIdUseCase = findLabatoryByIdUseCase;  // Initialized findLabatoryByIdUseCase
        this.labatoryUiController = labatoryUiController;
    }

    public void frmUpdateLabatory() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Update Labatory");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Update Labatory");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");
        jButton4 = new JButton("Find");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> updateLabatory());
        jButton3.addActionListener(e -> {
            dispose();
            labatoryUiController.showCrudOptions();
        });
        jButton4.addActionListener(e -> findLabatory());

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Labatory Code:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(jButton4, 2, 0, 2);
        addComponent(new JLabel("Labatory Name:"), 3, 0);
        addComponent(jTextField2, 3, 1);
        addComponent(new JLabel("City Code:"), 4, 0);
        addComponent(jTextField3, 4, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 5;
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

    private void updateLabatory() {
        try {
            Labatory labatory = new Labatory();
            labatory.setId(Integer.parseInt(jTextField1.getText()));
            labatory.setNamelab(jTextField2.getText());
            labatory.setCodecityreg(jTextField3.getText());

            updateLabatoryUseCase.execute(labatory);
            JOptionPane.showMessageDialog(this, "Labatory updated successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findLabatory() {
        String codeToUpdate = jTextField1.getText();
        Optional<Labatory> labatoryToUpdate = findLabatoryByIdUseCase.execute(codeToUpdate);
    
        if (labatoryToUpdate.isPresent()) {
            Labatory foundLabatory = labatoryToUpdate.get();
            jTextField2.setText(foundLabatory.getNamelab());
            jTextField3.setText(foundLabatory.getCodecityreg());
            jTextField1.setEditable(false);
            showComponents();
            revalidate(); // Asegura que el layout se actualice
            repaint(); // Redibuja la ventana
        } else {
            JOptionPane.showMessageDialog(this, "Labatory not found!", "Error", JOptionPane.ERROR_MESSAGE);
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
        jTextField3.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);

    }

    private void showComponents() {
        jTextField2.setVisible(true);
        jTextField3.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
}
