package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;

@Component
public class ControlGestionarUsuarios {

    @Autowired
    private VentanaGestionarUsuarios ventanaGestionarUsuarios;

    @Autowired
    private ControlAgregarUsuario controlAgregarUsuario;

    public void inicia() {
        ventanaGestionarUsuarios.setControl(this);
        ventanaGestionarUsuarios.muestra();
    }

    /**
     * Método que llama al control para abrir la ventana de agregar usuario.
     */
    public void lanzarVentanaAgregarUsuario() {
        controlAgregarUsuario.inicia(ventanaGestionarUsuarios, this);
    }

    /**
     * Método que se invoca desde VentanaAgregarUsuario para agregar un nuevo usuario.
     */
    public void agregaUsuario(UsuarioTabla usuario) {
        ventanaGestionarUsuarios.agregaUsuarioATabla(usuario);
    }
}

