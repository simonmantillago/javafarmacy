package com.javafarmacy.farmacy.infrastructure.farmacyUi;



import javax.swing.*;

import com.javafarmacy.farmacy.application.CreateFarmacyUseCase;
import com.javafarmacy.farmacy.domain.entity.Farmacy;

import java.awt.*;

public class CreateFarmacyUi extends JFrame {
    private final CreateFarmacyUseCase createFarmacyUseCase;
    private final FarmacyCrudUi farmacyCrudUi;
    
    // Components
    private JTextField jTextField1; // name
    private JTextField jTextField2; // address
    private JTextField jTextField3; // longitude
    private JTextField jTextField4; // latfarmacy
    private JTextField jTextField5; // codecity
    private JTextField jTextField6; // logo


    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back

    public CreateFarmacyUi(CreateFarmacyUseCase createFarmacyUseCase,FarmacyCrudUi farmacyCrudUi) {
        this.createFarmacyUseCase = createFarmacyUseCase;
        this.farmacyCrudUi = farmacyCrudUi;
    }

    public void frmRegFarmacy() {
       
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Farmacy");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Create Farmacy");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();



        

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> saveFarmacy());
        jButton3.addActionListener(e -> {
            dispose();
            farmacyCrudUi.showFrame();
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar el espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame
        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("Name:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("Address:"), 2, 0);
        addComponent(jTextField2, 2, 1);
        addComponent(new JLabel("Longitude:"), 3, 0);
        addComponent(jTextField3, 3, 1);
        addComponent(new JLabel("Latitude:"), 4, 0);
        addComponent(jTextField4, 4, 1);
        addComponent(new JLabel("City Code:"), 5, 0);
        addComponent(jTextField5, 5, 1);
        addComponent(new JLabel("Logo:"), 6, 0);
        addComponent(jTextField6, 6, 1);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 8;
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

    private void saveFarmacy() {
        try {
            Farmacy farmacy = new Farmacy();
            farmacy.setNamefarmacy(jTextField1.getText());
            farmacy.setAddressfarmacy(jTextField2.getText());
            farmacy.setLongitude(Float.parseFloat(jTextField3.getText()));
            farmacy.setLatfarmacy(Float.parseFloat(jTextField4.getText()));
            farmacy.setCodecityfarm(jTextField5.getText());
            farmacy.setLogofarmacy(jTextField6.getText());
        



            createFarmacyUseCase.execute(farmacy);
            JOptionPane.showMessageDialog(this, "Farmacy added successfully!");
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
    }
}