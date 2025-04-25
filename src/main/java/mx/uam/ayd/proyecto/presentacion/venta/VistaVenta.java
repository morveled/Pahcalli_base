package mx.uam.ayd.proyecto.presentacion.venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@org.springframework.stereotype.Component
public class VistaVenta extends JFrame {

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
    
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtTotal;
    private JTextField txtCodigo;
    private JTextField txtBusqueda;
    private ControlVenta control;
    private JLabel lblTotal;
    private JLabel lblCajero;

    public VistaVenta() {
        // Configuración básica de la ventana según el estándar (punto 1)
        setTitle("Realizar una venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500); // Dimensiones estándar
        setLocationRelativeTo(null);
        setBackground(COLOR_BACKGROUND);
        
        // Panel principal con márgenes estándar (punto 1)
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contentPane.setBackground(COLOR_BACKGROUND);
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Header panel with date and cashier info (punto 1 y 2)
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(COLOR_BACKGROUND);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        contentPane.add(headerPanel, BorderLayout.NORTH);
        
        // Título de la ventana (punto 2)
        JLabel titleLabel = new JLabel("Realizar Venta");
        titleLabel.setFont(FONT_TITLE);
        titleLabel.setForeground(COLOR_TEXT);
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Panel de información con fecha y cajero
        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        infoPanel.setBackground(COLOR_BACKGROUND);
        headerPanel.add(infoPanel, BorderLayout.CENTER);
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd / MMMM /yyyy    HH:mm 'hrs.'");
        JLabel lblDate = new JLabel(now.format(formatter));
        lblDate.setFont(FONT_LABEL);
        lblDate.setForeground(COLOR_TEXT);
        infoPanel.add(lblDate);
        
        lblCajero = new JLabel("Cajero Responsable: ");
        lblCajero.setFont(FONT_LABEL);
        lblCajero.setForeground(COLOR_TEXT);
        infoPanel.add(lblCajero);
        
        // Panel central que contiene búsqueda y tabla (punto 1)
        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        centerPanel.setBackground(COLOR_BACKGROUND);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        
        // Panel de búsqueda (puntos 1 y 2)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBackground(COLOR_PANEL);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        
        // Etiqueta y campo código (punto 2)
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(FONT_LABEL);
        lblCodigo.setForeground(COLOR_TEXT);
        searchPanel.add(lblCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setPreferredSize(new Dimension(100, 25));
        txtCodigo.setFont(FONT_LABEL);
        searchPanel.add(txtCodigo);
        
        // Etiqueta y campo cantidad (punto 2)
        JLabel lblBusqueda = new JLabel("Cantidad (opcional):");
        lblBusqueda.setFont(FONT_LABEL);
        lblBusqueda.setForeground(COLOR_TEXT);
        searchPanel.add(lblBusqueda);
        
        txtBusqueda = new JTextField();
        txtBusqueda.setPreferredSize(new Dimension(100, 25));
        txtBusqueda.setFont(FONT_LABEL);
        searchPanel.add(txtBusqueda);
        
        // Botón de agregar (punto 3)
        JButton btnBuscar = new JButton("Agregar");
        btnBuscar.setFont(FONT_BUTTON);
        btnBuscar.setBackground(COLOR_PRIMARY);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setPreferredSize(new Dimension(100, 30));
        searchPanel.add(btnBuscar);

        // Panel central para la tabla (punto 4)
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(COLOR_BACKGROUND);
        centerPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Configuración de la tabla (punto 4)
        String[] columnNames = {"Código", "Producto", "Dosis", "Presentación", "Precio Unitario", "Cantidad", "Subtotal", "Eliminar", "ID detalle"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 7; // Solo permitir editar la columna Cantidad
            }
        };
        table = new JTable(model);
        
        // Estilo de tabla según el estándar (punto 4)
        table.setRowHeight(25);
        table.setFont(FONT_LABEL);
        table.setGridColor(COLOR_TABLE_HEADER);
        table.setSelectionBackground(COLOR_TABLE_SELECTED);
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        
        // Estilo del encabezado de la tabla (punto 4)
        JTableHeader header = table.getTableHeader();
        header.setBackground(COLOR_TABLE_HEADER);
        header.setFont(FONT_SUBTITLE);
        header.setPreferredSize(new Dimension(header.getWidth(), 30));
        
        // Configuración de los botones de eliminar
        table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Eliminar").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        // Panel de desplazamiento para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_SECONDARY));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de suma total (punto 1)
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(COLOR_BACKGROUND);
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        centerPanel.add(totalPanel, BorderLayout.SOUTH);
        
        // Etiqueta e importe total (punto 2)
        lblTotal = new JLabel("Importe total: $");
        lblTotal.setFont(FONT_SUBTITLE);
        lblTotal.setForeground(COLOR_TEXT);
        totalPanel.add(lblTotal);
        
        txtTotal = new JTextField();
        txtTotal.setPreferredSize(new Dimension(100, 25));
        txtTotal.setEditable(false);
        txtTotal.setText("0.00");
        txtTotal.setFont(FONT_SUBTITLE);
        txtTotal.setHorizontalAlignment(JTextField.RIGHT);
        totalPanel.add(txtTotal);

        // Panel de botones en la parte inferior (punto 3)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(COLOR_BACKGROUND);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        // Botón confirmar con estilo estándar (punto 3)
        JButton btnConfirmar = new JButton("Confirmar Venta");
        btnConfirmar.setFont(FONT_BUTTON);
        btnConfirmar.setBackground(COLOR_PRIMARY);
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(btnConfirmar);
        
        // Botón cancelar con estilo estándar (punto 3)
        JButton btnCancelar = new JButton("Cancelar Venta");
        btnCancelar.setFont(FONT_BUTTON);
        btnCancelar.setBackground(COLOR_SECONDARY);
        btnCancelar.setForeground(COLOR_TEXT);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(btnCancelar);
        
        // Añadir listeners de acción
        btnConfirmar.addActionListener(e -> confirmarVenta());
        btnCancelar.addActionListener(e -> control.cancelarVenta());
        btnBuscar.addActionListener(e -> buscarProducto());
        txtCodigo.addActionListener(e -> agregarProductoPorCodigo());
        
        // Añadir listener para cambios en la tabla
        table.getModel().addTableModelListener(e -> {
            if (e.getColumn() == 5) { // Columna Cantidad
                actualizarSubtotalYTotal(e.getFirstRow());
            }
        });
    }

    private void buscarProducto() {
        String codigo = txtCodigo.getText().trim();
        String cant = txtBusqueda.getText().trim();
        //int cantidad = Integer.parseInt(cant);
        if (codigo.isEmpty() || cant.isEmpty()) {
            muestraError("Debe ingresar un código y una cantidad.");
            return;
       }

            try {
                int cantidad = Integer.parseInt(cant);
                control.agregarProductoAVenta(codigo, cantidad);
                txtCodigo.setText("");
                txtBusqueda.setText("");
                actualizarSubtotalYTotal(6);
             } catch (IllegalArgumentException e) {
                muestraError("Error al agregar producto: " + e.getMessage());
                return;
            }

    }

    public void confirmarVenta() {
        try{
            control.confirmarVenta();
            limpiarTabla();
        }catch(IllegalArgumentException e){
            muestraError("Error al confirmar venta: " + e.getMessage());
            return;
        }
    }



    private void agregarProductoPorCodigo() {
        String codigo = txtCodigo.getText().trim();
        if (!codigo.isEmpty()) {
            try {
                control.agregarProductoAVenta(codigo, 1);
                txtCodigo.setText("");
                actualizarSubtotalYTotal(6);
             } catch (IllegalArgumentException e) {
                muestraError("Error al agregar producto: " + e.getMessage());
                return;
            }
       }
    }

    private void actualizarSubtotalYTotal(int row) {
        // Implementation pending - will update subtotal for the row
        // and recalculate total
        double suma = 0.0;
        for(int fila = 0; fila<model.getRowCount(); fila++){
            Object valor = model.getValueAt(fila, row);
            if(valor != null){
                try{
                    suma += Double.parseDouble(valor.toString());

                }catch(NumberFormatException e){
                    muestraError("No se puede hacer el total");
                }
            }
        }

        String suma_text = String.valueOf(suma);
        txtTotal.setText(suma_text);


    }

    public void actualizaTabla(Object[] nuevaFila) {
        // Table
        model.addRow(nuevaFila);
    }

    public void limpiarTabla(){
        model.setRowCount(0);
        txtTotal.setText("0.00");
    }

    /**
     * Muestra un diálogo de error con formato estándar (punto 6)
     * @param mensaje Mensaje a mostrar al usuario
     */
    public void muestraError(String mensaje) {
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
    public void muestraExito(String mensaje) {
        // Configuración estándar para diálogos de información (punto 6)
        UIManager.put("OptionPane.messageFont", FONT_LABEL);
        UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
        UIManager.put("OptionPane.background", COLOR_BACKGROUND);
        UIManager.put("Panel.background", COLOR_BACKGROUND);
        UIManager.put("OptionPane.informationIcon", UIManager.getIcon("OptionPane.informationIcon"));
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void muestra(ControlVenta control) {
        this.control = control;
        setVisible(true);
    }

    public void setCajeroResponsable(String nombreCajero){
        lblCajero.setText("Cajero responsable: " + nombreCajero);
    }


    /**
     * Renderizador personalizado para el botón "Eliminar" con estilo estándar (punto 3)
     */
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setFont(FONT_BUTTON);
            setBackground(COLOR_PRIMARY);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Eliminar");
            return this;
        }
    }

    /**
     * Editor personalizado para el botón "Eliminar" con estilo estándar (punto 3)
     */
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setFont(FONT_BUTTON);
            button.setBackground(COLOR_PRIMARY);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            label = "Eliminar";
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                // Configuración estándar para diálogos de confirmación (punto 6)
                UIManager.put("OptionPane.messageFont", FONT_LABEL);
                UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
                UIManager.put("OptionPane.background", COLOR_BACKGROUND);
                UIManager.put("Panel.background", COLOR_BACKGROUND);
                UIManager.put("OptionPane.questionIcon", UIManager.getIcon("OptionPane.questionIcon"));
                
                // Diálogo de confirmación
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este producto?", 
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Long idDetalle = (Long) model.getValueAt(row, 8); // ID detalle en columna 8
                        control.eliminarDetalleVenta(idDetalle);
                        model.removeRow(row);
                        actualizarSubtotalYTotal(6); // Actualizar totales después de eliminar
                    } catch (Exception e) {
                        muestraError("Error al eliminar el producto: " + e.getMessage());
                    }
                }
            }
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

}
