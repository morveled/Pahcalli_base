package mx.uam.ayd.proyecto.presentacion.gestionProductos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.presentacion.agregarProducto.ControlAgregarProducto;
import mx.uam.ayd.proyecto.presentacion.eliminarProducto.ControlEliminarProducto;
import mx.uam.ayd.proyecto.presentacion.modificarProducto.ControlModificarProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

@SuppressWarnings("serial")
@Component
public class VentanaGestionProductos extends JFrame {
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
    private JScrollPane scrollPane;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;

    @Autowired
    private ControlAgregarProducto controlAgregarProducto;
    @Autowired
    private ControlModificarProducto controlModificarProducto;
    @Autowired
    private ControlEliminarProducto controlEliminarProducto;
    @Autowired
    private ServicioProducto servicioProducto;

    public VentanaGestionProductos() {
        initComponents();
    }

    private void initComponents() {
        // Configuración básica de la ventana según el estándar (punto 1)
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Productos");
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
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        contentPane.add(titlePanel, BorderLayout.NORTH);
        
        // Título (punto 2)
        titleLabel = new JLabel("Gestión de Productos");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        
        // Panel central para la tabla (punto 4)
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(COLOR_BACKGROUND);
        contentPane.add(tablePanel, BorderLayout.CENTER);
        
        // Configuración de la tabla (punto 4)
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{
            "Código", "Producto", "Descripción", "Ingrediente Activo",
            "Laboratorio", "Contenido", "¿Requiere Receta?", "Precio"
        });
        tablaProductos = new JTable(modeloTabla);
        
        // Aplicar estilo estándar a la tabla (punto 4)
        tablaProductos.setRowHeight(25);
        tablaProductos.setFont(FONT_LABEL);
        tablaProductos.setGridColor(COLOR_TABLE_HEADER);
        tablaProductos.setSelectionBackground(COLOR_TABLE_SELECTED);
        tablaProductos.setShowGrid(true);
        tablaProductos.setShowHorizontalLines(true);
        tablaProductos.setShowVerticalLines(true);
        
        // Estilo del encabezado de la tabla (punto 4)
        JTableHeader header = tablaProductos.getTableHeader();
        header.setBackground(COLOR_TABLE_HEADER);
        header.setFont(FONT_SUBTITLE);
        header.setPreferredSize(new Dimension(header.getWidth(), 30));
        
        // Panel de desplazamiento para la tabla
        scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_SECONDARY));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones en la parte inferior (punto 3)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(COLOR_BACKGROUND);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        // Botón agregar con estilo estándar (punto 3)
        btnAgregar = createStandardButton("Agregar Nuevo Producto", COLOR_PRIMARY, Color.WHITE);
        btnAgregar.addActionListener(evt -> {
            controlAgregarProducto.inicia();
            actualizarTablaProductos();
        });
        buttonPanel.add(btnAgregar);
        
        // Botón modificar con estilo estándar (punto 3)
        btnModificar = createStandardButton("Modificar Producto", COLOR_PRIMARY, Color.WHITE);
        btnModificar.addActionListener(evt -> modificarProducto());
        buttonPanel.add(btnModificar);
        
        // Botón eliminar con estilo estándar (punto 3)
        btnEliminar = createStandardButton("Eliminar Producto", COLOR_SECONDARY, COLOR_TEXT);
        btnEliminar.addActionListener(evt -> controlEliminarProducto.inicia(this));
        buttonPanel.add(btnEliminar);
    }

    /**
     * Método para crear botones con el estilo estándar (punto 3)
     * 
     * @param text Texto del botón
     * @param background Color de fondo
     * @param foreground Color del texto
     * @return Botón configurado con el estilo estándar
     */
    private JButton createStandardButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setFont(FONT_BUTTON);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }
    
    /**
     * Método para modificar un producto seleccionado
     */
    private void modificarProducto() {
        int selectedRow = tablaProductos.getSelectedRow();
        if (selectedRow >= 0) {
            String codigo = (String) tablaProductos.getValueAt(selectedRow, 0);
            Producto productoSeleccionado = servicioProducto.obtenerPorCodigo(codigo);
            if (productoSeleccionado != null) {
                controlModificarProducto.inicia(productoSeleccionado);
                actualizarTablaProductos();
            } else {
                muestraDialogoError("Producto no encontrado.");
            }
        } else {
            muestraDialogoError("Debe seleccionar un producto para modificar.");
        }
    }

    /**
     * Actualiza la tabla de productos con los datos actuales
     */
    public void actualizarTablaProductos() {
        SwingUtilities.invokeLater(() -> {
            modeloTabla.setRowCount(0);
            List<Producto> productos = servicioProducto.getAll();
            for (Producto producto : productos) {
                modeloTabla.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getIngrediente().getNombre(),
                    producto.getLaboratorio().getNombre(),
                    producto.getContenido(),
                    producto.getReceta() != null ? (producto.getReceta() ? "Sí" : "No") : "No",
                    producto.getPrecio() != null ? producto.getPrecio() : 0.0
                });
            }
        });
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
     * Hace visible la ventana y actualiza los datos
     */
    public void muestra() {
        actualizarTablaProductos();
        setVisible(true);
    }
}