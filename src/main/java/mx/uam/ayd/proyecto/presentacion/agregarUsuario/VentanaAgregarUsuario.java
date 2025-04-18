package mx.uam.ayd.proyecto.presentacion.agregarUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Sucursal;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEmpleado;
import mx.uam.ayd.proyecto.presentacion.gestionarUsuarios.ControlGestionarUsuarios;

@SuppressWarnings("serial")
public class VentanaAgregarUsuario extends JDialog {

    private ControlGestionarUsuarios controlGestionarUsuarios;
    private List<TipoEmpleado> tiposEmpleado;
    private List<Sucursal> sucursales;

    private JTextField campoNumeroEmpleado;
    private JTextField campoNombre;
    private JTextField campoApellidoPaterno;
    private JTextField campoApellidoMaterno;
    private JTextField campoCorreo;
    private JTextField campoTelefono;
    private JComboBox<String> comboPuesto;
    private JComboBox<String> comboSucursal;

    private JButton botonCerrar;
    private JButton botonAgregar;

    public VentanaAgregarUsuario(JFrame parent, ControlGestionarUsuarios control, List<TipoEmpleado> tiposEmpleado, List<Sucursal> sucursales) {
        super(parent, "Agregar nuevo usuario", true);
        this.controlGestionarUsuarios = control;
        this.tiposEmpleado = tiposEmpleado;
        this.sucursales = sucursales;

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
        comboSucursal = addSucursalComboBox("Sucursal:", ++gbc.gridy, gbc, sucursales);

        gbc.gridy++;
        gbc.gridx = 0;
        botonCerrar = new JButton("Cerrar");
        add(botonCerrar, gbc);

        gbc.gridx = 1;
        botonAgregar = new JButton("Agregar usuario");
        add(botonAgregar, gbc);

        botonCerrar.addActionListener(e -> dispose());

        botonAgregar.addActionListener((ActionEvent e) -> {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String correo = campoCorreo.getText();
            String telefono = campoTelefono.getText();

            if (!esCorreoValido(correo)) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un correo electrónico válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!esTelefonoValido(telefono)) {
                JOptionPane.showMessageDialog(this, "El número de teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setNumeroEmpleado(campoNumeroEmpleado.getText());
            nuevoEmpleado.setNombre(campoNombre.getText());
            nuevoEmpleado.setApellidoPaterno(campoApellidoPaterno.getText());
            nuevoEmpleado.setApellidoMaterno(campoApellidoMaterno.getText());
            nuevoEmpleado.setCorreoElectronico(correo);
            nuevoEmpleado.setTelefono(telefono);

            String tipoSeleccionado = (String) comboPuesto.getSelectedItem();
            TipoEmpleado tipo = tiposEmpleado.stream()
                    .filter(t -> t.getNombre().equalsIgnoreCase(tipoSeleccionado))
                    .findFirst()
                    .orElse(null);
            nuevoEmpleado.setTipo(tipo);

            String sucursalSeleccionada = (String) comboSucursal.getSelectedItem();
            Sucursal sucursal = sucursales.stream()
                    .filter(s -> s.getNombre().equalsIgnoreCase(sucursalSeleccionada))
                    .findFirst()
                    .orElse(null);
            nuevoEmpleado.setSucursal(sucursal);

            controlGestionarUsuarios.agregaEmpleado(nuevoEmpleado);

            JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente");
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

    private JComboBox<String> addSucursalComboBox(String label, int y, GridBagConstraints gbc, List<Sucursal> sucursales) {
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        JComboBox<String> comboBox = new JComboBox<>();
        for (Sucursal sucursal : sucursales) {
            comboBox.addItem(sucursal.getNombre());
        }
        add(comboBox, gbc);
        return comboBox;
    }

    private boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("^\\d{10}$");
    }

    private boolean camposVacios() {
        return campoNumeroEmpleado.getText().isEmpty()
                || campoNombre.getText().isEmpty()
                || campoApellidoPaterno.getText().isEmpty()
                || campoApellidoMaterno.getText().isEmpty()
                || campoCorreo.getText().isEmpty()
                || campoTelefono.getText().isEmpty();
    }
}
