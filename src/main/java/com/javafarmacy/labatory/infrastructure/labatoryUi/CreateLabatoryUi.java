package com.javafarmacy.labatory.infrastructure.labatoryUi;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.javafarmacy.labatory.application.CreateLabatoryUseCase;
import com.javafarmacy.labatory.domain.entity.Labatory;

public class CreateLabatoryUi extends JFrame{
private final CreateLabatoryUseCase createLabatoryUseCase;
    private final LabatoryUiController labatoryUiController; // Use LabatoryUiController if that's the intended class

    private JTextField jTextField1; // Labatory Name
    private JTextField jTextField2; // codecity

    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back

    public CreateLabatoryUi(CreateLabatoryUseCase createLabatoryUseCase, LabatoryUiController labatoryUiController) { // Use LabatoryUiController
        this.createLabatoryUseCase = createLabatoryUseCase;
        this.labatoryUiController = labatoryUiController; // Use LabatoryUiController
    }

    public void frmRegLabatory() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Labatory");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Create Labatory");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
    

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> saveLabatory());
        jButton3.addActionListener(e -> {
            dispose();
            labatoryUiController.showCrudOptions(); // Adjusted to call the method in LabatoryUiController
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Increased spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Laboratory Name:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("City code:"), 2, 0);
        addComponent(jTextField2, 2, 1);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setLocationRelativeTo(null);
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
        gbc.insets = new Insets(5, 5, 5, 5); // Spacing between components
        add(component, gbc);
    }

    private void saveLabatory() {
        try {
            Labatory labatory = new Labatory();
            labatory.setNamelab(jTextField1.getText());
            labatory.setCodecityreg(jTextField2.getText());

            createLabatoryUseCase.execute(labatory); // Corrected from "customer" to "labatory"
            JOptionPane.showMessageDialog(this, "Labatory added successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");

    }
}
