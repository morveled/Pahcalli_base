package mx.uam.ayd.proyecto.presentacion.menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.TipoEmpleado;
import mx.uam.ayd.proyecto.presentacion.gestionProductos.VentanaGestionProductos;

@SuppressWarnings("serial")
@Component
public class VentanaMenu extends JFrame {

    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JButton jButton4 = new JButton();
    private JButton jButton5 = new JButton();
    private JButton jButton6 = new JButton();
    private JButton jButton7 = new JButton();
    private JButton jButton8 = new JButton();
    private JButton jButton9 = new JButton();

    private ControlMenu controlMenu;

    @Autowired
    private VentanaGestionProductos ventanaGestionProductos;

    public VentanaMenu() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("¡Bienvenid@!");
        jLabel2.setText("Farmacia Pahcalli");

        jButton1.setText("Realizar Venta");
        jButton1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                controlMenu.mostrarRealizarVenta();
            }
        });

        jButton2.setText("Inventario de Farmacias");
        jButton2.addActionListener(evt -> controlMenu.mostrarMostrarInventario());

        jButton3.setText("Gestión De Usuarios");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlMenu.mostrarGestionUsuarios();
            }
        });
        

        jButton4.setText("Gestión De Promociones");

        jButton5.setText("Gestión de Inventario");
        jButton5.addActionListener(evt -> controlMenu.mostrarGestionInventario());

        jButton6.setText("Estadísticas");

        jButton7.setText("Gestión de Productos");
        jButton7.addActionListener(evt -> jButton7ActionPerformed());

        jButton8.setText("Solicitudes de Abastecimiento");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlMenu.mostrarSolicitudesAbastecimiento();
            }
        });

        jButton9.setText("Cerrar Sesión");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlMenu.cerrarSesion();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(93, 93, 93))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(106, 106, 106))))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9)
                    .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(153, 153, 153)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton2)
                            .addGap(30, 30, 30)
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addComponent(jButton4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5))
                    .addGap(7, 7, 7)
                    .addComponent(jButton6)
                    .addGap(35, 35, 35)
                    .addComponent(jButton7)
                    .addGap(18, 18, 18)
                    .addComponent(jButton8)
                    .addGap(18, 18, 18)
                    .addComponent(jButton9)
                    .addGap(18, 18, 18))
        );

        pack();
    }

    private void jButton7ActionPerformed() {
        ventanaGestionProductos.muestra(); 
    }

    public void muestra(ControlMenu control) {
        this.controlMenu = control;
        setVisible(true);
    }

    public void configurarVentana(Empleado empleado) {
        String tipo = empleado.getTipo().getNombre();
        jLabel1.setText("Bienvenid@ "+empleado.getNombre()+":"+tipo);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);

        switch (tipo) {
            case "Gerente":
            jButton1.setVisible(true);
            jButton3.setVisible(true);
            jButton5.setVisible(true);
            jButton7.setVisible(true);
            break;
            case "Cajero":
            jButton1.setVisible(true);
            break;
            case "Almacenista":
            jButton1.setVisible(false);
            jButton5.setVisible(true);
            jButton8.setVisible(true);
            break;

        }
    }
}
