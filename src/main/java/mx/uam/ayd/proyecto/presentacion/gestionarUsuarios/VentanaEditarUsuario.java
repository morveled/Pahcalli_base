package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaEditarUsuario extends JDialog {

    private ControlGestionarUsuarios controlGestionarUsuarios;
    private UsuarioTabla usuario;

    private JTextField campoNumeroEmpleado;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoContrasena;
    private JTextField campoCorreo;
    private JTextField campoTelefono;
    private JTextField campoPuesto;
    private JTextField campoSucursal;

    private JButton botonCerrar;
    private JButton botonGuardar;

    public VentanaEditarUsuario(JFrame parent, UsuarioTabla usuario, ControlGestionarUsuarios control) {
        super(parent, "Editar usuario", true);
        this.usuario = usuario;
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

        // Fila 1
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Número de empleado:"), gbc);
        gbc.gridx = 1;
        campoNumeroEmpleado = new JTextField(20);
        campoNumeroEmpleado.setText(usuario.getNumeroEmpleado());
        add(campoNumeroEmpleado, gbc);

        // Fila 2
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Nombre(s):"), gbc);
        gbc.gridx = 1;
        campoNombre = new JTextField(20);
        campoNombre.setText(usuario.getNombre());
        add(campoNombre, gbc);

        // Fila 3
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Apellido(s):"), gbc);
        gbc.gridx = 1;
        campoApellido = new JTextField(20);
        campoApellido.setText(usuario.getApellido());
        add(campoApellido, gbc);

        // Fila 4
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        campoContrasena = new JTextField(20);
        campoContrasena.setText(usuario.getContrasena());
        add(campoContrasena, gbc);

        // Fila 5
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1;
        campoCorreo = new JTextField(20);
        campoCorreo.setText(usuario.getCorreo());
        add(campoCorreo, gbc);

        // Fila 6
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        campoTelefono = new JTextField(20);
        campoTelefono.setText(usuario.getTelefono());
        add(campoTelefono, gbc);

        // Fila 7
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Puesto:"), gbc);
        gbc.gridx = 1;
        campoPuesto = new JTextField(20);
        campoPuesto.setText(usuario.getPuesto());
        add(campoPuesto, gbc);

        // Fila 8
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Sucursal:"), gbc);
        gbc.gridx = 1;
        campoSucursal = new JTextField(20);
        campoSucursal.setText(usuario.getSucursal());
        add(campoSucursal, gbc);

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
                // Guardar los cambios en el objeto usuario
                usuario.setNumeroEmpleado(campoNumeroEmpleado.getText());
                usuario.setNombre(campoNombre.getText());
                usuario.setApellido(campoApellido.getText());
                usuario.setContrasena(campoContrasena.getText());
                usuario.setCorreo(campoCorreo.getText());
                usuario.setTelefono(campoTelefono.getText());
                usuario.setPuesto(campoPuesto.getText());
                usuario.setSucursal(campoSucursal.getText());

                // Enviar los cambios al controlador
                controlGestionarUsuarios.actualizarUsuario(usuario);
                dispose();
            }
        });

        setSize(500, 500);
        setLocationRelativeTo(parent);
    }
}