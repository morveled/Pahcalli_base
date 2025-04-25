package mx.uam.ayd.proyecto.presentacion.eliminarProducto;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Component
public class VentanaEliminarProducto extends JFrame {
    
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

    private JComboBox<String> comboProductos;
    private JButton btnEliminar;
    private JButton btnCancelar;

    /**
     * Constructor que inicializa los componentes con el diseño estándar
     */
    public VentanaEliminarProducto() {
        // Configuración básica de la ventana (punto 1)
        setTitle("Eliminar Producto");
        setSize(800, 300); // Dimensiones estándar
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(COLOR_BACKGROUND);
        
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
        
        // Título de la ventana (punto 2)
        JLabel lblTitulo = new JLabel("Eliminar Producto", SwingConstants.CENTER);
        lblTitulo.setFont(FONT_TITLE);
        lblTitulo.setForeground(COLOR_TEXT);
        titlePanel.add(lblTitulo, BorderLayout.NORTH);
        
        // Subtitulo/Instrucciones (punto 2)
        JLabel lblInstrucciones = new JLabel("Selecciona un producto para eliminar:", SwingConstants.CENTER);
        lblInstrucciones.setFont(FONT_SUBTITLE);
        lblInstrucciones.setForeground(COLOR_TEXT);
        lblInstrucciones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titlePanel.add(lblInstrucciones, BorderLayout.CENTER);
        
        // Panel central para el combo (punto 2)
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(COLOR_BACKGROUND);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        contentPane.add(centerPanel, BorderLayout.CENTER);
        
        // Combo de productos con estilo estándar (punto 2)
        comboProductos = new JComboBox<>();
        comboProductos.setFont(FONT_LABEL);
        comboProductos.setPreferredSize(new Dimension(300, 30));
        centerPanel.add(comboProductos);
        
        // Panel de botones con alineación a la derecha (punto 3)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(COLOR_BACKGROUND);
        contentPane.add(panelBotones, BorderLayout.SOUTH);
        
        // Botón cancelar con estilo estándar (punto 3)
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(FONT_BUTTON);
        btnCancelar.setBackground(COLOR_SECONDARY);
        btnCancelar.setForeground(COLOR_TEXT);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setPreferredSize(new Dimension(120, 30));
        
        // Botón eliminar con estilo estándar (punto 3)
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(FONT_BUTTON);
        btnEliminar.setBackground(COLOR_PRIMARY);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setPreferredSize(new Dimension(120, 30));
        
        // Añadir botones en orden (cancelar, eliminar)
        panelBotones.add(btnCancelar);
        panelBotones.add(btnEliminar);
    }

    /**
     * Llena el combo de productos con la lista proporcionada
     * 
     * @param productos Lista de productos a mostrar
     */
    public void llenaProductos(List<Producto> productos) {
        comboProductos.removeAllItems();
        for (Producto p : productos) {
            comboProductos.addItem(p.getNombre());
        }
    }

    /**
     * Obtiene el nombre del producto seleccionado en el combo
     * 
     * @return Nombre del producto seleccionado
     */
    public String getProductoSeleccionado() {
        return (String) comboProductos.getSelectedItem();
    }

    /**
     * Agrega un listener al botón eliminar
     * 
     * @param listener Listener a agregar
     */
    public void agregarListenerEliminar(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }

    /**
     * Agrega un listener al botón cancelar
     * 
     * @param listener Listener a agregar
     */
    public void agregarListenerCancelar(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }

    /**
     * Muestra un mensaje informativo con estilo estándar (punto 6)
     * 
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        // Configuración estándar para diálogos de información (punto 6)
        UIManager.put("OptionPane.messageFont", FONT_LABEL);
        UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
        UIManager.put("OptionPane.background", COLOR_BACKGROUND);
        UIManager.put("Panel.background", COLOR_BACKGROUND);
        UIManager.put("OptionPane.informationIcon", UIManager.getIcon("OptionPane.informationIcon"));
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra un mensaje de error con estilo estándar (punto 6)
     * 
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarError(String mensaje) {
        // Configuración estándar para diálogos de error (punto 6)
        UIManager.put("OptionPane.messageFont", FONT_LABEL);
        UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
        UIManager.put("OptionPane.background", COLOR_BACKGROUND);
        UIManager.put("Panel.background", COLOR_BACKGROUND);
        UIManager.put("OptionPane.errorIcon", UIManager.getIcon("OptionPane.errorIcon"));
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un diálogo de confirmación con estilo estándar (punto 6)
     * 
     * @param nombreProducto Nombre del producto a eliminar
     * @return true si se confirma la eliminación, false en caso contrario
     */
    public boolean confirmarEliminacion(String nombreProducto) {
        // Configuración estándar para diálogos de confirmación (punto 6)
        UIManager.put("OptionPane.messageFont", FONT_LABEL);
        UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
        UIManager.put("OptionPane.background", COLOR_BACKGROUND);
        UIManager.put("Panel.background", COLOR_BACKGROUND);
        UIManager.put("OptionPane.questionIcon", UIManager.getIcon("OptionPane.questionIcon"));
        
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas eliminar el producto \"" + nombreProducto + "\"?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        return opcion == JOptionPane.YES_OPTION;
    }
}