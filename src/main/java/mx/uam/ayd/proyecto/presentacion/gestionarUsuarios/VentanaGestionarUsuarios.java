package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Component;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@SuppressWarnings("serial")
public class VentanaGestionarUsuarios extends JFrame {

    private ControlGestionarUsuarios controlGestionarUsuarios;

    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton jButtonAgregar;
    private DefaultTableModel modeloTabla;

    private java.util.List<Empleado> listaEmpleados = new ArrayList<>();

    public VentanaGestionarUsuarios() {
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jButtonAgregar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel1.setText("Gestión de usuarios");

        modeloTabla = new DefaultTableModel(
                new Object[][] {},
                new Object[] {
                        "Número de empleado", "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Contraseña",
                        "Correo", "Teléfono", "Puesto", "Sucursal", "", ""
                }
        ) {
            public boolean isCellEditable(int row, int column) {
                return column == 9 || column == 10;
            }
        };

        jTable1.setModel(modeloTabla);
        // Botón verde "Editar"
        Color colorVerde = new Color(46, 204, 113);
        // Botón rojo "Eliminar"
        Color colorRojo = new Color(231, 76, 60);

        jTable1.getColumnModel().getColumn(9).setCellRenderer(new BotonRenderer("Editar", colorVerde));
        jTable1.getColumnModel().getColumn(9).setCellEditor(new BotonEditor(new JCheckBox(), "Editar", colorVerde, this::editarEmpleado));

        jTable1.getColumnModel().getColumn(10).setCellRenderer(new BotonRenderer("Eliminar", colorRojo));
        jTable1.getColumnModel().getColumn(10).setCellEditor(new BotonEditor(new JCheckBox(), "Eliminar", colorRojo, this::eliminarEmpleado));


        JTableHeader header = jTable1.getTableHeader();
        header.getColumnModel().getColumn(9).setHeaderValue("");
        header.getColumnModel().getColumn(10).setHeaderValue("");

        jScrollPane1.setViewportView(jTable1);

        jButtonAgregar.setText("Agregar nuevo usuario");
        jButtonAgregar.addActionListener(evt -> controlGestionarUsuarios.lanzarVentanaAgregarUsuario());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 1100, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAgregar, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(30)
                        .addComponent(jLabel1)
                        .addGap(20)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addGap(30)
                        .addComponent(jButtonAgregar)
                        .addGap(20)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public void muestra(List<Empleado> empleados) {
        limpiarTabla();
        for (Empleado empleado : empleados) {
            agregaEmpleadoATabla(empleado);
        }
        setVisible(true);
    }

    public void setControl(ControlGestionarUsuarios control) {
        this.controlGestionarUsuarios = control;
    }

    public void agregaEmpleadoATabla(Empleado empleado) {
        listaEmpleados.add(empleado);
        modeloTabla.addRow(new Object[] {
                empleado.getNumeroEmpleado(),
                empleado.getNombre(),
                empleado.getApellidoPaterno(),
                empleado.getApellidoMaterno(),
                "",
                empleado.getCorreoElectronico(),
                empleado.getTelefono(),
                empleado.getTipo() != null ? empleado.getTipo().getNombre() : "",
                empleado.getSucursal() != null ? empleado.getSucursal().getNombre() : "",
                "Editar",
                "Eliminar"
        });
    }

    public void actualizarEmpleadoEnTabla(Empleado empleado) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getIdEmpleado().equals(empleado.getIdEmpleado())) {
                listaEmpleados.set(i, empleado); // Actualiza la referencia
                modeloTabla.setValueAt(empleado.getNombre(), i, 1);
                modeloTabla.setValueAt(empleado.getApellidoPaterno(), i, 2);
                modeloTabla.setValueAt(empleado.getApellidoMaterno(), i, 3);
                modeloTabla.setValueAt("", i, 4); // contraseña
                modeloTabla.setValueAt(empleado.getCorreoElectronico(), i, 5);
                modeloTabla.setValueAt(empleado.getTelefono(), i, 6);
                modeloTabla.setValueAt(empleado.getTipo() != null ? empleado.getTipo().getNombre() : "", i, 7);
                modeloTabla.setValueAt(empleado.getSucursal() != null ? empleado.getSucursal().getNombre() : "", i, 8);
                break;
            }
        }
    }

    public void eliminarEmpleadoDeTabla(Empleado empleado) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getIdEmpleado().equals(empleado.getIdEmpleado())) {
                listaEmpleados.remove(i);
                modeloTabla.removeRow(i);
                break;
            }
        }
    }

    private Empleado getEmpleadoDesdeTabla(int fila) {
        return listaEmpleados.get(fila);
    }

    private void editarEmpleado(int fila) {
        Empleado empleado = getEmpleadoDesdeTabla(fila);
        controlGestionarUsuarios.editarEmpleado(empleado, this);
    }

    private void eliminarEmpleado(int fila) {
        Empleado empleado = getEmpleadoDesdeTabla(fila);
        controlGestionarUsuarios.eliminarEmpleado(empleado);
    }

    private void limpiarTabla() {
        modeloTabla.setRowCount(0);
        listaEmpleados.clear();
    }

    class BotonRenderer extends JButton implements TableCellRenderer {

        private String text;
        private Color bg;

        public BotonRenderer(String text, Color bg) {
            this.text = text;
            this.bg = bg;
            setOpaque(true);
            setForeground(Color.WHITE);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column
        ) {
            setText(text);

            if (isSelected) {
                setBackground(bg.darker());
            } else {
                setBackground(bg);
            }

            setForeground(Color.WHITE);
            setBorderPainted(false);
            return this;
        }
    }


    class BotonEditor extends DefaultCellEditor {
        private JButton button;
        private int selectedRow;
        private java.util.function.IntConsumer onClick;

        public BotonEditor(JCheckBox checkBox, String text, Color bg, java.util.function.IntConsumer onClick) {
            super(checkBox);
            this.onClick = onClick;

            button = new JButton(text);
            button.setOpaque(true);
            button.setBackground(bg);
            button.setForeground(Color.WHITE);
            button.addActionListener(e -> {
                fireEditingStopped();
                onClick.accept(selectedRow);
            });
        }

        @Override
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column
        ) {
            selectedRow = row;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }

        @Override
        public boolean stopCellEditing() {
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

}
