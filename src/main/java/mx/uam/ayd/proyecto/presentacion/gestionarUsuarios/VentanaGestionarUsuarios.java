package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import javax.swing.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;

@SuppressWarnings("serial")
@Component
public class VentanaGestionarUsuarios extends JFrame {

    @Autowired
    private ControlAgregarUsuario controlAgregarUsuario;

    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton jButtonAgregar;

    public VentanaGestionarUsuarios() {
        jLabel1 = new JLabel("Gestión de usuarios");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));

        jTable1 = new JTable(new javax.swing.table.DefaultTableModel(
            new Object[][] {
                {"", "", "", "", "", "", "", "", "Editar", "Eliminar"},
                {"", "", "", "", "", "", "", "", "Editar", "Eliminar"},
            },
            new String[] {
                "Número de empleado", "Nombre(s)", "Apellido(s)", "Contraseña", "Correo",
                "Teléfono", "Puesto", "Sucursal", "Editar", "Eliminar"
            }
        ));

        jScrollPane1 = new JScrollPane(jTable1);

        jButtonAgregar = new JButton("Agregar nuevo usuario");
        jButtonAgregar.addActionListener(evt -> {
            controlAgregarUsuario.inicia(this); // Solo se pasaba this
        });

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void muestra() {
        setVisible(true);
    }
}