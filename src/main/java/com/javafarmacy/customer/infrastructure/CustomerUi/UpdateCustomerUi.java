package com.javafarmacy.customer.infrastructure.CustomerUi;


import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.javafarmacy.customer.application.FindCustomerByIdUseCase;
import com.javafarmacy.customer.application.UpdateCustomerUseCase;
import com.javafarmacy.customer.domain.entity.Customer;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class UpdateCustomerUi extends JFrame {
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
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
    private JButton jButton4; // Find Customer by ID

    public UpdateCustomerUi(FindCustomerByIdUseCase findCustomerByIdUseCase, UpdateCustomerUseCase updateCustomerUseCase,CustomerCrudUi customerCrudUi) {
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.customerCrudUi = customerCrudUi;
    }

    public void frmUpdateCustomer() {
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
        jButton4 = new JButton("Find");

        jButton1.addActionListener(e -> resetFields());
        jButton2.addActionListener(e -> updateCustomer());
        jButton3.addActionListener(e -> {
            dispose();
            customerCrudUi.showFrame();
        });
        jButton4.addActionListener(e -> findCustomer());
        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Aumentar el espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame
        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("ID Number:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(jButton4, 2, 0, 2);
        addComponent(new JLabel("First Name:"), 3, 0);
        addComponent(jTextField2, 3, 1);
        addComponent(new JLabel("Last Name:"), 4, 0);
        addComponent(jTextField3, 4, 1);
        addComponent(new JLabel("City Code:"), 5, 0);
        addComponent(jTextField4, 5, 1);
        addComponent(new JLabel("Email:"), 6, 0);
        addComponent(jTextField5, 6, 1);
        addComponent(new JLabel("BirthDate:"), 7, 0);
        addComponent(jTextField6, 7, 1);
        addComponent(new JLabel("Longitude:"), 8, 0);
        addComponent(jTextField7, 8, 1);
        addComponent(new JLabel("Latitude:"), 9, 0);
        addComponent(jTextField8, 9, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton3);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        setLocationRelativeTo(null);
    
        // Initially hide all components except ID and Find button
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

    private void updateCustomer() {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String birthDateStr = jTextField6.getText();
            java.util.Date date = inputFormat.parse(birthDateStr);
            String formattedBirthDate = outputFormat.format(date);

            Customer customer = new Customer();
            customer.setIdcustomer(jTextField1.getText());
            customer.setNamecustomer(jTextField2.getText());
            customer.setLastnamecustomer(jTextField3.getText());
            customer.setCodecitycustomer(jTextField4.getText());
            customer.setEmailcustomer(jTextField5.getText());
            customer.setBirthDate(formattedBirthDate);
            customer.setLon(Float.parseFloat(jTextField7.getText()));
            customer.setLatitud(Float.parseFloat(jTextField8.getText()));

            updateCustomerUseCase.execute(customer);
            JOptionPane.showMessageDialog(this, "Customer updated successfully!");
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
        hideComponents();
    }

    private void findCustomer() {
        String idToUpdate = jTextField1.getText();
        Optional<Customer> customerToUpdate = findCustomerByIdUseCase.execute(idToUpdate);
        if (customerToUpdate.isPresent()) {
            Customer foundCustomer = customerToUpdate.get();
            try {
                
                jTextField2.setText(foundCustomer.getNamecustomer());
                jTextField3.setText(foundCustomer.getLastnamecustomer());

                jTextField4.setText(foundCustomer.getCodecitycustomer());
                jTextField5.setText(foundCustomer.getEmailcustomer());
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedBirthDate = outputFormat.format(inputFormat.parse(foundCustomer.getBirthDate()));
                jTextField6.setText(formattedBirthDate);
                jTextField7.setText(Float.toString(foundCustomer.getLon()));
                jTextField8.setText(Float.toString(foundCustomer.getLatitud()));
                jTextField1.setEditable(false);
                showComponents();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hideComponents() {
        jTextField1.setEditable(true);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jTextField6.setVisible(false);
        jTextField7.setVisible(false);
        jTextField8.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
    }

    private void showComponents() {

        jTextField2.setVisible(true);
        jTextField3.setVisible(true);
        jTextField4.setVisible(true);
        jTextField5.setVisible(true);
        jTextField6.setVisible(true);
        jTextField7.setVisible(true);
        jTextField8.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
}