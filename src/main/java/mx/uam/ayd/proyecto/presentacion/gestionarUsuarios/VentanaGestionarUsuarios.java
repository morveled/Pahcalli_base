package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class VentanaGestionarUsuarios extends JFrame {

    private ControlGestionarUsuarios controlGestionarUsuarios;

    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton jButtonAgregar;
    private DefaultTableModel modeloTabla;
    private List<UsuarioTabla> listaUsuarios = new ArrayList<>();

    public VentanaGestionarUsuarios() {
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jButtonAgregar = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Gestión de usuarios");

        modeloTabla = new DefaultTableModel(
            new String[]{
                "Número de empleado", "Nombre(s)", "Apellido(s)", "Contraseña", "Correo",
                "Teléfono", "Puesto", "Sucursal", "Editar", "Eliminar"
            }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // todas las celdas no editables directamente
            }
        };
        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int fila = jTable1.rowAtPoint(evt.getPoint());
                int columna = jTable1.columnAtPoint(evt.getPoint());

                if (columna == 8) { // Columna "Editar"
                    UsuarioTabla usuario = listaUsuarios.get(fila);
                    controlGestionarUsuarios.editarUsuario(usuario, VentanaGestionarUsuarios.this);
                }
            }
        });

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
        setLocationRelativeTo(null); // Centrar en pantalla
    }

    public void muestra() {
        setVisible(true);
    }

    public void setControl(ControlGestionarUsuarios control) {
        this.controlGestionarUsuarios = control;
    }

    public void agregaUsuarioATabla(UsuarioTabla usuario) {
        listaUsuarios.add(usuario);
        modeloTabla.addRow(usuario.toRow());
    }

    public void actualizarUsuarioEnTabla(UsuarioTabla usuarioActualizado) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            UsuarioTabla usuario = listaUsuarios.get(i);
            if (usuario.getNumeroEmpleado().equals(usuarioActualizado.getNumeroEmpleado())) {
                listaUsuarios.set(i, usuarioActualizado);
                modeloTabla.removeRow(i);
                modeloTabla.insertRow(i, usuarioActualizado.toRow());
                break;
            }
        }
    }
}