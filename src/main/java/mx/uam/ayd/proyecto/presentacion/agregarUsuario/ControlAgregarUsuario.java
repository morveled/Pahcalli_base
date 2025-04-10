package mx.uam.ayd.proyecto.presentacion.agregarUsuario;

import org.springframework.stereotype.Component;
import javax.swing.JFrame;

@Component
public class ControlAgregarUsuario {

    public void inicia(JFrame parent) {
        VentanaAgregarUsuario ventana = new VentanaAgregarUsuario(parent);
        ventana.setVisible(true);
    }
}