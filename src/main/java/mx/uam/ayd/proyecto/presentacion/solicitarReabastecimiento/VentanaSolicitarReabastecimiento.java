package mx.uam.ayd.proyecto.presentacion.solicitarReabastecimiento;

import java.util.List;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.DetallesSolicitud;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Component
public class VentanaSolicitarReabastecimiento extends JFrame {
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
    private JScrollPane scrollPane;
    private JButton botonSolicitar;
    private JButton botonCancelar;
    private JTextField txtCodigo;

    private ControladorSolicitarReabastecimiento controlador;
    
    public VentanaSolicitarReabastecimiento() {
        // Configuración de la ventana según el estándar (punto 1)
        setTitle("Solicitud de Reabastecimiento");
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
        
        JLabel lblTitle = new JLabel("Solicitud de Reabastecimiento", JLabel.CENTER);
        lblTitle.setFont(FONT_TITLE);
        lblTitle.setForeground(COLOR_TEXT);
        titlePanel.add(lblTitle);
        
        // Panel superior para agregar productos por código
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(COLOR_BACKGROUND);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        
        JPanel agregar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        agregar.setBackground(COLOR_BACKGROUND);
        
        JLabel codigo = new JLabel("Código de producto: ");
        codigo.setFont(FONT_LABEL);
        codigo.setForeground(COLOR_TEXT);
        
        txtCodigo = new JTextField();
        txtCodigo.setColumns(15);
        txtCodigo.setFont(FONT_LABEL);
        txtCodigo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        txtCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.agregarProducto(txtCodigo.getText());
                txtCodigo.setText("");
            }
        });
        
        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.setFont(FONT_BUTTON);
        botonAgregar.setBackground(COLOR_PRIMARY);
        botonAgregar.setForeground(Color.WHITE);
        botonAgregar.setPreferredSize(new Dimension(120, 30));
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.agregarProducto(txtCodigo.getText());
                txtCodigo.setText("");
            }
        });
        
        agregar.add(codigo);
        agregar.add(txtCodigo);
        agregar.add(botonAgregar);
        topPanel.add(agregar, BorderLayout.CENTER);
        
        // Panel central con tabla de productos
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(COLOR_BACKGROUND);
        
        // Subtitulo para la tabla
        JPanel tableHeaderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tableHeaderPanel.setBackground(COLOR_BACKGROUND);
        JLabel lblSubtitle = new JLabel("Productos a solicitar:");
        lblSubtitle.setFont(FONT_SUBTITLE);
        lblSubtitle.setForeground(COLOR_TEXT);
        tableHeaderPanel.add(lblSubtitle);
        centerPanel.add(tableHeaderPanel, BorderLayout.NORTH);
        
        // Crear componentes de la tabla con estilos estándar (punto 4)
        tablaProductos = new JTable();
        tablaProductos.setFont(FONT_LABEL);
        tablaProductos.setForeground(COLOR_TEXT);
        tablaProductos.setRowHeight(25);
        tablaProductos.setGridColor(new Color(220, 220, 220));
        tablaProductos.setSelectionBackground(COLOR_TABLE_SELECTED);
        tablaProductos.setSelectionForeground(COLOR_TEXT);
        
        // Scroll pane para la tabla
        scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBackground(COLOR_BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel para los botones en parte inferior (punto 3)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(COLOR_BACKGROUND);
        
        // Botones estándar (punto 3)
        botonSolicitar = new JButton("Solicitar Reabastecimiento");
        botonSolicitar.setFont(FONT_BUTTON);
        botonSolicitar.setBackground(COLOR_PRIMARY);
        botonSolicitar.setForeground(Color.WHITE);
        botonSolicitar.setPreferredSize(new Dimension(200, 30));
        botonSolicitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.solicitarReabastecimiento();
            }
        });
        
        botonCancelar = new JButton("Cancelar");
        botonCancelar.setFont(FONT_BUTTON);
        botonCancelar.setBackground(COLOR_SECONDARY);
        botonCancelar.setForeground(COLOR_TEXT);
        botonCancelar.setPreferredSize(new Dimension(120, 30));
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.cancelar();
            }
        });
        
        // Agregar los botones al panel (botón de cancelar a la derecha, punto 3)
        panelBotones.add(botonSolicitar);
        panelBotones.add(botonCancelar);
        
        // Configurar layout y agregar paneles al panel principal
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);
        
        // Agregar el panel principal a la ventana
        add(mainPanel);
    }
    
    /**
     * Muestra la ventana con la lista de productos
     */
    public void muestra(ControladorSolicitarReabastecimiento controlador, List<DetallesSolicitud> productos) {
        this.controlador = controlador;
        
        // Crear modelo de tabla con formato estándar (punto 4)
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable directamente
            }
        };
        
        modelo.addColumn("Producto");
        modelo.addColumn("Código");
        modelo.addColumn("Cantidad");
        
        // Llenar la tabla con los datos
        for (DetallesSolicitud item : productos) {
            Producto producto = item.getProducto();
            modelo.addRow(new Object[]{
                producto.getNombre(),
                producto.getCodigo(),
                0 // Cantidad a solicitar inicialmente
            });
        }
        
        // Aplicar modelo a la tabla
        tablaProductos.setModel(modelo);
        
        // Aplicar estilo a los encabezados de la tabla (punto 4)
        JTableHeader header = tablaProductos.getTableHeader();
        header.setFont(FONT_SUBTITLE);
        header.setBackground(COLOR_TABLE_HEADER);
        header.setForeground(COLOR_TEXT);
        
        // Aplicar estilo de filas alternadas (punto 4)
        aplicarEstiloFilasAlternadas();
        
        // Mostrar la ventana
        setVisible(true);
    }
    /**
     * Muestra un mensaje de información estándar (punto 6)
     */
    public void muestraMensaje(String mensaje) {
        JOptionPane.showMessageDialog(
            this, 
            mensaje, 
            "Información", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Obtiene la cantidad deseada para un producto usando diálogo estándar (punto 6)
     */
    public int obtenerCantidad() {
        String cantidadStr = JOptionPane.showInputDialog(
            this,
            "Ingrese la cantidad deseada para el producto: " ,
            "Cantidad",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (cantidadStr == null || cantidadStr.trim().isEmpty()) {
            return 0; // Si el usuario cancela o no ingresa nada, retorna 0
        }
        
        try {
            return Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            // Diálogo de error estándar (punto 6)
            JOptionPane.showMessageDialog(
                this,
                "Por favor ingrese un número válido",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return 0;
        }
    }

    /**
     * Actualiza la tabla con nuevos datos de productos
     */
    public void actualizarTabla(List<DetallesSolicitud> productos) {
        // Obtener modelo actual y limpiarlo
        DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
        modelo.setRowCount(0);
        
        // Agregar filas actualizadas
        for (DetallesSolicitud item : productos) {
            Producto producto = item.getProducto();
            modelo.addRow(new Object[]{
                producto.getNombre(),
                producto.getCodigo(),
                item.getCantidad()
            });
        }
        
        // Actualizar el modelo y aplicar estilos
        tablaProductos.setModel(modelo);
        aplicarEstiloFilasAlternadas();
        tablaProductos.updateUI();
    }
    
    /**
     * Método auxiliar para aplicar el estilo de filas alternadas a la tabla (punto 4)
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