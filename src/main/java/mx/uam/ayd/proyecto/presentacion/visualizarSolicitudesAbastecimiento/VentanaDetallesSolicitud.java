package mx.uam.ayd.proyecto.presentacion.visualizarSolicitudesAbastecimiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.DetallesSolicitud;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudReabastecimiento;

/**
 * Ventana para visualizar los detalles de una solicitud de abastecimiento
 * 
 * @author Eduardo Morgado
 */
@Component
public class VentanaDetallesSolicitud extends JFrame {

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
    
    private ControlDetallesSolicitud control;
    private JTable tablaDetalles;
    private DefaultTableModel modelo;
    
    /**
     * Constructor
     */
    public VentanaDetallesSolicitud() {
        // Configuración de la ventana según el estándar (punto 1)
        setTitle("Detalle de solicitud de abastecimiento");
        setSize(800, 500); // Dimensiones estándar
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(COLOR_BACKGROUND);
        setLayout(new BorderLayout());
        
        // Panel principal con márgenes estándar (punto 1)
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(COLOR_BACKGROUND);
        
        // Panel de título en la parte superior (punto 2)
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(COLOR_BACKGROUND);
        
        JLabel lblTitulo = new JLabel("Detalle de solicitud", JLabel.CENTER);
        lblTitulo.setFont(FONT_TITLE);
        lblTitulo.setForeground(COLOR_TEXT);
        titlePanel.add(lblTitulo);
        
        // Panel central con tabla y descripción
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(COLOR_BACKGROUND);
        
        // Información de la solicitud
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        panelInfo.setBackground(COLOR_BACKGROUND);
        
        JLabel lblSubtitulo = new JLabel("Productos solicitados");
        lblSubtitulo.setFont(FONT_SUBTITLE);
        lblSubtitulo.setForeground(COLOR_TEXT);
        lblSubtitulo.setAlignmentX(CENTER_ALIGNMENT);
        panelInfo.add(lblSubtitulo);
        panelInfo.add(Box.createVerticalStrut(5));
        
        centerPanel.add(panelInfo, BorderLayout.NORTH);
        
        // Crear el modelo de tabla
        modelo = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };
        
        // Definir columnas
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad en Almacén");
        modelo.addColumn("Cantidad Solicitada");
        
        // Crear la tabla con estilos estándar (punto 4)
        tablaDetalles = new JTable(modelo);
        tablaDetalles.setFont(FONT_LABEL);
        tablaDetalles.setForeground(COLOR_TEXT);
        tablaDetalles.setRowHeight(25);
        tablaDetalles.setGridColor(new Color(220, 220, 220));
        tablaDetalles.setSelectionBackground(COLOR_TABLE_SELECTED);
        
        // Estilo para los encabezados de la tabla (punto 4)
        JTableHeader header = tablaDetalles.getTableHeader();
        header.setFont(FONT_SUBTITLE);
        header.setBackground(COLOR_TABLE_HEADER);
        header.setForeground(COLOR_TEXT);
        
        JScrollPane scrollPane = new JScrollPane(tablaDetalles);
        scrollPane.setBackground(COLOR_BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones según el estándar (punto 3)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(COLOR_BACKGROUND);
        
        JButton btnAtender = new JButton("Marcar como Atendida");
        btnAtender.setFont(FONT_BUTTON);
        btnAtender.setBackground(COLOR_PRIMARY);
        btnAtender.setForeground(Color.WHITE);
        btnAtender.setPreferredSize(new Dimension(180, 30));
        btnAtender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Diálogo de confirmación estándar (punto 6)
                int confirmacion = JOptionPane.showConfirmDialog(
                    VentanaDetallesSolicitud.this, 
                    "¿Está seguro de marcar esta solicitud como atendida?", 
                    "Confirmar", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                
                if (confirmacion == JOptionPane.YES_OPTION) {
                    control.marcarComoAtendida();
                }
            }
        });
        
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
        
        // Botón cerrar a la derecha (punto 3)
        panelBotones.add(btnAtender);
        panelBotones.add(btnCerrar);
        
        // Estructura estándar de paneles (punto 1)
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel);
    }
    
    /**
     * Método para mostrar la ventana y actualizarla con los detalles de la solicitud
     * 
     * @param control el controlador
     * @param solicitud la solicitud cuyos detalles se mostrarán
     * @param detalles lista de detalles de la solicitud
     * @param cantidadesEnAlmacen lista de cantidades en almacén para cada producto
     */
    public void muestra(ControlDetallesSolicitud control, SolicitudReabastecimiento solicitud, 
                       List<DetallesSolicitud> detalles, List<Integer> cantidadesEnAlmacen) {
        this.control = control;
        actualizarTabla(detalles, cantidadesEnAlmacen);
        
        // Actualizar título con información de la solicitud
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String fechaFormateada = solicitud.getFecha() != null ? dateFormat.format(solicitud.getFecha()) : "Sin fecha";
        String sucursal = solicitud.getSucursal() != null ? solicitud.getSucursal().getNombre() : "Sin sucursal";
        
        // Actualizar la información del título usando el estándar (punto 2)
        JLabel lblTitulo = (JLabel) ((JPanel) ((JPanel) getContentPane().getComponent(0)).getComponent(0)).getComponent(0);
        lblTitulo.setText("Solicitud: " + sucursal + " - " + fechaFormateada);
        
        setTitle("Detalle de solicitud - " + sucursal + " (" + fechaFormateada + ")");
        
        setVisible(true);
    }
    
    /**
     * Método para actualizar la tabla con los detalles de la solicitud
     * 
     * @param detalles los detalles de la solicitud a mostrar
     * @param cantidadesEnAlmacen lista de cantidades en almacén para cada producto
     */
    public void actualizarTabla(List<DetallesSolicitud> detalles, List<Integer> cantidadesEnAlmacen) {
        // Limpiar la tabla
        modelo.setRowCount(0);
        
        // Llenar con datos
        for (int i = 0; i < detalles.size(); i++) {
            DetallesSolicitud detalle = detalles.get(i);
            String nombreProducto = detalle.getProducto().getNombre();
            Integer cantidadSolicitada = detalle.getCantidad();
            
            // Obtener la cantidad en almacén correspondiente
            Integer cantidadEnAlmacen = (i < cantidadesEnAlmacen.size()) ? cantidadesEnAlmacen.get(i) : 0;
            
            // Agregar fila con los datos
            modelo.addRow(new Object[]{nombreProducto, cantidadEnAlmacen, cantidadSolicitada});
        }
        
        // Aplicar estilo de filas alternadas según el estándar (punto 4)
        for (int i = 0; i < tablaDetalles.getRowCount(); i++) {
            if (i % 2 == 0) {
                // Filas pares con fondo blanco
                tablaDetalles.setBackground(COLOR_BACKGROUND);
            } else {
                // Filas impares con fondo gris claro
                tablaDetalles.setBackground(COLOR_PANEL);
            }
        }
        
        // Agregar listener a la tabla para editar la cantidad
        tablaDetalles.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) { // Solo con un clic
                    int row = tablaDetalles.getSelectedRow();
                    if (row >= 0) {
                        Integer cantidadEnAlmacen = (Integer) modelo.getValueAt(row, 1);
                        control.editarCantidad(row, cantidadEnAlmacen);
                    }
                }
            }
        });
        
        tablaDetalles.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = tablaDetalles.getSelectedRow();
                    if (row >= 0) {
                        Integer cantidadEnAlmacen = (Integer) modelo.getValueAt(row, 1);
                        control.editarCantidad(row, cantidadEnAlmacen);
                    }
                }
            }
        });
    }
}
