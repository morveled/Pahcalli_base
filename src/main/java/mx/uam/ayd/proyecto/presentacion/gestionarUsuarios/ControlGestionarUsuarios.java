package mx.uam.ayd.proyecto.presentacion.gestionarUsuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;

@Component
public class ControlGestionarUsuarios {

    @Autowired
    private VentanaGestionarUsuarios ventanaGestionarUsuarios;

    @Autowired
    private ControlAgregarUsuario controlAgregarUsuario;

    @Autowired
    private ServicioEmpleado servicioEmpleado;

    public void inicia() {
        ventanaGestionarUsuarios.setControl(this);

        List<Empleado> empleados = servicioEmpleado.getAll();

        for (Empleado empleado : empleados) {
            UsuarioTabla usuario = new UsuarioTabla(
                empleado.getNumeroEmpleado(),
                empleado.getNombre(),
                (empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno()).trim(),
                "", // contraseña no disponible desde la entidad Empleado
                empleado.getCorreoElectronico(),
                empleado.getTelefono(),
                empleado.getTipo() != null ? empleado.getTipo().getNombre() : "",
                empleado.getSucursal() != null ? empleado.getSucursal().getNombre() : ""
            );

            ventanaGestionarUsuarios.agregaUsuarioATabla(usuario);
        }
    
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

    /**
     * Lanza la ventana para editar un usuario existente.
     */
    public void editarUsuario(UsuarioTabla usuario, VentanaGestionarUsuarios ventana) {
        VentanaEditarUsuario ventanaEditarUsuario = new VentanaEditarUsuario(ventana, usuario, this);
        ventanaEditarUsuario.setVisible(true);
    }

    /**
     * Recibe los cambios de un usuario editado y actualiza la tabla.
     */
    public void actualizarUsuario(UsuarioTabla usuario) {
        ventanaGestionarUsuarios.actualizarUsuarioEnTabla(usuario);
    }

    public void eliminarUsuario(UsuarioTabla usuario) {
        ventanaGestionarUsuarios.eliminarUsuarioDeTabla(usuario);
    }
    
}