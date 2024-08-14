package com.javafarmacy.customer.infrastructure.CustomerUi;


import javax.swing.*;

import com.javafarmacy.customer.application.CreateCustomerUseCase;
import com.javafarmacy.customer.application.DeleteCustomerUseCase;
import com.javafarmacy.customer.application.FindAllCustomerUseCase;
import com.javafarmacy.customer.application.FindCustomerByIdUseCase;
import com.javafarmacy.customer.application.UpdateCustomerUseCase;

import java.awt.*;

public class CustomerCrudUi {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final FindAllCustomerUseCase findAllCustomerUseCase;
    private JFrame frame;


    public CustomerCrudUi(CreateCustomerUseCase createCustomerUseCase, FindCustomerByIdUseCase findCustomerByIdUseCase,
            UpdateCustomerUseCase updateCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase,FindAllCustomerUseCase findAllCustomerUseCase ) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.findAllCustomerUseCase = findAllCustomerUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("Customer Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Customers");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Estilo común para los botones
        Dimension buttonSize = new Dimension(250, 50);
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        // Botón Create Customer
        JButton btnCreate = createStyledButton("Create Customer", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateCustomerUi customerUi = new CreateCustomerUi(createCustomerUseCase, this);
            customerUi.frmRegCustomer();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find Customer", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindCustomerUi findCustomerUi = new FindCustomerUi(findCustomerByIdUseCase, this);
            findCustomerUi.showFindCustomer();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update Customer", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateCustomerUi updateCustomerUi = new UpdateCustomerUi(findCustomerByIdUseCase, updateCustomerUseCase, this);
            updateCustomerUi.frmUpdateCustomer();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFindAll = createStyledButton("Find All Customers", buttonSize, buttonFont);
        btnFindAll.addActionListener(e -> {
            FindAllCustomersUi findAllCustomersUi = new FindAllCustomersUi(findAllCustomerUseCase, this);
            findAllCustomersUi.showAllCustomers();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFindAll);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnDelete = createStyledButton("Delete Customer", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteCustomerUi deleteCustomerUi = new DeleteCustomerUi(deleteCustomerUseCase, this);
            deleteCustomerUi.showDeleteCustomer();
            frame.setVisible(false);
        });
        buttonPanel.add(btnDelete);


        mainPanel.add(buttonPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void showFrame() {
        if (frame != null) {
            frame.setVisible(true);
        } else {
            showCrudOptions();
        }
    }
    private JButton createStyledButton(String text, Dimension size, Font font) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setFont(font);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
}