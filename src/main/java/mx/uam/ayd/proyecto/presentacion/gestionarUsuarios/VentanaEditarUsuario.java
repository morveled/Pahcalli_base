package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@SuppressWarnings("serial")
public class VentanaEditarUsuario extends JDialog {

    private ControlGestionarUsuarios controlGestionarUsuarios;
    private Empleado empleado;

    private JTextField campoNumeroEmpleado;
    private JTextField campoNombre;
    private JTextField campoApellidoPaterno;
    private JTextField campoApellidoMaterno;
    private JTextField campoCorreo;
    private JTextField campoTelefono;

    private JButton botonCerrar;
    private JButton botonGuardar;

    public VentanaEditarUsuario(JFrame parent, Empleado empleado, ControlGestionarUsuarios control) {
        super(parent, "Editar usuario", true);
        this.empleado = empleado;
        this.controlGestionarUsuarios = control;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("Editar usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        JLabel subtitulo = new JLabel("Por favor, edite los campos que desea modificar.");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(titulo, gbc);

        gbc.gridy++;
        add(subtitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 1: Número de empleado
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Número de empleado:"), gbc);
        gbc.gridx = 1;
        campoNumeroEmpleado = new JTextField(20);
        campoNumeroEmpleado.setText(empleado.getNumeroEmpleado());
        add(campoNumeroEmpleado, gbc);

        // Fila 2: Nombre
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Nombre(s):"), gbc);
        gbc.gridx = 1;
        campoNombre = new JTextField(20);
        campoNombre.setText(empleado.getNombre());
        add(campoNombre, gbc);

        // Fila 3: Apellido paterno
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Apellido paterno:"), gbc);
        gbc.gridx = 1;
        campoApellidoPaterno = new JTextField(20);
        campoApellidoPaterno.setText(empleado.getApellidoPaterno());
        add(campoApellidoPaterno, gbc);

        // Fila 4: Apellido materno
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Apellido materno:"), gbc);
        gbc.gridx = 1;
        campoApellidoMaterno = new JTextField(20);
        campoApellidoMaterno.setText(empleado.getApellidoMaterno());
        add(campoApellidoMaterno, gbc);

        // Fila 5: Correo
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Correo electrónico:"), gbc);
        gbc.gridx = 1;
        campoCorreo = new JTextField(20);
        campoCorreo.setText(empleado.getCorreoElectronico());
        add(campoCorreo, gbc);

        // Fila 6: Teléfono
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        campoTelefono = new JTextField(20);
        campoTelefono.setText(empleado.getTelefono());
        add(campoTelefono, gbc);

        // Botones
        gbc.gridy++;
        gbc.gridx = 0;
        botonCerrar = new JButton("Cerrar");
        add(botonCerrar, gbc);

        gbc.gridx = 1;
        botonGuardar = new JButton("Guardar cambios");
        add(botonGuardar, gbc);

        // Eventos
        botonCerrar.addActionListener(e -> dispose());

        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Actualizar datos del empleado
                empleado.setNumeroEmpleado(campoNumeroEmpleado.getText());
                empleado.setNombre(campoNombre.getText());
                empleado.setApellidoPaterno(campoApellidoPaterno.getText());
                empleado.setApellidoMaterno(campoApellidoMaterno.getText());
                empleado.setCorreoElectronico(campoCorreo.getText());
                empleado.setTelefono(campoTelefono.getText());

                // Enviar cambios al controlador para persistir
                controlGestionarUsuarios.actualizarEmpleado(empleado);
                dispose();
            }
        });

        setSize(500, 500);
        setLocationRelativeTo(parent);
    }
}
