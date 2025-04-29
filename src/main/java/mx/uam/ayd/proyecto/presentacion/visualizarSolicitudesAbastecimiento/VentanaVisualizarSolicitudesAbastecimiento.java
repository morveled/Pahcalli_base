package mx.uam.ayd.proyecto.presentacion.visualizarSolicitudesAbastecimiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.SolicitudReabastecimiento;

/**
 * Ventana para visualizar las solicitudes de abastecimiento
 * 
 * @author Eduardo Morgado
 */
@Component
public class VentanaVisualizarSolicitudesAbastecimiento extends JFrame {

    private static final long serialVersionUID = 1L;
    
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
    
    private ControlVisualizarSolicitudesAbastecimiento control;
    private JTable tablaSolicitudes;
    private DefaultTableModel modelo;
    
    /**
     * Constructor
     */
    public VentanaVisualizarSolicitudesAbastecimiento() {
        // Configuración de la ventana según el estándar (punto 1)
        setTitle("Solicitudes de abastecimiento");
        setSize(800, 500); // Dimensiones estándar
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        // Panel principal con margen interior estándar (punto 1)
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(COLOR_BACKGROUND);
        
        // Panel de título en la parte superior (punto 2)
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(COLOR_BACKGROUND);
        
        JLabel lblTitle = new JLabel("Solicitudes de abastecimiento", JLabel.CENTER);
        lblTitle.setFont(FONT_TITLE);
        lblTitle.setForeground(COLOR_TEXT);
        titlePanel.add(lblTitle);
        
        // Panel central con la tabla
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(COLOR_BACKGROUND);
        
        // Crear el modelo de tabla
        modelo = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };
        
        // Definir columnas
        modelo.addColumn("Fecha");
        modelo.addColumn("Sucursal");
        modelo.addColumn("Acción");
        
        // Crear la tabla con estilos según el estándar (punto 4)
        tablaSolicitudes = new JTable(modelo);
        tablaSolicitudes.setFont(FONT_LABEL);
        tablaSolicitudes.setForeground(COLOR_TEXT);
        tablaSolicitudes.setRowHeight(25);
        tablaSolicitudes.setGridColor(new Color(220, 220, 220));
        tablaSolicitudes.setSelectionBackground(COLOR_TABLE_SELECTED);
        tablaSolicitudes.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        
        // Estilo de los encabezados de la tabla (punto 4)
        JTableHeader header = tablaSolicitudes.getTableHeader();
        header.setFont(FONT_SUBTITLE);
        header.setBackground(COLOR_TABLE_HEADER);
        header.setForeground(COLOR_TEXT);
        
        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaSolicitudes);
        scrollPane.setBackground(COLOR_BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones en la parte inferior (punto 3)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(COLOR_BACKGROUND);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(FONT_BUTTON);
        btnCerrar.setBackground(COLOR_SECONDARY);
        btnCerrar.setForeground(COLOR_TEXT);
        btnCerrar.setPreferredSize(new Dimension(120, 30));
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.termina();
            }
        });
        panelBotones.add(btnCerrar);
        
        // Agregar los paneles según la estructura estándar BorderLayout (punto 1)
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel);
    }
    
    /**
     * Método para mostrar la ventana y actualizarla con las solicitudes
     * 
     * @param control el controlador
     * @param solicitudes la lista de solicitudes
     */
    public void muestra(ControlVisualizarSolicitudesAbastecimiento control, List<SolicitudReabastecimiento> solicitudes) {
        this.control = control;
        actualizarTabla(solicitudes);
        setVisible(true);
    }
    
    /**
     * Método para actualizar la tabla con la lista de solicitudes
     * 
     * @param solicitudes la lista de solicitudes
     */
    public void actualizarTabla(final List<SolicitudReabastecimiento> solicitudes) {
        // Limpiar la tabla y eliminar listeners previos
        modelo.setRowCount(0);
        for (java.awt.event.MouseListener listener : tablaSolicitudes.getMouseListeners()) {
            if (listener instanceof java.awt.event.MouseAdapter) {
                tablaSolicitudes.removeMouseListener(listener);
            }
        }
        
        // Formato para fechas
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        
        // Llenar con datos
        for (SolicitudReabastecimiento solicitud : solicitudes) {
            String fecha = solicitud.getFecha() != null ? dateFormat.format(solicitud.getFecha()) : "Sin fecha";
            String sucursal = solicitud.getSucursal() != null ? solicitud.getSucursal().getNombre() : "Sin sucursal";
            
            // Agregar fila con los datos
            modelo.addRow(new Object[]{fecha, sucursal, "Revisar"});
        }
        
        // Aplicar estilo de filas alternadas según el estándar (punto 4)
        for (int i = 0; i < tablaSolicitudes.getRowCount(); i++) {
            if (i % 2 == 0) {
                // Filas pares con fondo blanco
                tablaSolicitudes.setBackground(COLOR_BACKGROUND);
            } else {
                // Filas impares con fondo gris claro
                tablaSolicitudes.setBackground(COLOR_PANEL);
            }
        }
        
        // Agregar nuevo MouseListener con referencia a las solicitudes actuales
        tablaSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tablaSolicitudes.rowAtPoint(evt.getPoint());
                int columna = tablaSolicitudes.columnAtPoint(evt.getPoint());
                
                if (columna == 2) { // Si se hace clic en la columna "Acción"
                    if (fila >= 0 && fila < solicitudes.size()) {
                        control.revisarSolicitud(solicitudes.get(fila));
                    }
                }
            }
        });
        tablaSolicitudes.updateUI();
    }
    
    /**
     * Clase para renderizar botones en la tabla
     */
    private class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        private static final long serialVersionUID = 1L;

        public ButtonRenderer() {
            setOpaque(true);
            // Aplicar estilo a los botones de acción en la tabla según el estándar (punto 3)
            setFont(FONT_BUTTON);
            setBackground(COLOR_PRIMARY);
            setForeground(Color.WHITE);
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            
            // Estilo para la celda seleccionada (punto 4)
            if (isSelected) {
                setBackground(COLOR_TABLE_SELECTED);
                setForeground(COLOR_TEXT);
            } else {
                setBackground(COLOR_PRIMARY);
                setForeground(Color.WHITE);
            }
            
            return this;
        }
    }
}
