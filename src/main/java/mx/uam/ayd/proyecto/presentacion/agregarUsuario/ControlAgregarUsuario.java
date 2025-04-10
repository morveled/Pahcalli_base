package mx.uam.ayd.proyecto.presentacion.agregarUsuario;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.gestionarUsuarios.ControlGestionarUsuarios;

@Component
public class ControlAgregarUsuario {

    /**
     * Lanza la ventana de Agregar Usuario y le pasa el control principal de gestionar usuarios.
     * 
     * @param parent La ventana padre (VentanaGestionarUsuarios)
     * @param controlGestionarUsuarios El controlador que maneja la lista de usuarios
     */
    public void inicia(JFrame parent, ControlGestionarUsuarios controlGestionarUsuarios) {
        VentanaAgregarUsuario ventana = new VentanaAgregarUsuario(parent, controlGestionarUsuarios);
        ventana.setVisible(true);
    }
}