package com.farmacysystem.customer.infrastructure.CustomerUi;

import com.farmacysystem.customer.application.FindCustomerByIdUseCase;
import com.farmacysystem.customer.application.UpdateCustomerUseCase;
import com.farmacysystem.customer.domain.entity.Customer;
import com.farmacysystem.customer.domain.entity.CustomerDto;
import com.farmacysystem.identificationtypes.application.FindAllIdentificationTypesUseCase;
import com.farmacysystem.identificationtypes.domain.entity.IdentificationType;
import com.farmacysystem.identificationtypes.domain.service.IdentificationTypeService;
import com.farmacysystem.identificationtypes.infrastructure.IdentificationTypeRepository;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class UpdateCustomerUi extends JFrame {
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final CustomerCrudUi customerCrudUi;
    
    // Components
    private JTextField jTextField1; // ID Number
    private JComboBox<String> jComboBox1; // ID Type
    private JTextField jTextField2; // First Name
    private JTextField jTextField3; // Last Name
    private JFormattedTextField jTextField4; // BirthDate
    private JTextField jTextField5; // Age
    private JTextField jTextField6; // City ID
    private JTextField jTextField7; // Neighborhood ID
    private JButton jButton1; // Reset
    private JButton jButton2; // Save
    private JButton jButton3; // Go back
    private JButton jButton4; // Find

    public UpdateCustomerUi(FindCustomerByIdUseCase findCustomerByIdUseCase, UpdateCustomerUseCase updateCustomerUseCase,CustomerCrudUi customerCrudUi) {
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.customerCrudUi = customerCrudUi;
    }

    public void frmUpdateCustomer() {
        IdentificationTypeService identificationTypeService = new IdentificationTypeRepository();
        FindAllIdentificationTypesUseCase findAllIdentificationTypesUseCase = new FindAllIdentificationTypesUseCase(identificationTypeService);
        initComponents(findAllIdentificationTypesUseCase);
        setVisible(true);
    }

    private void initComponents(FindAllIdentificationTypesUseCase findAllIdentificationTypesUseCase) {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Customer");
        setSize(500, 600);

        JLabel jLabel1 = new JLabel("Update Customer");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        jTextField1 = new JTextField();
        jComboBox1 = new JComboBox<>();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();
        jTextField7 = new JTextField();

        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            jTextField4 = new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            e.printStackTrace();
            jTextField4 = new JFormattedTextField();
        }

        List<IdentificationType> identificationTypes = findAllIdentificationTypesUseCase.execute();
        for (IdentificationType identificationType : identificationTypes) {
            jComboBox1.addItem(identificationType.getDescription());
        }

        jTextField5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
                if (jTextField5.getText().length() >= 2) {
                    e.consume();
                }
            }
        });

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

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponent(jLabel1, 0, 0, 2);
        addComponent(new JLabel("ID Number:"), 1, 0);
        addComponent(jTextField1, 1, 1);
        addComponent(jButton4, 2, 0, 2);
        addComponent(new JLabel("ID Type:"), 3, 0);
        addComponent(jComboBox1, 3, 1);
        addComponent(new JLabel("First Name:"), 4, 0);
        addComponent(jTextField2, 4, 1);
        addComponent(new JLabel("Last Name:"), 5, 0);
        addComponent(jTextField3, 5, 1);
        addComponent(new JLabel("Birth Date:"), 6, 0);
        addComponent(jTextField4, 6, 1);
        addComponent(new JLabel("Age:"), 7, 0);
        addComponent(jTextField5, 7, 1);
        addComponent(new JLabel("City ID:"), 8, 0);
        addComponent(jTextField6, 8, 1);
        addComponent(new JLabel("Neighborhood ID:"), 9, 0);
        addComponent(jTextField7, 9, 1);

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
            String birthDateStr = jTextField4.getText();
            java.util.Date date = inputFormat.parse(birthDateStr);
            String formattedBirthDate = outputFormat.format(date);

            Customer customer = new Customer();
            customer.setIdentificationNumber(jTextField1.getText());
            customer.setTypeID(jComboBox1.getSelectedItem().toString());
            customer.setFirstName(jTextField2.getText());
            customer.setLastName(jTextField3.getText());
            customer.setBirthDate(formattedBirthDate);
            customer.setAge(Integer.parseInt(jTextField5.getText()));
            customer.setCityID(Integer.parseInt(jTextField6.getText()));
            customer.setNeighborhoodID(Integer.parseInt(jTextField7.getText()));

            updateCustomerUseCase.execute(customer);
            JOptionPane.showMessageDialog(this, "Customer updated successfully!");
            resetFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        hideComponents();
    }

    private void findCustomer() {
        String idToUpdate = jTextField1.getText();
        Optional<CustomerDto> customerToUpdate = findCustomerByIdUseCase.execute(idToUpdate);
        if (customerToUpdate.isPresent()) {
            CustomerDto foundCustomer = customerToUpdate.get();
            String typeIdBox = foundCustomer.getTypeDescription();

            try {
                jComboBox1.setSelectedItem(typeIdBox);
                jTextField2.setText(foundCustomer.getFirstName());
                jTextField3.setText(foundCustomer.getLastName());

                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedBirthDate = outputFormat.format(inputFormat.parse(foundCustomer.getBirthDate()));
                jTextField4.setText(formattedBirthDate);
                jTextField5.setText(Integer.toString(foundCustomer.getAge()));
                jTextField6.setText(Integer.toString(foundCustomer.getCityID()));
                jTextField7.setText(Integer.toString(foundCustomer.getNeighborhoodID()));
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
        jComboBox1.setVisible(false);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jTextField5.setVisible(false);
        jTextField6.setVisible(false);
        jTextField7.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
    }

    private void showComponents() {
        jComboBox1.setVisible(true);
        jTextField2.setVisible(true);
        jTextField3.setVisible(true);
        jTextField4.setVisible(true);
        jTextField5.setVisible(true);
        jTextField6.setVisible(true);
        jTextField7.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
}