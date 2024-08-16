package com.javafarmacy.modeadministration.infrastructure.modeAdministrationUi;
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

import com.javafarmacy.modeadministration.application.CreateModeAdministrationUseCase;
import com.javafarmacy.modeadministration.application.DeleteModeAdministrationUseCase;
import com.javafarmacy.modeadministration.application.FindModeAdministrationByIdUseCase;
import com.javafarmacy.modeadministration.application.UpdateModeAdministrationUseCase;

public class ModeAdministrationUiController {
    private final CreateModeAdministrationUseCase createModeAdministrationUseCase;
    private final FindModeAdministrationByIdUseCase findModeAdministrationByIdUseCase;
    private final UpdateModeAdministrationUseCase updateModeAdministrationUseCase;
    private final DeleteModeAdministrationUseCase deleteModeAdministrationUseCase;
    private JFrame frame;

    



    public ModeAdministrationUiController(CreateModeAdministrationUseCase createModeAdministrationUseCase, FindModeAdministrationByIdUseCase findModeAdministrationByIdUseCase,
            UpdateModeAdministrationUseCase updateModeAdministrationUseCase, DeleteModeAdministrationUseCase deleteModeAdministrationUseCase) {
        this.createModeAdministrationUseCase = createModeAdministrationUseCase;
        this.findModeAdministrationByIdUseCase = findModeAdministrationByIdUseCase;
        this.updateModeAdministrationUseCase = updateModeAdministrationUseCase;
        this.deleteModeAdministrationUseCase = deleteModeAdministrationUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("ModeAdministration Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Administration");
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

        // Botón Create ModeAdministration
        JButton btnCreate = createStyledButton("Create ModeAdministration", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateModeAdministrationUi modeAdministrationUi = new CreateModeAdministrationUi(createModeAdministrationUseCase, this);
            modeAdministrationUi.frmRegModeAdministration();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find ModeAdministration", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindModeAdministrationByIdUi findModeAdministrationUi = new FindModeAdministrationByIdUi(findModeAdministrationByIdUseCase, this);
            findModeAdministrationUi.showFindModeAdministration();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update ModeAdministration", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateModeAdministrationUi updateModeAdministrationUi = new UpdateModeAdministrationUi(updateModeAdministrationUseCase, findModeAdministrationByIdUseCase,this);
            updateModeAdministrationUi.frmUpdateModeAdministration();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnDelete = createStyledButton("Delete ModeAdministration", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteModeAdministrationUi deleteModeAdministrationUi = new DeleteModeAdministrationUi(deleteModeAdministrationUseCase, this);
            deleteModeAdministrationUi.showDeleteModeAdministration();
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
