package com.javafarmacy.region.infrastructure.regionUi;

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

import com.javafarmacy.region.application.CreateRegionUseCase;
import com.javafarmacy.region.application.DeleteRegionUseCase;
import com.javafarmacy.region.application.FindRegionByIdUseCase;
import com.javafarmacy.region.application.UpdateRegionUseCase;

public class RegionUiController {
    private final CreateRegionUseCase createRegionUseCase;
    private final FindRegionByIdUseCase findRegionByIdUseCase;
    private final UpdateRegionUseCase updateRegionUseCase;
    private final DeleteRegionUseCase deleteRegionUseCase;
    private JFrame frame;

    



    public RegionUiController(CreateRegionUseCase createRegionUseCase, FindRegionByIdUseCase findRegionByIdUseCase,
            UpdateRegionUseCase updateRegionUseCase, DeleteRegionUseCase deleteRegionUseCase) {
        this.createRegionUseCase = createRegionUseCase;
        this.findRegionByIdUseCase = findRegionByIdUseCase;
        this.updateRegionUseCase = updateRegionUseCase;
        this.deleteRegionUseCase = deleteRegionUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("Region Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Regions");
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

        // Botón Create Region
        JButton btnCreate = createStyledButton("Create Region", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateRegionUi regionUi = new CreateRegionUi(createRegionUseCase, this);
            regionUi.frmRegRegion();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find Region", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindRegionByIdUi findRegionUi = new FindRegionByIdUi(findRegionByIdUseCase, this);
            findRegionUi.showFindRegion();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update Region", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateRegionUi updateRegionUi = new UpdateRegionUi(updateRegionUseCase, findRegionByIdUseCase, this);
            updateRegionUi.frmUpdateRegion();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnDelete = createStyledButton("Delete Region", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteRegionUi deleteRegionUi = new DeleteRegionUi(deleteRegionUseCase, this);
            deleteRegionUi.showDeleteRegion();
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
