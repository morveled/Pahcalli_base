package mx.uam.ayd.proyecto.presentacion.modificarProducto;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import mx.uam.ayd.proyecto.negocio.modelo.*;

public class VentanaModificarProducto extends JDialog {
    
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

    private JTextField txtCodigo, txtNombre, txtDescripcion, txtContenido, txtPrecio;
    private JCheckBox chkReceta;
    private JComboBox<Laboratorio> cmbLaboratorio;
    private JComboBox<Ingrediente> cmbIngrediente;
    private JComboBox<CategoriaProducto> cmbCategoria;
    private JButton btnModificar;
    private JButton btnCancelar;

    /**
     * Constructor de la ventana de modificación de productos
     * 
     * @param parent Ventana padre
     * @param laboratorios Lista de laboratorios disponibles
     * @param ingredientes Lista de ingredientes disponibles
     * @param categorias Lista de categorías disponibles
     */
    public VentanaModificarProducto(JFrame parent, List<Laboratorio> laboratorios,
                                  List<Ingrediente> ingredientes, List<CategoriaProducto> categorias) {
        super(parent, "Modificar Producto", true);
        // Configuración básica de la ventana (punto 1)
        setSize(800, 500); // Dimensiones estándar
        setLocationRelativeTo(parent);
        setResizable(true);
        getContentPane().setBackground(COLOR_BACKGROUND);
        initUI(laboratorios, ingredientes, categorias);
    }

