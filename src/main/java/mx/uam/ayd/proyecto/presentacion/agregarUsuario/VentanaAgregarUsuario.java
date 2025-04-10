package mx.uam.ayd.proyecto.presentacion.agregarUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaAgregarUsuario extends JDialog {

    public VentanaAgregarUsuario(JFrame parent) {
        super(parent, "Agregar nuevo usuario", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("Agrega usuario nuevo");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        JLabel subtitulo = new JLabel("Por favor llene el formulario");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(titulo, gbc);

        gbc.gridy++;
        add(subtitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Campos simples (puedes mejorar esto después)
        String[] labels = {"Número de empleado", "Nombre(s)", "Apellido(s)", "Contraseña", "Correo", "Teléfono", "Puesto", "Sucursal"};
        for (String label : labels) {
            gbc.gridy++;
            gbc.gridx = 0;
            add(new JLabel(label + ":"), gbc);
            gbc.gridx = 1;
            add(new JTextField(20), gbc);
        }

        gbc.gridy++;
        gbc.gridx = 0;
        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> dispose());
        add(botonCerrar, gbc);

        gbc.gridx = 1;
        JButton botonAgregar = new JButton("Agregar usuario");
        botonAgregar.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Usuario agregado (simulación)", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
        add(botonAgregar, gbc);

        setSize(500, 500);
        setLocationRelativeTo(parent);
    }
}
