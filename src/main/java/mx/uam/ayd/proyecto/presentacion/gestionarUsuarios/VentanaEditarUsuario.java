package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Sucursal;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEmpleado;

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
    private JComboBox<String> comboPuesto;
    private JComboBox<String> comboSucursal;

    private JButton botonCerrar;
    private JButton botonGuardar;

    private List<TipoEmpleado> tiposEmpleado;
    private List<Sucursal> sucursales;

    public VentanaEditarUsuario(JFrame parent, Empleado empleado, ControlGestionarUsuarios control) {
        super(parent, "Editar usuario", true);
        this.empleado = empleado;
        this.controlGestionarUsuarios = control;

        this.tiposEmpleado = control.obtenerTiposEmpleado();
        this.sucursales = control.obtenerSucursales();

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

        campoNumeroEmpleado = addField("Número de empleado:", ++gbc.gridy, gbc, empleado.getNumeroEmpleado());
        campoNombre = addField("Nombre(s):", ++gbc.gridy, gbc, empleado.getNombre());
        campoApellidoPaterno = addField("Apellido paterno:", ++gbc.gridy, gbc, empleado.getApellidoPaterno());
        campoApellidoMaterno = addField("Apellido materno:", ++gbc.gridy, gbc, empleado.getApellidoMaterno());
        campoCorreo = addField("Correo electrónico:", ++gbc.gridy, gbc, empleado.getCorreoElectronico());
        campoTelefono = addField("Teléfono:", ++gbc.gridy, gbc, empleado.getTelefono());

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Puesto:"), gbc);
        gbc.gridx = 1;
        comboPuesto = new JComboBox<>();
        for (TipoEmpleado tipo : tiposEmpleado) {
            comboPuesto.addItem(tipo.getNombre());
        }
        comboPuesto.setSelectedItem(empleado.getTipo() != null ? empleado.getTipo().getNombre() : "");
        add(comboPuesto, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Sucursal:"), gbc);
        gbc.gridx = 1;
        comboSucursal = new JComboBox<>();
        for (Sucursal sucursal : sucursales) {
            comboSucursal.addItem(sucursal.getNombre());
        }
        comboSucursal.setSelectedItem(empleado.getSucursal() != null ? empleado.getSucursal().getNombre() : "");
        add(comboSucursal, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        botonCerrar = new JButton("Cerrar");
        add(botonCerrar, gbc);

        gbc.gridx = 1;
        botonGuardar = new JButton("Guardar cambios");
        add(botonGuardar, gbc);

        botonCerrar.addActionListener(e -> dispose());

        botonGuardar.addActionListener((ActionEvent e) -> {
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

            empleado.setNumeroEmpleado(campoNumeroEmpleado.getText());
            empleado.setNombre(campoNombre.getText());
            empleado.setApellidoPaterno(campoApellidoPaterno.getText());
            empleado.setApellidoMaterno(campoApellidoMaterno.getText());
            empleado.setCorreoElectronico(correo);
            empleado.setTelefono(telefono);

            String tipoSeleccionado = (String) comboPuesto.getSelectedItem();
            TipoEmpleado tipo = tiposEmpleado.stream()
                    .filter(t -> t.getNombre().equalsIgnoreCase(tipoSeleccionado))
                    .findFirst()
                    .orElse(null);
            empleado.setTipo(tipo);

            String sucursalSeleccionada = (String) comboSucursal.getSelectedItem();
            Sucursal sucursal = sucursales.stream()
                    .filter(s -> s.getNombre().equalsIgnoreCase(sucursalSeleccionada))
                    .findFirst()
                    .orElse(null);
            empleado.setSucursal(sucursal);

            controlGestionarUsuarios.actualizarEmpleado(empleado);
            dispose();
        });

        setSize(500, 600);
        setLocationRelativeTo(parent);
    }

    private JTextField addField(String label, int y, GridBagConstraints gbc, String value) {
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        JTextField field = new JTextField(20);
        field.setText(value);
        add(field, gbc);
        return field;
    }

    private boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("^\\d{10}$");
    }
}
