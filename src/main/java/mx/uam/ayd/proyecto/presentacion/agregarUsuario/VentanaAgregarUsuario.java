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

    // Definición de constantes de estilo según el estándar
    private static final Font FONT_TITLE = new Font("Dialog", Font.BOLD, 20);
    private static final Font FONT_SUBTITLE = new Font("Dialog", Font.BOLD, 14);
    private static final Font FONT_LABEL = new Font("Dialog", Font.PLAIN, 12);
    private static final Font FONT_BUTTON = new Font("Dialog", Font.BOLD, 12);
    
    private static final Color COLOR_PRIMARY = new Color(78, 154, 241); // #4e9af1
    private static final Color COLOR_SECONDARY = new Color(240, 240, 240); // #f0f0f0
    private static final Color COLOR_TEXT = new Color(51, 51, 51); // #333333
    private static final Color COLOR_BACKGROUND = Color.WHITE;
    private static final Color COLOR_PANEL = new Color(245, 245, 245); // #f5f5f5

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

        // Configuración básica según el estándar
        setSize(800, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // Panel principal con margen interior
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(COLOR_BACKGROUND);
        
        // Panel de título en la parte superior
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(COLOR_BACKGROUND);
        
        JLabel titulo = new JLabel("Agregar nuevo usuario", JLabel.CENTER);
        titulo.setFont(FONT_TITLE);
        titulo.setForeground(COLOR_TEXT);
        titlePanel.add(titulo);
        
        // Panel de formulario en el centro
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(COLOR_BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel subtitulo = new JLabel("Por favor llena el siguiente formulario");
        subtitulo.setFont(FONT_SUBTITLE);
        subtitulo.setForeground(COLOR_TEXT);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(subtitulo, gbc);

        gbc.gridwidth = 1;
        
        // Campos del formulario con el estilo estandarizado
        campoNumeroEmpleado = addField("Número de empleado:", ++gbc.gridy, gbc, formPanel);
        campoNombre = addField("Nombre(s):", ++gbc.gridy, gbc, formPanel);
        campoApellidoPaterno = addField("Apellido paterno:", ++gbc.gridy, gbc, formPanel);
        campoApellidoMaterno = addField("Apellido materno:", ++gbc.gridy, gbc, formPanel);
        campoCorreo = addField("Correo:", ++gbc.gridy, gbc, formPanel);
        campoTelefono = addField("Teléfono:", ++gbc.gridy, gbc, formPanel);
        comboPuesto = addComboBox("Puesto:", ++gbc.gridy, gbc, formPanel, tiposEmpleado);
        comboSucursal = addSucursalComboBox("Sucursal:", ++gbc.gridy, gbc, formPanel, sucursales);
        
        // Panel de botones en la parte inferior
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(COLOR_BACKGROUND);
        
        botonAgregar = new JButton("Agregar usuario");
        botonAgregar.setFont(FONT_BUTTON);
        botonAgregar.setBackground(COLOR_PRIMARY);
        botonAgregar.setForeground(Color.WHITE);
        botonAgregar.setPreferredSize(new Dimension(150, 30));
        
        botonCerrar = new JButton("Cerrar");
        botonCerrar.setFont(FONT_BUTTON);
        botonCerrar.setBackground(COLOR_SECONDARY);
        botonCerrar.setForeground(COLOR_TEXT);
        botonCerrar.setPreferredSize(new Dimension(120, 30));
        
        buttonPanel.add(botonAgregar);
        buttonPanel.add(botonCerrar);
        
        // Agregamos los paneles al panel principal según la estructura BorderLayout
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Agregamos el panel principal a la ventana
        add(mainPanel);
        
        // Configuración de los listeners de botones
        botonCerrar.addActionListener(e -> dispose());

        botonAgregar.addActionListener((ActionEvent e) -> {
            if (camposVacios()) {
                // Diálogo de error estándar (punto 6)
                JOptionPane.showMessageDialog(this, 
                    "Todos los campos deben estar completos.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            String correo = campoCorreo.getText();
            String telefono = campoTelefono.getText();

            if (!esCorreoValido(correo)) {
                // Diálogo de error estándar (punto 6)
                JOptionPane.showMessageDialog(this, 
                    "Por favor, ingresa un correo electrónico válido.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!esTelefonoValido(telefono)) {
                // Diálogo de error estándar (punto 6)
                JOptionPane.showMessageDialog(this, 
                    "El número de teléfono debe contener exactamente 10 dígitos numéricos.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
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

            // Diálogo de información estándar (punto 6)
            JOptionPane.showMessageDialog(this, 
                "Empleado agregado exitosamente", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }

    /**
     * Método helper para agregar campos de texto con etiquetas
     */
    private JTextField addField(String label, int y, GridBagConstraints gbc, JPanel panel) {
        // Etiqueta
        JLabel lbl = new JLabel(label);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(COLOR_TEXT);
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(lbl, gbc);

        // Campo de texto
        gbc.gridx = 1;
        JTextField field = new JTextField(20);
        field.setFont(FONT_LABEL);
        panel.add(field, gbc);
        return field;
    }

    /**
     * Método helper para agregar combos de selección con etiquetas para tipos de empleado
     */
    private JComboBox<String> addComboBox(String label, int y, GridBagConstraints gbc, JPanel panel, List<TipoEmpleado> tipos) {
        // Etiqueta
        JLabel lbl = new JLabel(label);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(COLOR_TEXT);
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(lbl, gbc);

        // Combo
        gbc.gridx = 1;
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(FONT_LABEL);
        for (TipoEmpleado tipo : tipos) {
            comboBox.addItem(tipo.getNombre());
        }
        panel.add(comboBox, gbc);
        return comboBox;
    }

    /**
     * Método helper para agregar combos de selección con etiquetas para sucursales
     */
    private JComboBox<String> addSucursalComboBox(String label, int y, GridBagConstraints gbc, JPanel panel, List<Sucursal> sucursales) {
        // Etiqueta
        JLabel lbl = new JLabel(label);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(COLOR_TEXT);
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(lbl, gbc);

        // Combo
        gbc.gridx = 1;
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(FONT_LABEL);
        for (Sucursal sucursal : sucursales) {
            comboBox.addItem(sucursal.getNombre());
        }
        panel.add(comboBox, gbc);
        return comboBox;
    }

    /**
     * Valida el formato del correo electrónico
     */
    private boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    /**
     * Valida que el teléfono tenga exactamente 10 dígitos
     */
    private boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("^\\d{10}$");
    }

    /**
     * Verifica que todos los campos obligatorios estén completos
     */
    private boolean camposVacios() {
        return campoNumeroEmpleado.getText().isEmpty()
                || campoNombre.getText().isEmpty()
                || campoApellidoPaterno.getText().isEmpty()
                || campoApellidoMaterno.getText().isEmpty()
                || campoCorreo.getText().isEmpty()
                || campoTelefono.getText().isEmpty();
    }
}
