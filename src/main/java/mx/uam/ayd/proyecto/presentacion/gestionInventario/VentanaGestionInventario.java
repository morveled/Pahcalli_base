package mx.uam.ayd.proyecto.presentacion.gestionInventario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.stereotype.Component;

@Component
public class VentanaGestionInventario extends JFrame {
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
    
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JButton btnCargarCSV;
    private JButton btnAgregarInventario;
    private JScrollPane scrollPane;

    private ControladorGestionInventario controladorGestionInventario;

    public VentanaGestionInventario() {
        // Configuración de la ventana según el estándar (punto 1)
        setTitle("Gestión de Inventario");
        setSize(800, 500); // Dimensiones estándar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        // Panel principal con márgenes estándar (punto 1)
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(COLOR_BACKGROUND);
        
        // Panel de título en la parte superior (punto 2)
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(COLOR_BACKGROUND);
        
        JLabel lblTitle = new JLabel("Gestión de Inventario", JLabel.CENTER);
        lblTitle.setFont(FONT_TITLE);
        lblTitle.setForeground(COLOR_TEXT);
        titlePanel.add(lblTitle);
        
        // Panel central con tabla
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(COLOR_BACKGROUND);
        
        // Crear modelo de tabla
        String[] columnas = {"Producto", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable directamente
            }
        };

        // Crear tabla con estilos (punto 4)
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setFont(FONT_LABEL);
        tablaProductos.setForeground(COLOR_TEXT);
        tablaProductos.setRowHeight(25);
        tablaProductos.setGridColor(new Color(220, 220, 220));
        tablaProductos.setSelectionBackground(COLOR_TABLE_SELECTED);
        tablaProductos.setSelectionForeground(COLOR_TEXT);
        tablaProductos.setAutoCreateRowSorter(true);
        
        // Estilo de los encabezados de la tabla (punto 4)
        JTableHeader header = tablaProductos.getTableHeader();
        header.setFont(FONT_SUBTITLE);
        header.setBackground(COLOR_TABLE_HEADER);
        header.setForeground(COLOR_TEXT);
        
        scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBackground(COLOR_BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones en la parte inferior (punto 3)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(COLOR_BACKGROUND);
        
        // Botones con estilo estándar (punto 3)
        btnCargarCSV = new JButton("Cargar desde CSV");
        btnCargarCSV.setFont(FONT_BUTTON);
        btnCargarCSV.setBackground(COLOR_PRIMARY);
        btnCargarCSV.setForeground(Color.WHITE);
        btnCargarCSV.setPreferredSize(new Dimension(150, 30));
        btnCargarCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorGestionInventario.agregarInventarioDesdeCSV();
            }
        });
        
        btnAgregarInventario = new JButton("Agregar Inventario");
        btnAgregarInventario.setFont(FONT_BUTTON);
        btnAgregarInventario.setBackground(COLOR_PRIMARY);
        btnAgregarInventario.setForeground(Color.WHITE);
        btnAgregarInventario.setPreferredSize(new Dimension(150, 30));
        btnAgregarInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorGestionInventario.agregarInventario();
            }
        });
        
        // Botón de cerrar/cancelar (punto 3)
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(FONT_BUTTON);
        btnCerrar.setBackground(COLOR_SECONDARY);
        btnCerrar.setForeground(COLOR_TEXT);
        btnCerrar.setPreferredSize(new Dimension(120, 30));
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Agregar botones (botón de cerrar a la derecha, punto 3)
        panelBotones.add(btnCargarCSV);
        panelBotones.add(btnAgregarInventario);
        panelBotones.add(btnCerrar);

        // Estructura de paneles estándar (punto 1)
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);
        
        // Agregar panel principal a la ventana
        add(mainPanel);
    }

    /**
     * Agrega un producto a la tabla de inventario
     */
    public void agregarProducto(String producto, String cantidad) {
        // Añadir fila
        Object[] fila = {producto, cantidad};
        modeloTabla.addRow(fila);
        
        // Aplicar estilo de filas alternadas (punto 4)
        aplicarEstiloFilasAlternadas();
        tablaProductos.updateUI();
    }

    /**
     * Limpia todos los datos de la tabla
     */
    public void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    /**
     * Muestra la ventana de gestión de inventario
     */
    public void muestra(ControladorGestionInventario controladorGestionInventario) {
        this.controladorGestionInventario = controladorGestionInventario;
        
        // Aplicar estilo a la tabla antes de mostrar
        aplicarEstiloFilasAlternadas();
        setVisible(true);
    }
    
    /**
     * Método auxiliar para aplicar estilo de filas alternadas a la tabla (punto 4)
     */
    private void aplicarEstiloFilasAlternadas() {
        // Personalizar la apariencia de las filas alternadas
        tablaProductos.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (isSelected) {
                    c.setBackground(COLOR_TABLE_SELECTED);
                    c.setForeground(COLOR_TEXT);
                } else {
                    // Filas alternadas con colores diferentes
                    if (row % 2 == 0) {
                        c.setBackground(COLOR_BACKGROUND); // Filas pares: blanco
                    } else {
                        c.setBackground(COLOR_PANEL); // Filas impares: gris claro
                    }
                    c.setForeground(COLOR_TEXT);
                }
                
                return c;
            }
        });
    }

}