    /**
     * Inicializa la interfaz de usuario con el estándar visual (puntos 1-6)
     * 
     * @param laboratorios Lista de laboratorios disponibles
     * @param ingredientes Lista de ingredientes disponibles
     * @param categorias Lista de categorías disponibles
     */
    private void initUI(List<Laboratorio> laboratorios, List<Ingrediente> ingredientes, 
                       List<CategoriaProducto> categorias) {
        // Panel principal con márgenes estándar (punto 1)
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(COLOR_BACKGROUND);
        
        // Título de la ventana (punto 2)
        JLabel titulo = new JLabel("Modificar Producto", SwingConstants.CENTER);
        titulo.setFont(FONT_TITLE);
        titulo.setForeground(COLOR_TEXT);
        mainPanel.add(titulo, BorderLayout.NORTH);
        
        // Panel de formulario con espaciado estándar (punto 2)
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 15));
        formPanel.setBackground(COLOR_BACKGROUND);

        // Campos de texto con estilos estándar (punto 2)
        txtCodigo = addFormField(formPanel, "Código:");
        txtNombre = addFormField(formPanel, "Nombre:");
        txtDescripcion = addFormField(formPanel, "Descripción:");
        txtContenido = addFormField(formPanel, "Contenido:");
        txtPrecio = addFormField(formPanel, "Precio:");
        
        // Checkbox con estilo estándar (punto 2)
        JLabel recetaLabel = new JLabel("Requiere Receta:");
        recetaLabel.setFont(FONT_LABEL);
        recetaLabel.setForeground(COLOR_TEXT);
        formPanel.add(recetaLabel);
        
        chkReceta = new JCheckBox();
        chkReceta.setBackground(COLOR_BACKGROUND);
        formPanel.add(chkReceta);
        
        // Combos con estilo estándar (punto 2)
        JLabel ingredienteLabel = new JLabel("Ingrediente Activo:");
        ingredienteLabel.setFont(FONT_LABEL);
        ingredienteLabel.setForeground(COLOR_TEXT);
        formPanel.add(ingredienteLabel);
        
        cmbIngrediente = new JComboBox<>(ingredientes.toArray(new Ingrediente[0]));
        cmbIngrediente.setFont(FONT_LABEL);
        formPanel.add(cmbIngrediente);
        
        JLabel laboratorioLabel = new JLabel("Laboratorio:");
        laboratorioLabel.setFont(FONT_LABEL);
        laboratorioLabel.setForeground(COLOR_TEXT);
        formPanel.add(laboratorioLabel);
        
        cmbLaboratorio = new JComboBox<>(laboratorios.toArray(new Laboratorio[0]));
        cmbLaboratorio.setFont(FONT_LABEL);
        formPanel.add(cmbLaboratorio);
        
        JLabel categoriaLabel = new JLabel("Categoría:");
        categoriaLabel.setFont(FONT_LABEL);
        categoriaLabel.setForeground(COLOR_TEXT);
        formPanel.add(categoriaLabel);
        
        cmbCategoria = new JComboBox<>(categorias.toArray(new CategoriaProducto[0]));
        cmbCategoria.setFont(FONT_LABEL);
        formPanel.add(cmbCategoria);

        // Panel de botones con alineación a la derecha (punto 3)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(COLOR_BACKGROUND);
        
        // Botón de cancelar con estilo estándar (punto 3)
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(FONT_BUTTON);
        btnCancelar.setBackground(COLOR_SECONDARY);
        btnCancelar.setForeground(COLOR_TEXT);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setPreferredSize(new Dimension(120, 30));
        btnCancelar.addActionListener(e -> dispose()); // Cerrar ventana al cancelar
        buttonPanel.add(btnCancelar);
        
        // Botón de modificar con estilo estándar (punto 3)
        btnModificar = new JButton("Modificar Producto");
        btnModificar.setFont(FONT_BUTTON);
        btnModificar.setBackground(COLOR_PRIMARY);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFocusPainted(false);
        btnModificar.setBorderPainted(false);
        btnModificar.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(btnModificar);
        
        // Panel de contenido con espaciado estándar (punto 1)
        JPanel contentPanel = new JPanel(new BorderLayout(10, 20));
        contentPanel.setBackground(COLOR_BACKGROUND);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Añadir panel de contenido al panel principal
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
    }

    /**
     * Crea y agrega un campo de formulario con estilo estándar (punto 2)
     * 
     * @param panel Panel al que se añadirá el campo
     * @param labelText Texto de la etiqueta
     * @return Campo de texto creado
     */
    private JTextField addFormField(JPanel panel, String labelText) {
        // Etiqueta con estilo estándar
        JLabel label = new JLabel(labelText);
        label.setFont(FONT_LABEL);
        label.setForeground(COLOR_TEXT);
        panel.add(label);
        
        // Campo de texto con estilo estándar
        JTextField textField = new JTextField();
        textField.setFont(FONT_LABEL);
        textField.setPreferredSize(new Dimension(180, 25));
        panel.add(textField);
        
        return textField;
    }

    public void agregarListenerModificar(ActionListener listener) {
        btnModificar.addActionListener(listener);
    }

    // Métodos para obtener los valores
    public String getCodigo() { return txtCodigo.getText(); }
    public String getNombre() { return txtNombre.getText(); }
    public String getDescripcion() { return txtDescripcion.getText(); }
    public String getContenido() { return txtContenido.getText(); }
    public double getPrecio() { return Double.parseDouble(txtPrecio.getText()); }
    public boolean getReceta() { return chkReceta.isSelected(); }
    public Laboratorio getLaboratorio() { return (Laboratorio) cmbLaboratorio.getSelectedItem(); }
    public Ingrediente getIngrediente() { return (Ingrediente) cmbIngrediente.getSelectedItem(); }
    public CategoriaProducto getCategoria() { return (CategoriaProducto) cmbCategoria.getSelectedItem(); }

    // Métodos para establecer valores (útil al cargar un producto existente)
    public void setCodigo(String codigo) { txtCodigo.setText(codigo); }
    public void setNombre(String nombre) { txtNombre.setText(nombre); }
    public void setDescripcion(String descripcion) { txtDescripcion.setText(descripcion); }
    public void setContenido(String contenido) { txtContenido.setText(contenido); }
    public void setPrecio(double precio) { txtPrecio.setText(String.valueOf(precio)); }
    public void setReceta(boolean receta) { chkReceta.setSelected(receta); }
    public void setLaboratorio(Laboratorio laboratorio) { cmbLaboratorio.setSelectedItem(laboratorio); }
    public void setIngrediente(Ingrediente ingrediente) { cmbIngrediente.setSelectedItem(ingrediente); }
    public void setCategoria(CategoriaProducto categoria) { cmbCategoria.setSelectedItem(categoria); }

    /**
     * Muestra un mensaje de éxito con estilo estándar (punto 6)
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
}