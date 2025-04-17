package mx.uam.ayd.proyecto.presentacion.agregarUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEmpleado;
import mx.uam.ayd.proyecto.presentacion.gestionarUsuarios.ControlGestionarUsuarios;

@SuppressWarnings("serial")
public class VentanaAgregarUsuario extends JDialog {

    private ControlGestionarUsuarios controlGestionarUsuarios;
    private List<TipoEmpleado> tiposEmpleado;

    private JTextField campoNumeroEmpleado;
    private JTextField campoNombre;
    private JTextField campoApellidoPaterno;
    private JTextField campoApellidoMaterno;
    private JTextField campoCorreo;
    private JTextField campoTelefono;
    private JComboBox<String> comboPuesto;
    private JTextField campoSucursal;

    private JButton botonCerrar;
    private JButton botonAgregar;

    public VentanaAgregarUsuario(JFrame parent, ControlGestionarUsuarios control, List<TipoEmpleado> tiposEmpleado) {
        super(parent, "Agregar nuevo usuario", true);
        this.controlGestionarUsuarios = control;
        this.tiposEmpleado = tiposEmpleado;

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
        campoApellidoPaterno = addField("Apellido paterno:", ++gbc.gridy, gbc);
        campoApellidoMaterno = addField("Apellido materno:", ++gbc.gridy, gbc);
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
            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setNumeroEmpleado(campoNumeroEmpleado.getText());
            nuevoEmpleado.setNombre(campoNombre.getText());
            nuevoEmpleado.setApellidoPaterno(campoApellidoPaterno.getText());
            nuevoEmpleado.setApellidoMaterno(campoApellidoMaterno.getText());
            nuevoEmpleado.setCorreoElectronico(campoCorreo.getText());
            nuevoEmpleado.setTelefono(campoTelefono.getText());

            // Obtener TipoEmpleado por nombre seleccionado
            String tipoSeleccionado = (String) comboPuesto.getSelectedItem();
            TipoEmpleado tipo = tiposEmpleado.stream()
                    .filter(t -> t.getNombre().equalsIgnoreCase(tipoSeleccionado))
                    .findFirst()
                    .orElse(null);
            nuevoEmpleado.setTipo(tipo);

            // Enviar a persistencia desde el controlador
            controlGestionarUsuarios.agregaEmpleado(nuevoEmpleado);

            JOptionPane.showMessageDialog(VentanaAgregarUsuario.this, "Empleado agregado exitosamente");
            dispose();
        });

        setSize(500, 600);
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
