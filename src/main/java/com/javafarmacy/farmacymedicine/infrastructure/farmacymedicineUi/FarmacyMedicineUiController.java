package com.javafarmacy.farmacymedicine.infrastructure.farmacymedicineUi;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.javafarmacy.farmacymedicine.application.CreateFarmacyMedicineUseCase;
import com.javafarmacy.farmacymedicine.application.DeleteFarmacyMedicineUseCase;
import com.javafarmacy.farmacymedicine.application.FindFarmacyMedicineByIdUseCase;
import com.javafarmacy.farmacymedicine.application.UpdateFarmacyMedicineUseCase;

public class FarmacyMedicineUiController {
    private final CreateFarmacyMedicineUseCase createFarmacyMedicineUseCase;
    private final FindFarmacyMedicineByIdUseCase findFarmacyMedicineByIdUseCase;
    private final UpdateFarmacyMedicineUseCase updateFarmacyMedicineUseCase;
    private final DeleteFarmacyMedicineUseCase deleteFarmacyMedicineUseCase;
    private JFrame frame;

    



    public FarmacyMedicineUiController(CreateFarmacyMedicineUseCase createFarmacyMedicineUseCase, FindFarmacyMedicineByIdUseCase findFarmacyMedicineByIdUseCase,
            UpdateFarmacyMedicineUseCase updateFarmacyMedicineUseCase, DeleteFarmacyMedicineUseCase deleteFarmacyMedicineUseCase) {
        this.createFarmacyMedicineUseCase = createFarmacyMedicineUseCase;
        this.findFarmacyMedicineByIdUseCase = findFarmacyMedicineByIdUseCase;
        this.updateFarmacyMedicineUseCase = updateFarmacyMedicineUseCase;
        this.deleteFarmacyMedicineUseCase = deleteFarmacyMedicineUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("FarmacyMedicine Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("FarmaMeds");
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

        // Botón Create FarmacyMedicine
        JButton btnCreate = createStyledButton("Create FarmacyMedicine", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateFarmacyMedicineUi farmacyMedicineUi = new CreateFarmacyMedicineUi(createFarmacyMedicineUseCase, this);
            farmacyMedicineUi.frmRegFarmacyMedicine();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find FarmacyMedicine", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindFarmacyMedicineByIdUi findFarmacyMedicineUi = new FindFarmacyMedicineByIdUi(findFarmacyMedicineByIdUseCase, this);
            findFarmacyMedicineUi.showFindFarmacyMedicine();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update FarmacyMedicine", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateFarmacyMedicineUi updateFarmacyMedicineUi = new UpdateFarmacyMedicineUi(updateFarmacyMedicineUseCase, findFarmacyMedicineByIdUseCase, this);
            updateFarmacyMedicineUi.frmUpdateFarmacyMedicine();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnDelete = createStyledButton("Delete FarmacyMedicine", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteFarmacyMedicineUi deleteFarmacyMedicineUi = new DeleteFarmacyMedicineUi(deleteFarmacyMedicineUseCase, this);
            deleteFarmacyMedicineUi.showDeleteFarmacyMedicine();
            frame.setVisible(false);
        });
        buttonPanel.add(btnDelete);

        mainPanel.add(buttonPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
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
