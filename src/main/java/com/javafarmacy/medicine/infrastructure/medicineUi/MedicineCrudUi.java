package com.javafarmacy.medicine.infrastructure.medicineUi;


import javax.swing.*;

import com.javafarmacy.medicine.application.CreateMedicineUseCase;
import com.javafarmacy.medicine.application.DeleteMedicineUseCase;
import com.javafarmacy.medicine.application.FindMedicineByIdUseCase;
import com.javafarmacy.medicine.application.UpdateMedicineUseCase;

import java.awt.*;

public class MedicineCrudUi {
    private final CreateMedicineUseCase createMedicineUseCase;
    private final FindMedicineByIdUseCase findMedicineByIdUseCase;
    private final UpdateMedicineUseCase updateMedicineUseCase;
    private final DeleteMedicineUseCase deleteMedicineUseCase;

    private JFrame frame;


    public MedicineCrudUi(CreateMedicineUseCase createMedicineUseCase, FindMedicineByIdUseCase findMedicineByIdUseCase,
            UpdateMedicineUseCase updateMedicineUseCase, DeleteMedicineUseCase deleteMedicineUseCase) {
        this.createMedicineUseCase = createMedicineUseCase;
        this.findMedicineByIdUseCase = findMedicineByIdUseCase;
        this.updateMedicineUseCase = updateMedicineUseCase;
        this.deleteMedicineUseCase = deleteMedicineUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("Medicine Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Medicines");
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

        // Botón Create Medicine
        JButton btnCreate = createStyledButton("Create Medicine", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateMedicineUi medicineUi = new CreateMedicineUi(createMedicineUseCase, this);
            medicineUi.frmRegMedicine();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find Medicine", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindMedicineUi findMedicineUi = new FindMedicineUi(findMedicineByIdUseCase, this);
            findMedicineUi.showFindMedicine();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update Medicine", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateMedicineUi updateMedicineUi = new UpdateMedicineUi(findMedicineByIdUseCase, updateMedicineUseCase, this);
            updateMedicineUi.frmUpdateMedicine();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));



        JButton btnDelete = createStyledButton("Delete Medicine", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteMedicineUi deleteMedicineUi = new DeleteMedicineUi(deleteMedicineUseCase, this);
            deleteMedicineUi.showDeleteMedicine();
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