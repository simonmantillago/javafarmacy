package com.javafarmacy.unitmeasurement.infrastructure.unitMesurementUi;

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

import com.javafarmacy.unitmeasurement.application.CreateUnitMeasurementUseCase;
import com.javafarmacy.unitmeasurement.application.DeleteUnitMeasurementUseCase;
import com.javafarmacy.unitmeasurement.application.FindUnitMeasurementByIdUseCase;
import com.javafarmacy.unitmeasurement.application.UpdateUnitMeasurementUseCase;

public class UnitMeasurementUiController {
    private final CreateUnitMeasurementUseCase createUnitMeasurementUseCase;
    private final FindUnitMeasurementByIdUseCase findUnitMeasurementByIdUseCase;
    private final UpdateUnitMeasurementUseCase updateUnitMeasurementUseCase;
    private final DeleteUnitMeasurementUseCase deleteUnitMeasurementUseCase;
    private JFrame frame;

    



    public UnitMeasurementUiController(CreateUnitMeasurementUseCase createUnitMeasurementUseCase, FindUnitMeasurementByIdUseCase findUnitMeasurementByIdUseCase,
            UpdateUnitMeasurementUseCase updateUnitMeasurementUseCase, DeleteUnitMeasurementUseCase deleteUnitMeasurementUseCase) {
        this.createUnitMeasurementUseCase = createUnitMeasurementUseCase;
        this.findUnitMeasurementByIdUseCase = findUnitMeasurementByIdUseCase;
        this.updateUnitMeasurementUseCase = updateUnitMeasurementUseCase;
        this.deleteUnitMeasurementUseCase = deleteUnitMeasurementUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("UnitMeasurement Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Units");
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

        // Botón Create UnitMeasurement
        JButton btnCreate = createStyledButton("Create UnitMeasurement", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateUnitMeasurementUi unitMeasurementUi = new CreateUnitMeasurementUi(createUnitMeasurementUseCase, this);
            unitMeasurementUi.frmRegUnitMeasurement();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find UnitMeasurement", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindUnitMeasurementByIdUi findUnitMeasurementUi = new FindUnitMeasurementByIdUi(findUnitMeasurementByIdUseCase, this);
            findUnitMeasurementUi.showFindUnitMeasurement();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update UnitMeasurement", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateUnitMeasurementUi updateUnitMeasurementUi = new UpdateUnitMeasurementUi(updateUnitMeasurementUseCase, findUnitMeasurementByIdUseCase,this);
            updateUnitMeasurementUi.frmUpdateUnitMeasurement();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnDelete = createStyledButton("Delete UnitMeasurement", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteUnitMeasurementUi deleteUnitMeasurementUi = new DeleteUnitMeasurementUi(deleteUnitMeasurementUseCase, this);
            deleteUnitMeasurementUi.showDeleteUnitMeasurement();
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
