package com.javafarmacy.country.infrastructure.CountryUi;

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

import com.javafarmacy.country.application.CreateCountryUseCase;
import com.javafarmacy.country.application.FindCountryByIdUseCase;
import com.javafarmacy.country.application.UpdateCountryUseCase;

public class CountryUiController {
    private final CreateCountryUseCase createCountryUseCase;
    private final FindCountryByIdUseCase findCountryByIdUseCase;
    private final UpdateCountryUseCase updateCountryUseCase;
    private JFrame frame;

    

    public CountryUiController(CreateCountryUseCase createCountryUseCase, FindCountryByIdUseCase findCountryByIdUseCase,
            UpdateCountryUseCase updateCountryUseCase) {
        this.createCountryUseCase = createCountryUseCase;
        this.findCountryByIdUseCase = findCountryByIdUseCase;
        this.updateCountryUseCase = updateCountryUseCase;
    }

    public void showCrudOptions() {
        frame = new JFrame("Country Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        // Crear un panel principal con BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir título grande
        JLabel titleLabel = new JLabel("Countries");
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

        // Botón Create Country
        JButton btnCreate = createStyledButton("Create Country", buttonSize, buttonFont);
        btnCreate.addActionListener(e -> {
            CreateCountryUi countryUi = new CreateCountryUi(createCountryUseCase, this);
            countryUi.frmRegCountry();
            frame.setVisible(false);
        });
        buttonPanel.add(btnCreate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton btnUpdate = createStyledButton("Update Country", buttonSize, buttonFont);
        btnUpdate.addActionListener(e -> {
            UpdateCountryUi updateCountryUi = new UpdateCountryUi(updateCountryUseCase, findCountryByIdUseCase, this);
            updateCountryUi.frmUpdateCountry();
            frame.setVisible(false);
        });
        buttonPanel.add(btnUpdate);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

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
