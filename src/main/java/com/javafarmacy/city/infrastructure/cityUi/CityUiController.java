package com.javafarmacy.city.infrastructure.cityUi;

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

import com.javafarmacy.city.application.CreateCityUseCase;
import com.javafarmacy.city.application.DeleteCityUseCase;
import com.javafarmacy.city.application.FindCityByIdUseCase;
import com.javafarmacy.city.application.UpdateCityUseCase;

public class CityUiController {
    private final CreateCityUseCase createCityUseCase;
    private final FindCityByIdUseCase findCityByIdUseCase;
    private final UpdateCityUseCase updateCityUseCase;
    private final DeleteCityUseCase deleteCityUseCase;
    private JFrame frame;

    



    public CityUiController(CreateCityUseCase createCityUseCase, FindCityByIdUseCase findCityByIdUseCase,
            UpdateCityUseCase updateCityUseCase, DeleteCityUseCase deleteCityUseCase) {
        this.createCityUseCase = createCityUseCase;
        this.findCityByIdUseCase = findCityByIdUseCase;
        this.updateCityUseCase = updateCityUseCase;
        this.deleteCityUseCase = deleteCityUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("City Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Cities");
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

        // Botón Create City
        JButton btnCreate = createStyledButton("Create City", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateCityUi cityUi = new CreateCityUi(createCityUseCase, this);
            cityUi.frmRegCity();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnFind = createStyledButton("Find City", buttonSize, buttonFont);
        btnFind.addActionListener(e -> {
            FindCityByIdUi findCityUi = new FindCityByIdUi(findCityByIdUseCase, this);
            findCityUi.showFindCity();
            frame.setVisible(false);
        });
        buttonPanel.add(btnFind);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update City", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateCityUi updateCityUi = new UpdateCityUi(updateCityUseCase, findCityByIdUseCase, this);
            updateCityUi.frmUpdateCity();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnDelete = createStyledButton("Delete City", buttonSize, buttonFont);
        btnDelete.addActionListener(e -> {
            DeleteCityUi deleteCityUi = new DeleteCityUi(deleteCityUseCase, this);
            deleteCityUi.showDeleteCity();
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
