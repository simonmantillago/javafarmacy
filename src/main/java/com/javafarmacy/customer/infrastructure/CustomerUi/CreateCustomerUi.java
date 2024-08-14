package com.javafarmacy.customer.infrastructure.CustomerUi;



import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.javafarmacy.customer.application.CreateCustomerUseCase;
import com.javafarmacy.customer.domain.entity.Customer;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateCustomerUi extends JFrame {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final CustomerCrudUi customerCrudUi;
    
    // Components
    private JTextField jTextField1; // ID Number
    private JTextField jTextField2; // First Name
    private JTextField jTextField3; // Last Name
    private JTextField jTextField4; // City ID
    private JTextField jTextField5; // Email
    private JFormattedTextField jTextField6; // BirthDate
    private JTextField jTextField7; // lon
    private JTextField jTextField8; // lat

    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back

    public CreateCustomerUi(CreateCustomerUseCase createCustomerUseCase,CustomerCrudUi customerCrudUi) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.customerCrudUi = customerCrudUi;
    }

    public void frmRegCustomer() {
       
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Customer");
        setSize(500, 500);

        JLabel jLabel1 = new JLabel("Create Customer");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField7 = new JTextField();
        jTextField8 = new JTextField();


        // Configurar el campo de fecha con MaskFormatter
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            jTextField6 = new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            e.printStackTrace();
            jTextField6 = new JFormattedTextField(); // Fallback en caso de error
        }

        

        jButton1 = new JButton("Reset");
        jButton2 = new JButton("Save");
        jButton3 = new JButton("Go back");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> saveCustomer());
        jButton3.addActionListener(e -> {
            dispose();
            customerCrudUi.showFrame();
        });

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar el espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame
        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("ID Number:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(new JLabel("First Name:"), 2, 0);
        addComponent(jTextField2, 2, 1);
        addComponent(new JLabel("Last Name:"), 3, 0);
        addComponent(jTextField3, 3, 1);
        addComponent(new JLabel("City Code:"), 4, 0);
        addComponent(jTextField4, 4, 1);
        addComponent(new JLabel("Email:"), 5, 0);
        addComponent(jTextField5, 5, 1);
        addComponent(new JLabel("BirthDate:"), 6, 0);
        addComponent(jTextField6, 6, 1);
        addComponent(new JLabel("Longitude:"), 7, 0);
        addComponent(jTextField7, 7, 1);
        addComponent(new JLabel("Latitude:"), 8, 0);
        addComponent(jTextField7, 8, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 10;
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

    private void saveCustomer() {
        try {
            Customer customer = new Customer();
            customer.setIdcustomer(jTextField1.getText());
            customer.setNamecustomer(jTextField2.getText());
            customer.setLastnamecustomer(jTextField3.getText());
            customer.setCodecitycustomer(jTextField4.getText());
            customer.setEmailcustomer(jTextField5.getText());

            String birthDateText = jTextField6.getText();
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedBirthDate = outputFormat.format(inputFormat.parse(birthDateText));
            customer.setBirthDate(formattedBirthDate);

            customer.setLon(Float.parseFloat(jTextField7.getText()));
            customer.setLatitud(Float.parseFloat(jTextField8.getText()));


            createCustomerUseCase.execute(customer);
            JOptionPane.showMessageDialog(this, "Customer added successfully!");
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
    }
}