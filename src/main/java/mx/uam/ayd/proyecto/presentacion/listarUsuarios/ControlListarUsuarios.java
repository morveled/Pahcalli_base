package mx.uam.ayd.proyecto.presentacion.listarUsuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

/**
 * Controlador para la ventana de listar usuarios
 */
@Slf4j
@Component
public class ControlListarUsuarios {
    
    @Autowired
    private ServicioEmpleado servicioEmpleado;
    
    @Autowired
    private VentanaListarUsuarios ventana;

    /**
     * Inicia la ventana de listado de usuarios
     */
    public void inicia() {
        try {
            // Recupera la lista de empleados del sistema
            List<Empleado> empleados = servicioEmpleado.getAll();
            
            // Registra en el log la información obtenida
            log.info("Recuperados " + empleados.size() + " empleados del sistema");
            
            // Muestra la ventana con los empleados recuperados
            ventana.mostrarUsuarios(empleados);
            ventana.muestra(this);
            
        } catch (Exception ex) {
            log.error("Error al recuperar los empleados", ex);
            ventana.mostrarError("No se pudieron cargar los usuarios: " + ex.getMessage());
        }
    }
}
