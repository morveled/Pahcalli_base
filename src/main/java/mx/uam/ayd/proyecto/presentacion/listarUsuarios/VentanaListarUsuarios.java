package mx.uam.ayd.proyecto.presentacion.listarUsuarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

/**
 * Ventana para listar los usuarios/empleados del sistema
 */
@Component
public class VentanaListarUsuarios extends JFrame {

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

    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private JButton btnCerrar;
    private ControlListarUsuarios control;

    /**
     * Crea la ventana de listar usuarios con el diseño estándar
     */
    public VentanaListarUsuarios() {
        // Configuración básica de la ventana (punto 1)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Listado de Usuarios");
        setSize(800, 500); // Dimensiones estándar
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
        
        // Título de la ventana (punto 2)
        JLabel titleLabel = new JLabel("Listado de Usuarios");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        
        // Panel central para la tabla (punto 4)
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(COLOR_BACKGROUND);
        contentPane.add(tablePanel, BorderLayout.CENTER);
        
        // Configuración de la tabla (punto 4)
        String[] columnNames = {"ID", "Nombre", "Apellidos", "Usuario", "Tipo", "Sucursal"};
        modeloTabla = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // La tabla no es editable
            }
        };
        
        tablaUsuarios = new JTable(modeloTabla);
        
        // Estilo de tabla según el estándar (punto 4)
        tablaUsuarios.setRowHeight(25);
        tablaUsuarios.setFont(FONT_LABEL);
        tablaUsuarios.setGridColor(COLOR_TABLE_HEADER);
        tablaUsuarios.setSelectionBackground(COLOR_TABLE_SELECTED);
        tablaUsuarios.setShowGrid(true);
        tablaUsuarios.setShowHorizontalLines(true);
        tablaUsuarios.setShowVerticalLines(true);
        
        // Estilo del encabezado de la tabla (punto 4)
        JTableHeader header = tablaUsuarios.getTableHeader();
        header.setBackground(COLOR_TABLE_HEADER);
        header.setFont(FONT_SUBTITLE);
        header.setPreferredSize(new Dimension(header.getWidth(), 30));
        
        // Scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_SECONDARY));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones en la parte inferior (punto 3)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(COLOR_BACKGROUND);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        // Botón cerrar con estilo estándar (punto 3)
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(FONT_BUTTON);
        btnCerrar.setBackground(COLOR_SECONDARY);
        btnCerrar.setForeground(COLOR_TEXT);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setPreferredSize(new Dimension(120, 30));
        btnCerrar.addActionListener(e -> dispose()); // Cerrar ventana
        buttonPanel.add(btnCerrar);
    }
    
    /**
     * Carga la lista de usuarios en la tabla
     * 
     * @param usuarios Lista de usuarios a mostrar
     */
    public void mostrarUsuarios(List<Empleado> usuarios) {
        // Limpiar la tabla primero
        modeloTabla.setRowCount(0);
        
        // Llenar la tabla con los datos de los usuarios
        for (Empleado usuario : usuarios) {
            Object[] fila = new Object[6];
            fila[0] = usuario.getNumeroEmpleado();
            fila[1] = usuario.getNombre();
            fila[2] = usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno();
            fila[3] = usuario.getUsuario() != null ? usuario.getUsuario().getNombre() : "";
            fila[4] = usuario.getTipo() != null ? usuario.getTipo().getNombre() : "";
            fila[5] = usuario.getSucursal() != null ? usuario.getSucursal().getNombre() : "";
            
            modeloTabla.addRow(fila);
        }
    }
    
    /**
     * Muestra un mensaje de error con formato estándar (punto 6)
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
     * Hace visible la ventana y establece el controlador
     * 
     * @param control Controlador de la ventana
     */
    public void muestra(ControlListarUsuarios control) {
        this.control = control;
        setVisible(true);
    }

}
