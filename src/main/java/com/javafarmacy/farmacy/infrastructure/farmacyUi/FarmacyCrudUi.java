package com.javafarmacy.farmacy.infrastructure.farmacyUi;


import javax.swing.*;

import com.javafarmacy.farmacy.application.CreateFarmacyUseCase;
import com.javafarmacy.farmacy.application.DeleteFarmacyUseCase;
import com.javafarmacy.farmacy.application.FindFarmacyByIdUseCase;
import com.javafarmacy.farmacy.application.UpdateFarmacyUseCase;

import java.awt.*;

public class FarmacyCrudUi {
    private final CreateFarmacyUseCase createFarmacyUseCase;
    private final FindFarmacyByIdUseCase findFarmacyByIdUseCase;
    private final UpdateFarmacyUseCase updateFarmacyUseCase;
    private final DeleteFarmacyUseCase deleteFarmacyUseCase;

    private JFrame frame;


    public FarmacyCrudUi(CreateFarmacyUseCase createFarmacyUseCase, FindFarmacyByIdUseCase findFarmacyByIdUseCase,
            UpdateFarmacyUseCase updateFarmacyUseCase, DeleteFarmacyUseCase deleteFarmacyUseCase) {
        this.createFarmacyUseCase = createFarmacyUseCase;
        this.findFarmacyByIdUseCase = findFarmacyByIdUseCase;
        this.updateFarmacyUseCase = updateFarmacyUseCase;
        this.deleteFarmacyUseCase = deleteFarmacyUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("Farmacy Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Farmacys");
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

        // Botón Create Farmacy
        JButton btnCreate = createStyledButton("Create Farmacy", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateFarmacyUi farmacyUi = new CreateFarmacyUi(createFarmacyUseCase, this);
            farmacyUi.frmRegFarmacy();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find Farmacy", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindFarmacyUi findFarmacyUi = new FindFarmacyUi(findFarmacyByIdUseCase, this);
            findFarmacyUi.showFindFarmacy();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update Farmacy", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateFarmacyUi updateFarmacyUi = new UpdateFarmacyUi(findFarmacyByIdUseCase, updateFarmacyUseCase, this);
            updateFarmacyUi.frmUpdateFarmacy();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));



        JButton btnDelete = createStyledButton("Delete Farmacy", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteFarmacyUi deleteFarmacyUi = new DeleteFarmacyUi(deleteFarmacyUseCase, this);
            deleteFarmacyUi.showDeleteFarmacy();
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