package com.javafarmacy.activeprinciple.infrastructure.activePrincipleUi;

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

import com.javafarmacy.activeprinciple.application.CreateActiveprincipleUseCase;
import com.javafarmacy.activeprinciple.application.DeleteActiveprincipleUseCase;
import com.javafarmacy.activeprinciple.application.FindActiveprincipleByIdUseCase;
import com.javafarmacy.activeprinciple.application.UpdateActiveprincipleUseCase;

public class ActiveprincipleUiController {
    private final CreateActiveprincipleUseCase createActiveprincipleUseCase;
    private final FindActiveprincipleByIdUseCase findActiveprincipleByIdUseCase;
    private final UpdateActiveprincipleUseCase updateActiveprincipleUseCase;
    private final DeleteActiveprincipleUseCase deleteActiveprincipleUseCase;
    private JFrame frame;

    



    public ActiveprincipleUiController(CreateActiveprincipleUseCase createActiveprincipleUseCase, FindActiveprincipleByIdUseCase findActiveprincipleByIdUseCase,
            UpdateActiveprincipleUseCase updateActiveprincipleUseCase, DeleteActiveprincipleUseCase deleteActiveprincipleUseCase) {
        this.createActiveprincipleUseCase = createActiveprincipleUseCase;
        this.findActiveprincipleByIdUseCase = findActiveprincipleByIdUseCase;
        this.updateActiveprincipleUseCase = updateActiveprincipleUseCase;
        this.deleteActiveprincipleUseCase = deleteActiveprincipleUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("Activeprinciple Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Components");
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

        // Botón Create Activeprinciple
        JButton btnCreate = createStyledButton("Create Activeprinciple", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateActiveprincipleUi activeprincipleUi = new CreateActiveprincipleUi(createActiveprincipleUseCase, this);
            activeprincipleUi.frmRegActiveprinciple();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find Activeprinciple", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindActiveprincipleByIdUi findActiveprincipleUi = new FindActiveprincipleByIdUi(findActiveprincipleByIdUseCase, this);
            findActiveprincipleUi.showFindActiveprinciple();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update Activeprinciple", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateActiveprincipleUi updateActiveprincipleUi = new UpdateActiveprincipleUi(updateActiveprincipleUseCase, findActiveprincipleByIdUseCase, this);
            updateActiveprincipleUi.frmUpdateActiveprinciple();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnDelete = createStyledButton("Delete Activeprinciple", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteActiveprincipleUi deleteActiveprincipleUi = new DeleteActiveprincipleUi(deleteActiveprincipleUseCase, this);
            deleteActiveprincipleUi.showDeleteActiveprinciple();
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
