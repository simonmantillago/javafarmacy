package com.javafarmacy.labatory.infrastructure.labatoryUi;

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

import com.javafarmacy.labatory.application.CreateLabatoryUseCase;
import com.javafarmacy.labatory.application.DeleteLabatoryUseCase;
import com.javafarmacy.labatory.application.FindLabatoryByIdUseCase;
import com.javafarmacy.labatory.application.UpdateLabatoryUseCase;

public class LabatoryUiController {
    private final CreateLabatoryUseCase createLabatoryUseCase;
    private final FindLabatoryByIdUseCase findLabatoryByIdUseCase;
    private final UpdateLabatoryUseCase updateLabatoryUseCase;
    private final DeleteLabatoryUseCase deleteLabatoryUseCase;
    private JFrame frame;

    



    public LabatoryUiController(CreateLabatoryUseCase createLabatoryUseCase, FindLabatoryByIdUseCase findLabatoryByIdUseCase,
            UpdateLabatoryUseCase updateLabatoryUseCase, DeleteLabatoryUseCase deleteLabatoryUseCase) {
        this.createLabatoryUseCase = createLabatoryUseCase;
        this.findLabatoryByIdUseCase = findLabatoryByIdUseCase;
        this.updateLabatoryUseCase = updateLabatoryUseCase;
        this.deleteLabatoryUseCase = deleteLabatoryUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("Labatory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Laboratories");
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

        // Botón Create Labatory
        JButton btnCreate = createStyledButton("Create Labatory", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateLabatoryUi labatoryUi = new CreateLabatoryUi(createLabatoryUseCase, this);
            labatoryUi.frmRegLabatory();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find Labatory", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindLabatoryByIdUi findLabatoryUi = new FindLabatoryByIdUi(findLabatoryByIdUseCase, this);
            findLabatoryUi.showFindLabatory();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update Labatory", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateLabatoryUi updateLabatoryUi = new UpdateLabatoryUi(updateLabatoryUseCase, findLabatoryByIdUseCase, this);
            updateLabatoryUi.frmUpdateLabatory();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnDelete = createStyledButton("Delete Labatory", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteLabatoryUi deleteLabatoryUi = new DeleteLabatoryUi(deleteLabatoryUseCase, this);
            deleteLabatoryUi.showDeleteLabatory();
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
