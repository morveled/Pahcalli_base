package mx.uam.ayd.proyecto.presentacion.agregarUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import mx.uam.ayd.proyecto.negocio.modelo.TipoEmpleado;
import mx.uam.ayd.proyecto.presentacion.gestionarUsuarios.ControlGestionarUsuarios;
import mx.uam.ayd.proyecto.presentacion.gestionarUsuarios.UsuarioTabla;

@SuppressWarnings("serial")
public class VentanaAgregarUsuario extends JDialog {

    private ControlGestionarUsuarios controlGestionarUsuarios;

    private JTextField campoNumeroEmpleado;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoContrasena;
    private JTextField campoCorreo;
    private JTextField campoTelefono;
    private JComboBox<String> comboPuesto;
    private JTextField campoSucursal;

    private JButton botonCerrar;
    private JButton botonAgregar;

    public VentanaAgregarUsuario(JFrame parent, ControlGestionarUsuarios control, List<TipoEmpleado> tiposEmpleado) {
        super(parent, "Agregar nuevo usuario", true);
        this.controlGestionarUsuarios = control;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("Agregar nuevo usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        JLabel subtitulo = new JLabel("Por favor llena el siguiente formulario");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(titulo, gbc);

        gbc.gridy++;
        add(subtitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        campoNumeroEmpleado = addField("Número de empleado:", ++gbc.gridy, gbc);
        campoNombre = addField("Nombre(s):", ++gbc.gridy, gbc);
        campoApellido = addField("Apellido(s):", ++gbc.gridy, gbc);
        campoContrasena = addField("Contraseña:", ++gbc.gridy, gbc);
        campoCorreo = addField("Correo:", ++gbc.gridy, gbc);
        campoTelefono = addField("Teléfono:", ++gbc.gridy, gbc);
        comboPuesto = addComboBox("Puesto:", ++gbc.gridy, gbc, tiposEmpleado);
        campoSucursal = addField("Sucursal:", ++gbc.gridy, gbc);

        // Botones
        gbc.gridy++;
        gbc.gridx = 0;
        botonCerrar = new JButton("Cerrar");
        add(botonCerrar, gbc);

        gbc.gridx = 1;
        botonAgregar = new JButton("Agregar usuario");
        add(botonAgregar, gbc);

        botonCerrar.addActionListener(e -> dispose());

        botonAgregar.addActionListener((ActionEvent e) -> {
            UsuarioTabla nuevoUsuario = new UsuarioTabla(
                campoNumeroEmpleado.getText(),
                campoNombre.getText(),
                campoApellido.getText(),
                campoContrasena.getText(),
                campoCorreo.getText(),
                campoTelefono.getText(),
                comboPuesto.getSelectedItem().toString(),
                campoSucursal.getText()
            );

            controlGestionarUsuarios.agregaUsuario(nuevoUsuario);
            JOptionPane.showMessageDialog(VentanaAgregarUsuario.this, "Usuario agregado exitosamente");
            dispose();
        });

        setSize(500, 500);
        setLocationRelativeTo(parent);
    }

    private JTextField addField(String label, int y, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        JTextField field = new JTextField(20);
        add(field, gbc);
        return field;
    }

    private JComboBox<String> addComboBox(String label, int y, GridBagConstraints gbc, List<TipoEmpleado> tipos) {
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        JComboBox<String> comboBox = new JComboBox<>();
        for (TipoEmpleado tipo : tipos) {
            comboBox.addItem(tipo.getNombre());
        }
        add(comboBox, gbc);
        return comboBox;
    }
}
