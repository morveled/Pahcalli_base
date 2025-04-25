package mx.uam.ayd.proyecto.presentacion.menu;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEmpleado;
import mx.uam.ayd.proyecto.presentacion.gestionProductos.VentanaGestionProductos;

@SuppressWarnings("serial")
@Component
public class VentanaMenu extends JFrame {
    
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
    private static final Color COLOR_TABLE_HEADER = new Color(233, 233, 233); // #e9e9e9
    private static final Color COLOR_TABLE_SELECTED = new Color(208, 228, 255); // #d0e4ff

    private JLabel titleLabel;
    private JLabel welcomeLabel;
    private JPanel buttonPanel;
    
    // Botones de menú
    private JButton btnRealizarVenta;
    private JButton btnInventarioFarmacias;
    private JButton btnGestionUsuarios; 
    private JButton btnGestionPromociones;
    private JButton btnGestionInventario;
    private JButton btnEstadisticas;
    private JButton btnGestionProductos;
    private JButton btnSolicitudesAbastecimiento;
    private JButton btnCerrarSesion;

    private ControlMenu controlMenu;

    @Autowired
    private VentanaGestionProductos ventanaGestionProductos;

    /**
     * Constructor que inicializa los componentes
     */
    public VentanaMenu() {
        initComponents();
    }
    
    /**
     * Inicializa los componentes con el diseño estándar
     */
    private void initComponents() {
        // Configuración básica de la ventana (punto 1)
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal - Farmacia Pahcalli");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setBackground(COLOR_BACKGROUND);
        
        // Panel principal con márgenes estándar (punto 1)
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contentPane.setBackground(COLOR_BACKGROUND);
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);
        
        // Panel de título (punto 2)
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(COLOR_BACKGROUND);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        contentPane.add(titlePanel, BorderLayout.NORTH);
        
        // Título de la farmacia (punto 2)
        titleLabel = new JLabel("Farmacia Pahcalli");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        
        // Etiqueta de bienvenida (punto 2)
        welcomeLabel = new JLabel("¡Bienvenid@!");
        welcomeLabel.setFont(FONT_SUBTITLE);
        welcomeLabel.setForeground(COLOR_TEXT);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(welcomeLabel, BorderLayout.SOUTH);
        
        // Panel central para los botones del menú (punto 3)
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 10)); // Una columna con 10px de espacio vertical
        buttonPanel.setBackground(COLOR_BACKGROUND);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        
        // Crear y añadir los botones al panel
        btnRealizarVenta = createMenuButton("Realizar Venta", e -> controlMenu.mostrarRealizarVenta());
        btnInventarioFarmacias = createMenuButton("Inventario de Farmacias", e -> controlMenu.mostrarMostrarInventario());
        btnGestionUsuarios = createMenuButton("Gestión de Usuarios", e -> controlMenu.mostrarGestionUsuarios());
        btnGestionPromociones = createMenuButton("Gestión de Promociones", null);
        btnGestionInventario = createMenuButton("Gestión de Inventario", e -> controlMenu.mostrarGestionInventario());
        btnEstadisticas = createMenuButton("Estadísticas", null);
        btnGestionProductos = createMenuButton("Gestión de Productos", e -> jButton7ActionPerformed());
        btnSolicitudesAbastecimiento = createMenuButton("Solicitudes de Abastecimiento", 
                e -> controlMenu.mostrarSolicitudesAbastecimiento());
        
        // Añadir los botones al panel en orden
        buttonPanel.add(btnRealizarVenta);
        buttonPanel.add(btnInventarioFarmacias);
        buttonPanel.add(btnGestionUsuarios);
        buttonPanel.add(btnGestionPromociones);
        buttonPanel.add(btnGestionInventario);
        buttonPanel.add(btnEstadisticas);
        buttonPanel.add(btnGestionProductos);
        buttonPanel.add(btnSolicitudesAbastecimiento);
        
        // Panel inferior para el botón de cerrar sesión (punto 3)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(COLOR_BACKGROUND);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        
        // Botón de cerrar sesión
        btnCerrarSesion = createMenuButton("Cerrar Sesión", e -> controlMenu.cerrarSesion());
        btnCerrarSesion.setPreferredSize(new Dimension(150, 30));
        bottomPanel.add(btnCerrarSesion);
    }

    /**
     * Método para crear botones del menú con estilo estándar (punto 3)
     * 
     * @param text Texto del botón
     * @param listener ActionListener para el botón (puede ser null)
     * @return Botón configurado con el estilo estándar
     */
    private JButton createMenuButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(FONT_BUTTON);
        button.setBackground(COLOR_PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        
        if (listener != null) {
            button.addActionListener(listener);
        }
        
        return button;
    }
    
    /**
     * Muestra un diálogo de error con formato estándar (punto 6)
     * @param mensaje Mensaje a mostrar al usuario
     */
    private void muestraDialogoError(String mensaje) {
        // Configuración estándar para diálogos de error (punto 6)
        UIManager.put("OptionPane.messageFont", FONT_LABEL);
        UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
        UIManager.put("OptionPane.background", COLOR_BACKGROUND);
        UIManager.put("Panel.background", COLOR_BACKGROUND);
        UIManager.put("OptionPane.errorIcon", UIManager.getIcon("OptionPane.errorIcon"));
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra un diálogo de éxito con formato estándar (punto 6)
     * @param mensaje Mensaje a mostrar al usuario
     */
    private void muestraDialogoExito(String mensaje) {
        // Configuración estándar para diálogos de información (punto 6)
        UIManager.put("OptionPane.messageFont", FONT_LABEL);
        UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
        UIManager.put("OptionPane.background", COLOR_BACKGROUND);
        UIManager.put("Panel.background", COLOR_BACKGROUND);
        UIManager.put("OptionPane.informationIcon", UIManager.getIcon("OptionPane.informationIcon"));
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Abre la ventana de gestión de productos
     */
    private void jButton7ActionPerformed() {
        ventanaGestionProductos.muestra(); 
    }

    /**
     * Hace visible la ventana y establece el controlador
     * 
     * @param control Controlador del menú
     */
    public void muestra(ControlMenu control) {
        this.controlMenu = control;
        setVisible(true);
    }

    /**
     * Configura la ventana según el tipo de empleado que inicia sesión
     * 
     * @param empleado Empleado que inicia sesión
     */
    public void configurarVentana(Empleado empleado) {
        // Personalizar mensaje de bienvenida
        String tipo = empleado.getTipo().getNombre();
        welcomeLabel.setText("Bienvenid@ " + empleado.getNombre() + ": " + tipo);
        
        // Ocultar todos los botones primero
        btnGestionUsuarios.setVisible(false);
        btnGestionPromociones.setVisible(false);
        btnGestionInventario.setVisible(false);
        btnEstadisticas.setVisible(false);
        btnGestionProductos.setVisible(false);
        btnSolicitudesAbastecimiento.setVisible(false);
        btnRealizarVenta.setVisible(false);
        btnInventarioFarmacias.setVisible(true); // Este se mantiene visible para todos

        // Configurar visibilidad de botones según el tipo de empleado
        switch (tipo) {
            case "Gerente":
                btnRealizarVenta.setVisible(true);
                btnGestionUsuarios.setVisible(true);
                btnGestionInventario.setVisible(true);
                btnGestionProductos.setVisible(true);
                break;
            case "Cajero":
                btnRealizarVenta.setVisible(true);
                break;
            case "Almacenista":
                btnRealizarVenta.setVisible(false);
                btnGestionInventario.setVisible(true);
                btnSolicitudesAbastecimiento.setVisible(true);
                break;
        }
    }
}
