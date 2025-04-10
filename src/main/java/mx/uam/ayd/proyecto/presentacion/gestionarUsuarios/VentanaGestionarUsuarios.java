package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import javax.swing.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

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
            new String[] {
                "Número de empleado", "Nombre(s)", "Apellido(s)", "Contraseña", "Correo",
                "Teléfono", "Puesto", "Sucursal", "Editar", "Eliminar"
            }, 0
        );
        jTable1.setModel(modeloTabla);
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
}