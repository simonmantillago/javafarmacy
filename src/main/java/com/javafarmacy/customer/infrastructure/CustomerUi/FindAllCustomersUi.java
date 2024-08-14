package com.javafarmacy.customer.infrastructure.CustomerUi;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.javafarmacy.customer.application.FindAllCustomerUseCase;
import com.javafarmacy.customer.domain.entity.Customer;

import java.awt.*;
import java.util.List;


public class FindAllCustomersUi {
    private final FindAllCustomerUseCase findAllCustomerUseCase;
    private final CustomerCrudUi customerCrudUi;
    private JFrame frame;

    public FindAllCustomersUi(FindAllCustomerUseCase findAllCustomerUseCase, CustomerCrudUi customerCrudUi) {
        this.findAllCustomerUseCase = findAllCustomerUseCase;
        this.customerCrudUi = customerCrudUi;
    }

    public void showAllCustomers() {
        frame = new JFrame("All Customers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("All Customers");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JTable customerTable = createCustomerTable();
        JScrollPane scrollPane = new JScrollPane(customerTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            customerCrudUi.showFrame();
        });
        mainPanel.add(backButton, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JTable createCustomerTable() {
        String[] columnNames = {"ID Number", "Type ID", "First Name", "Last Name", "Age", "Birth Date", "City", "Neighborhood"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Customer> customers = findAllCustomerUseCase.execute();
        if (!customers.isEmpty()) {
            for (Customer customer : customers) {
                Object[] rowData = {
                    customer.getIdcustomer(),
                    customer.getNamecustomer(),
                    customer.getLastnamecustomer(),
                    customer.getCodecitycustomer(),
                    customer.getEmailcustomer(),
                    customer.getBirthDate(),
                    customer.getLon(),
                    customer.getLatitud()
                };
                model.addRow(rowData);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No customers found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }
}