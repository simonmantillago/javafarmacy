package com.javafarmacy.medicine.infrastructure.medicineUi;



import javax.swing.*;

import com.javafarmacy.medicine.application.CreateMedicineUseCase;
import com.javafarmacy.medicine.domain.entity.Medicine;

import java.awt.*;

public class CreateMedicineUi extends JFrame {
    private final CreateMedicineUseCase createMedicineUseCase;
    private final MedicineCrudUi medicineCrudUi;
    
    // Components
    private JTextField jTextField1;
    private JTextField jTextField2; 
    private JTextField jTextField3; 
    private JTextField jTextField4; 
    private JTextField jTextField5; 
    private JTextField jTextField6;
    private JTextField jTextField7;
    private JTextField jTextField8;
    private JTextField jTextField9;
    private JTextField jTextField10;



    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back

    public CreateMedicineUi(CreateMedicineUseCase createMedicineUseCase,MedicineCrudUi medicineCrudUi) {
        this.createMedicineUseCase = createMedicineUseCase;
        this.medicineCrudUi = medicineCrudUi;
    }

    public void frmRegMedicine() {
       
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Medicine");
        setSize(800, 800);

        JLabel jLabel1 = new JLabel("Create Medicine");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();
        jTextField7 = new JTextField();
        jTextField8 = new JTextField();
        jTextField9 = new JTextField();
        jTextField10 = new JTextField();



        

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> saveMedicine());
        jButton3.addActionListener(e -> {
            dispose();
            medicineCrudUi.showFrame();
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar el espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame
        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Proceedings:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("Name:"), 2, 0);
        addComponent(jTextField2, 2, 1);
        addComponent(new JLabel("HealthRegister:"), 3, 0);
        addComponent(jTextField3, 3, 1);
        addComponent(new JLabel("Description:"), 4, 0);
        addComponent(jTextField4, 4, 1);
        addComponent(new JLabel("DescriotionShort:"), 5, 0);
        addComponent(jTextField5, 5, 1);
        addComponent(new JLabel("Administration Code:"), 6, 0);
        addComponent(jTextField6, 6, 1);
        addComponent(new JLabel("Component Code:"), 7, 0);
        addComponent(jTextField7, 7, 1);
        addComponent(new JLabel("Unit Code:"), 8, 0);
        addComponent(jTextField8, 8, 1);
        addComponent(new JLabel("Rol Name:"), 9, 0);
        addComponent(jTextField9, 9, 1);
        addComponent(new JLabel("Laboratory Code:"), 10, 0);
        addComponent(jTextField10, 10, 1);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 12;
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
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes
        add(component, gbc);
    }

    private void saveMedicine() {
        try {
            Medicine medicine = new Medicine();
            medicine.setProceedings(jTextField1.getText());
            medicine.setNamemedicine(jTextField2.getText());
            medicine.setHealthregister(jTextField3.getText());
            medicine.setDescription(jTextField4.getText());
            medicine.setDescriptionshort(jTextField5.getText());
            medicine.setCodemodeadmin(Integer.parseInt(jTextField6.getText()));
            medicine.setCodeap(Integer.parseInt(jTextField7.getText()));
            medicine.setCodeum(Integer.parseInt(jTextField8.getText()));
            medicine.setNamerol(jTextField9.getText());
            medicine.setCodelab(Integer.parseInt(jTextField10.getText()));

            createMedicineUseCase.execute(medicine);
            JOptionPane.showMessageDialog(this, "Medicine added successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
    }
}