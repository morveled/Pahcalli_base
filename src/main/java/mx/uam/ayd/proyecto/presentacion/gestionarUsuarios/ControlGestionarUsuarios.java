package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlGestionarUsuarios {

    @Autowired
    private VentanaGestionarUsuarios ventanaGestionarUsuarios;

    public void inicia() {
        ventanaGestionarUsuarios.muestra();
    }
}
